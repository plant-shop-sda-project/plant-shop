package pl.sda.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.model.User;
import pl.sda.repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //metoda pobiera użytkownika z bazy danych i mapuje go na obiekt UserDetails, który jest modelem springowym przechowującym dane o użytkowniku i rolach
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userFromDB = userRepository.findByUsername(username);

        if (userFromDB == null) {
            throw new UsernameNotFoundException("Username " + username + " not found in database!");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(userFromDB.getUsername())
                .password(userFromDB.getPassword())
                //zamień listę typu Role na tablicę typu String zawierającą nazwy ról
                .roles(userFromDB.getRoles()
                        .stream()
                        .map(u -> u.getName())
                        .toArray(String[]::new)
                ).build();
    }
}
