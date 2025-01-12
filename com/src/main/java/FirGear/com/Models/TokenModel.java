package FirGear.com.Models;

import lombok.*;

import java.time.LocalDateTime;

/**
 * TokenModel class represents the structure of a token payload.
 * It contains the access token, refresh token, and other optional fields for managing authentication tokens.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenModel {

    /**
     * The access token used for authenticating API requests.
     */
    private String accessToken;

    /**
     * The refresh token used for obtaining a new access token when the current one expires.
     */
    private String refreshToken;

    /**
     * Optional field for token type (e.g., "Bearer").
     */
    private String tokenType;

    /**
     * Optional field for token expiration time in seconds.
     */
    private LocalDateTime expiresIn;


}
