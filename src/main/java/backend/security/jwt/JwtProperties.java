package backend.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey = "4VHJND2BboLk1vvFgCn979XuFnD97cBx";
    private long validInMs = 100000;

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
