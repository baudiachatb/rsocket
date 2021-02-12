package b.com.rsocker.demo.server.config;

import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;
import org.springframework.security.rsocket.core.SecuritySocketAcceptorInterceptor;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

@Configuration
@EnableRSocketSecurity
public class RSocketConfig {
//    @Bean
//    public RSocketMessageHandler rsocketMessageHandler() {
//        RSocketMessageHandler handler = new RSocketMessageHandler();
//        handler.setRouteMatcher(new PathPatternRouteMatcher());
//        return handler;
//    }

    //    @Bean
//    RSocketStrategies socketStrategies() {
//        return RSocketStrategies.Builder()
//                .encoder(new)
//                .decoder()
//                .build();
//    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        return httpSecurity
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                .anyExchange().permitAll())
                .build();
    }

    @Bean
    ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders
//                .fromOidcIssuerLocation("https://auth.anythingvn.com/auth/realms/Authorization");
                .fromIssuerLocation("https://auth.anythingvn.com/auth/realms/Authorization");
    }

    @Bean
    PayloadSocketAcceptorInterceptor rsocketInterceptor(RSocketSecurity rsocket) {
        rsocket
                .authorizePayload(authorize ->
                        authorize
                                .anyRequest().authenticated()
                                .anyExchange().permitAll()
                )
                .jwt(jwtSpec -> {
                    try {
                        jwtSpec.authenticationManager(new JwtReactiveAuthenticationManager(jwtDecoder()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return rsocket.build();
    }

}
