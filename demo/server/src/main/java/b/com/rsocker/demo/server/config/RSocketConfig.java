package b.com.rsocker.demo.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

@Configuration
public class RSocketConfig {
//    @Bean
//    public RSocketMessageHandler rsocketMessageHandler() {
//        RSocketMessageHandler handler = new RSocketMessageHandler();
//        handler.routeMatcher(new PathPatternRouteMatcher());
//        return handler;
//    }
}
