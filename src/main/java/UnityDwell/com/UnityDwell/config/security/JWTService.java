package UnityDwell.com.UnityDwell.config.security;

import UnityDwell.com.UnityDwell.model.users.User;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@Service
public class JWTService {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String tenantURI;

    private static final String ROLES_KEY = "user_roles";
    private static final String EMAIL_KEY = "user_email";
    public static final String USER_IDENTIFICATOR_KEY = "user_identificator";

    public String extractUserEmail(String jwtToken) {
        return extractAllClaims(jwtToken).get(EMAIL_KEY).asString();
    }

    public String extractUserRole(String jwtToken) {
        return extractAllClaims(jwtToken).get(ROLES_KEY).asList(String.class).stream()
                .findFirst().orElse(null);
    }

    public boolean  isTokenValid(String jwtToken, User userDetails) {
        final String userName = extractUserEmail(jwtToken);
        final String userId = extractUserId(jwtToken);
        return userName.equals(userDetails.getUsername()) && userId.equals(userDetails.getId().toString());
    }

    private Map<String, Claim> extractAllClaims(String jwtToken) {
        DecodedJWT jwt = JWT.decode(jwtToken);
        return jwt.getClaims();
    }

    public void verifyToken(String jwtToken) {
        DecodedJWT jwt = JWT.decode(jwtToken);
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) getPublicKey(jwt), null);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(tenantURI)
                .acceptExpiresAt(3)
                .build();
        verifier.verify(jwtToken);
    }

    private String extractUserId(String jwtToken) {
        return extractAllClaims(jwtToken).get(USER_IDENTIFICATOR_KEY).asString();
    }

    private PublicKey getPublicKey(DecodedJWT jwtToken) {
        JwkProvider provider = new UrlJwkProvider(tenantURI);
        Jwk jwk;
        PublicKey publicKey;
        try {
            jwk = provider.get(jwtToken.getKeyId());
            publicKey = jwk.getPublicKey();
        } catch (JwkException e) {
            throw new RuntimeException(e);
        }
        return publicKey;
    }
}
