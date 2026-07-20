package in.kp.authify.IO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileRequest {
    @NotBlank(message = "Name should be not empty")
    private String name;
    @Email(message = "Enter a valid email Address")
    @NotNull(message = "Email must be not empty")
    private String email;
    @Size(min = 6, message = "Password should be at least 6 character")
    private String password;
}
