package dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    String login;
    String hash_password;
    String email;
    String full_name;
    String nickname;
    Timestamp register_date;
}
