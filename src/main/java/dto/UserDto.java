package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
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