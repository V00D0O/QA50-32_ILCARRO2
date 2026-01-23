package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
