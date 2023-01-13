/*package Recipe4;

import lombok.NonNull;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {*/
    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                // No need for a session to be created, it is the job of the authentication service
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                // Needed to emit the correct error code when a client tries to access an endpoint that requires auth
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpStatus.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/identify/**", "/actuator/**").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }*/

    // https://stackoverflow.com/questions/58157755/spring-boot-2-spring-security-login-form-session-redis-not-working
    // https://docs.spring.io/spring-session/docs/2.5.0/reference/html5/guides/java-custom-cookie.html
   /* @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }*/
/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        // todo discover why CORS don't work with JSON files and axios.
                        .allowedHeaders("Authorization", "Cache-Control", "Content-Type",
                                "Access-Control-Request-Headers", "Access-Control-Request-Method",
                                "Access-Control-Allow-Origin", "Access-Control-Allow-Headers")
                        .allowedOrigins("*");
            }
        };
    }*/
//}