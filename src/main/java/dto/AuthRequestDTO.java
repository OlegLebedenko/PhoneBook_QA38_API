package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class AuthRequestDTO {

//    "username": "string",
//    "password": "g%09!ehSy0J<;HN$v;}5~"

    String username;
    String password;
}
