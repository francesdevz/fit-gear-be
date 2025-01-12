package FirGear.com.Entity;


import FirGear.com.Models.TokenModel;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class TokenEntity {
    private String emailAddress;
    private String password;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expireIn;
    private String tokenType;

    public void saveAll(TokenModel tokenModel, String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.accessToken = tokenModel.getAccessToken();
        this.refreshToken = tokenModel.getRefreshToken();
        this.expireIn = tokenModel.getExpiresIn();
        this.tokenType = tokenModel.getTokenType();
    }
}
