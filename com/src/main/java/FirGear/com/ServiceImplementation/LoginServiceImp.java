package FirGear.com.ServiceImplementation;

import FirGear.com.Common.FitGearException;
import FirGear.com.Common.Message;
import FirGear.com.Entity.TokenEntity;
import FirGear.com.Models.LoginModel;
import FirGear.com.Models.TokenModel;
import FirGear.com.Entity.RegisterEntity;
import FirGear.com.Repository.RegisterRepository;
import FirGear.com.Repository.TokenRepository;
import FirGear.com.Services.LoginService;
import FirGear.com.Security.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public TokenModel getUser(LoginModel loginModel) {
        try {
            RegisterEntity user = registerRepository.findByEmailAddress(loginModel.getEmailAddress());
            TokenEntity tokenEntity = tokenRepository.findByEmailAddress(loginModel.getEmailAddress());

            if (user != null && passwordEncoder.matches(loginModel.getPassword(), user.getPassword()) && tokenEntity == null) {
                String accessToken = jwtUtil.generateToken(user.getFullName(), user.getId(),  user.getEmailAddress());
                String refreshToken = jwtUtil.generateRefreshToken( user.getFullName(), user.getId(),  user.getEmailAddress());

                Date expirationDate = Jwts.parser()
                        .setSigningKey(jwtUtil.getSecretKey())
                        .parseClaimsJws(accessToken)
                        .getBody()
                        .getExpiration();

                LocalDateTime expirationLocalDateTime = expirationDate.toInstant()
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime();

                TokenModel tokenModel = new TokenModel();
                tokenModel.setAccessToken(accessToken);
                tokenModel.setRefreshToken(null);
                tokenModel.setExpiresIn(expirationLocalDateTime);

                TokenEntity token = new TokenEntity();
                token.saveAll(tokenModel, user.getEmailAddress(), user.getPassword());
                tokenRepository.save(token);
                return tokenModel;
            }
            if(tokenEntity != null && user != null && passwordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
                TokenModel tokenModel = new TokenModel();
                tokenModel.setAccessToken(tokenEntity.getAccessToken());
                tokenModel.setRefreshToken(null);
                tokenModel.setExpiresIn(tokenEntity.getExpireIn());
                return tokenModel;
            }
            if ((user == null) || (user != null && !passwordEncoder.matches(loginModel.getPassword(), user.getPassword()))) {
                throw new FitGearException(Message.USER_NOT_FOUND);
            }
            return null;
        } catch (FitGearException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An unexpected error occurred", e);
        }

    }
}
