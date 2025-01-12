package FirGear.com.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginModel class represents the structure of user login data.
 * It contains the fields required for a user to log in to the system.
 */
@Data
@NoArgsConstructor
public class LoginModel {

    /**
     * The email address of the user attempting to log in.
     * It is used to identify the user in the system.
     */
    private String emailAddress;

    /**
     * The password entered by the user for authentication.
     */
    private String password;

}
