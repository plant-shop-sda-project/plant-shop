package pl.sda.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;

    private String roles;

}
