package pl.sda.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {

    private String actualPassword;

    private String newPassword;

    private String newRepeatedPassword;

}
