package FirGear.com.Security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().equals("/login/user") || request.getRequestURI().equals("/register/register-user")) {
            // Skip token validation for the login endpoint
            filterChain.doFilter(request, response);
            return;
        }

        // Get the Authorization header
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // Extract token from the header

            if (jwtUtil.validateToken(token)) {
                Map<String, Object> userDetails = jwtUtil.extractUserDetails(token);
                if (userDetails != null) {

                    String userEmail = (String) userDetails.get("email"); // Extract the username (email) from user details
                    String userId = (String) userDetails.get("userId");
                    String name = (String) userDetails.get("name");

                    // Set the username in the security context for further processing
                    SecurityContextHolder.getContext()
                            .setAuthentication(new UsernamePasswordAuthenticationToken(userEmail, null, new ArrayList<>()));

                    // Send a success response to the frontend for token validation
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(
                            "{ \"status\": \"success\", \"message\": \"Token is valid\", " +
                                    "\"userId\": \"" + userId + "\", " +
                                    "\"name\": \"" + name + "\", " +
                                    "\"email\": \"" + userEmail + "\"}"
                    );
                    return; // Stop further processing
                }
            } else {
                // Invalid token, send error message
                sendErrorResponse(response, "Invalid or expired token");
                return;
            }
        } else {
            // Missing or malformed token, send error message
            sendErrorResponse(response, "Authorization token missing or malformed");
            return;
        }

        // Proceed with the next filter in the chain if no response was sent
        filterChain.doFilter(request, response);
    }

    // Utility method to send error response
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
        response.getWriter().write(
                "{ \"status\": \"error\", \"message\": \"" + message + "\" }"
        );
    }



}
