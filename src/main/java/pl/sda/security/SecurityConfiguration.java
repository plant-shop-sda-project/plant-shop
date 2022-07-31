package pl.sda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //pozwalaj zrealizować wszystkie żądania do określonych ścieżek i podścieżek
                .antMatchers("/login", "/h2-console/**", "/registration",
                        "/register", "/css/style.css","/js/bootstrap.js","/css/style.scss")
                .permitAll()
//                .antMatchers("/css/style.css","/js/bootstrap.js","/css/style.scss").permitAll()
                //tylko user z rolą ADMIN może wyświetlić wszystko na podścieżkach /admin/
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                //wszyscy pozostali userzy mogą mieć dostęp z wymienionymi rolami do wszystkich ścieżek, które nie są  /admin/
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                //przekieruj na odpowiednią stronę po zalogowaniu
                .successHandler(customSuccessHandler)
//                .defaultSuccessUrl("/products/list") //zakomentowane ponieważ używamy CustomSuccessHandler
                .failureUrl("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                //wyłączanie frame options na potrzeby poprawnego działania konsoli H2
                .headers().frameOptions().disable();
    }

    //metoda określa, że Spring będzie pobierał użytkowników z bazy danych i szyfrował hasła za pomobą algorytmu BCrypt
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
