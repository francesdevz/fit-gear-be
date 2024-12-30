package FirGear.com.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterModel {
    private String fullName;
    private String emailAddress;
    private String password;
    private String repeatPassword;
}
