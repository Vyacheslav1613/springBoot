package ru.netology.springgiga.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springgiga.profile.DevProfile;
import ru.netology.springgiga.profile.ProductionProfile;
import ru.netology.springgiga.profile.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
