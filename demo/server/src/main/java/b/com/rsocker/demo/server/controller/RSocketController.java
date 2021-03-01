package b.com.rsocker.demo.server.controller;

import b.com.rsocker.demo.server.model.Info;
import b.com.rsocker.demo.server.service.DemoService;
import io.rsocket.core.RSocketConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
//import org.springframework.security.rsocket.api.PayloadExchange;
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
//        System.out.println(payload);
        return demoService.getInfo()
                .doOnNext(System.out::println)
                .doOnComplete(() -> {
                    System.out.println("endpoint-1 completed!");
                });
    }

    @MessageMapping("resquest-response")
    public Mono<String> requestResponse() {
        return Mono.just("request response tested!");
    }

    @MessageMapping("request-stream")
    public Flux<Info> requestStream() {
        return Flux.interval(Duration.ofSeconds(1)).map(idx -> {
            System.out.println(idx);
            return new Info(idx.toString(), "aaaaa");
        }).take(10);
    }

    @MessageMapping("request-channel")
    public Flux<Info> requestChannel(Flux<Info> input) {
        input.subscribe(System.out::println);
        return demoService.getInfo();
    }


}
