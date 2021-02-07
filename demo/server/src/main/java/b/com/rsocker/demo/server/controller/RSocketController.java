package b.com.rsocker.demo.server.controller;

import b.com.rsocker.demo.server.model.Info;
import io.rsocket.core.RSocketConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class RSocketController {

    @MessageMapping("endpoint-1")
    public Mono<Info> demoRsocket() {
//        connector.
//        Flux.interval(Duration.ofMillis(1000)).
        return Mono.just(new Info("aaaa", "bbbb"));
    }
}
