package b.com.rsocker.demo.server.controller;

import b.com.rsocker.demo.server.model.Info;
import b.com.rsocker.demo.server.service.DemoService;
import io.rsocket.core.RSocketConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class RSocketController {

    @Autowired
    DemoService demoService;

    @MessageMapping("endpoint-1")
    public Flux<Info> demoRsocket(Info info) {
//        connector.
//        Flux.interval(Duration.ofMillis(1000)).
//        return Mono.just(new Info("aaaa", "bbbb"));
//        return Flux.interval(Duration.ofSeconds(1)).map(idx -> info);
        return demoService.getInfo()
//                .doOnNext(System.out::println)
                .doOnComplete(() -> {
                    System.out.println("endpoint-1 completed!");
                });
    }
}
