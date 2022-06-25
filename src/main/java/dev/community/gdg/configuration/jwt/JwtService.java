package dev.community.gdg.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class JwtService {
    private final String CLAIM_NAME_MEMBER_ID = "memberId";

    @Value("${jwt.secretKey}")
    private String secretKey;

    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secretKey);
        jwtVerifier = JWT.require(algorithm).build();
    }

    /**
     * from memberId to accessToken
     */
    public String encode(Long memberId) {
        return JWT.create()
                .withClaim(CLAIM_NAME_MEMBER_ID, memberId)
                .sign(algorithm);
    }

    /**
     * from accessToken to memberId
     */
    public Long decode(String token) {
        try {
            return jwtVerifier.verify(token)
                    .getClaim(CLAIM_NAME_MEMBER_ID)
                    .asLong();
        } catch (JWTVerificationException e) {
            log.warn("Failed to decode jwt. token: {}", token, e);
            throw e;
        }
    }
}
