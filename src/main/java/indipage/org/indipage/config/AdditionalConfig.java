package indipage.org.indipage.config;

import indipage.org.indipage.auth.service.apple.AppleTokenValidationContext;
import indipage.org.indipage.auth.service.google.GoogleTokenValidationContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        AppleTokenValidationContext.class,
        GoogleTokenValidationContext.class
})
public class AdditionalConfig {
}
