package FirGear.com.Models;

import lombok.*;

/**
 * RegisterModel class represents the structure of user registration data.
 * It contains the fields required to register a new user in the system.
 */
@Data
@NoArgsConstructor
public class RegisterModel {

    /**
     * The full name of the user registering an account.
     */
    private String fullName;

    /**
     * The email address of the user. Used as a unique identifier for the account.
     */
    private String emailAddress;

    /**
     * The password chosen by the user for the account.
     */
    private String password;

    /**
     * The repeated password to confirm the user's intended password.
     */
    private String repeatPassword;

}
