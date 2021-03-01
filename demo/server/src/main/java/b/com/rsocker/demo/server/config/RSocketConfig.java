package b.com.rsocker.demo.server.config;

import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.MetadataExtractor;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
//import org.springframework.security.config.annotation.rsocket.PayloadInterceptorOrder;
//import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
//import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
//import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
//import org.springframework.security.rsocket.api.PayloadExchange;
//import org.springframework.security.rsocket.authentication.AuthenticationPayloadInterceptor;
//import org.springframework.security.rsocket.authentication.PayloadExchangeAuthenticationConverter;
//import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;
//import org.springframework.security.rsocket.core.SecuritySocketAcceptorInterceptor;
//import org.springframework.security.rsocket.metadata.BearerTokenMetadata;
//import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
//@EnableRSocketSecurity
public class RSocketConfig {
    @Bean
    public RSocketMessageHandler rsocketMessageHandler() {
        RSocketMessageHandler handler = new RSocketMessageHandler();
        handler.setRouteMatcher(new PathPatternRouteMatcher());
        handler.setRSocketStrategies(socketStrategies());
        return handler;
    }

    @Bean
    RSocketStrategies socketStrategies() {
        RSocketStrategies.builder().build();
        return RSocketStrategies.builder()
                .encoder(new Jackson2JsonEncoder())
                .decoder(new Jackson2CborDecoder(), new Jackson2JsonDecoder())
                .build();
    }

//    @Bean
//    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//        return httpSecurity
//                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
//                        .anyExchange().permitAll())
//                .build();
//    }
//
//    @Bean
//    ReactiveJwtDecoder jwtDecoder() {
//        return ReactiveJwtDecoders
////                .fromOidcIssuerLocation("https://auth.anythingvn.com/auth/realms/Authorization");
//                .fromIssuerLocation("https://auth.anythingvn.com/auth/realms/Authorization");
//    }

//    @Bean
//    RSocketMessageHandler handler(){
//        RSocketMessageHandler handler = new RSocketMessageHandler();
//        handler.
//        return handler;
//    }

//    @Bean
//    PayloadSocketAcceptorInterceptor rsocketInterceptor(RSocketSecurity rsocket) {
//        rsocket
//                .authorizePayload(authorize ->
//                        authorize
//                                .anyRequest().permitAll()
//                                .anyExchange().permitAll()
//                )
//                .jwt(jwtSpec -> {
//                    try {
//                        jwtSpec.authenticationManager(new JwtReactiveAuthenticationManager(jwtDecoder()));
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//        return rsocket.build();
//    }

//    @Bean
//    AuthenticationPayloadInterceptor jwt(ReactiveJwtDecoder decoder, MetadataExtractorBearerTokenConverter bearerTokenConverter,
//                                         UserProfileAuthenticationConverter converter) {
//        JwtReactiveAuthenticationManager manager = new JwtReactiveAuthenticationManager(decoder);
//        manager.setJwtAuthenticationConverter(converter);
//        AuthenticationPayloadInterceptor result = new AuthenticationPayloadInterceptor(manager);
//        result.setAuthenticationConverter(bearerTokenConverter);
//        result.setOrder(PayloadInterceptorOrder.JWT_AUTHENTICATION.getOrder());
//        return result;
//    }
//
//    @Component
//    class MetadataExtractorBearerTokenConverter implements
//            PayloadExchangeAuthenticationConverter {
//
//        private final MetadataExtractor metadataExtractor;
//
//        MetadataExtractorBearerTokenConverter(RSocketMessageHandler handler) {
//            this.metadataExtractor = handler.getMetadataExtractor();
//        }
//
//        @Override
//        public Mono<Authentication> convert(PayloadExchange exchange) {
//            Map<String, Object> data = this.metadataExtractor
//                    .extract(exchange.getPayload(), exchange.getMetadataMimeType());
//            return Mono.justOrEmpty(data.get(BearerTokenMetadata.BEARER_AUTHENTICATION_MIME_TYPE.toString()))
//                    .cast(String.class)
//                    .map(BearerTokenAuthenticationToken::new);
//        }
//    }

}
