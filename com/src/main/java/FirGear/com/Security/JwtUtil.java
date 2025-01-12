package FirGear.com.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final SecretKey secretKey = Keys.hmacShaKeyFor("your-secret-key-your-secret-key-your-secret-key".getBytes());

    // Generate JWT token
    public String generateToken(String username, String userId, String email) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusDays(1);
        Date expirationDate = Date.from(expirationTime.toInstant(ZoneOffset.UTC));
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .claim("userId", userId)  // Add user ID as a claim
                .claim("name", username)      // Add user name as a claim
                .claim("email", email)    // Add email address as a claim
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Generate refresh token (long-lived)
    public String generateRefreshToken(String username, String userId, String email) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusDays(7); // Refresh token expires in 7 days
        Date expirationDate = Date.from(expirationTime.toInstant(ZoneOffset.UTC));

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .claim("userId", userId)  // Add user ID as a claim
                .claim("name", username)      // Add user name as a claim
                .claim("email", email)    // Add email address as a claim
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }


    public SecretKey getSecretKey() {
        return secretKey;
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Map<String, Object> extractUserDetails(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        System.out.println("Claims: " + claims);

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("userId", claims.get("userId"));
        userDetails.put("name", claims.get("name"));
        userDetails.put("email", claims.get("email"));
        return userDetails;
    }


}
