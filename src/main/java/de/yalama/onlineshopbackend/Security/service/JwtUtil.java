package de.yalama.onlineshopbackend.Security.service;

import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static de.yalama.onlineshopbackend.Security.SecurityConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtil {

    @Autowired
    private UserService userService;

    /**
     * Used to extract claims from JWTokens.
     * Claims are encrypted pieces of information in a string called token, read more about that here:
     * https://auth0.com/docs/tokens/json-web-tokens/json-web-token-claims
     *
     * Claims used here: Username, date, signature Algorithm
     *
     * @param token The JWToken to decrypt
     * @param claimsResolver resolves claims in the token
     * @param <T> class of the token
     * @return depending on the claim returns one of the pieces of information listed above
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates JWToken from the passed UserDetails instance
     *
     * @param userDetails passed by WebSecurity Config
     * @return JWT for a user as a string
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>();
        User user = this.userService.findByEmail(userDetails.getUsername());
        claims.put("id", user.getId());
        claims.put("isBanned", user.isBanned());
        claims.put("isConfirmed", user.isEmailConfirmed());
        claims.put("email", user.getEmail());
        claims.put("username", user.getUsername());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
