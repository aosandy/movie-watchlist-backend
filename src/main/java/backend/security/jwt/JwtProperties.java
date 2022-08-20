package backend.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey = System.getenv("JWT_SECRET_KEY");
    private long validInMs = 86_400_000;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getValidInMs() {
        return validInMs;
    }

    public void setValidInMs(long validInMs) {
        this.validInMs = validInMs;
    }
}
