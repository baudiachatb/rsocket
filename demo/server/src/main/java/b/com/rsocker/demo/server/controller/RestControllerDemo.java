package b.com.rsocker.demo.server.controller;

import b.com.rsocker.demo.server.model.Info;
import b.com.rsocker.demo.server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class RestControllerDemo {

    private static final Map<String, RSocketRequester> REQUESTER_MAP = new HashMap<>();

    @Autowired
    DemoService demoService;


    @RequestMapping("demo1")
    Flux<Info> test() {
        return demoService.generate()
                .doOnNext(System.out::println)
                .doOnComplete(() -> {
                    System.out.println("completed!");
                });
    }

//    @ConnectMapping("client-id")
//    public Mono<Void> connect(RSocketRequester requester, String clientId) {
//        System.out.println(requester);
//        System.out.println(clientId);
//        return Mono.empty();
//    }

    @ConnectMapping("client-id")
    void onConnect(RSocketRequester rSocketRequester, String clientId) {
        System.out.println(clientId);
        rSocketRequester.rsocket()
                .onClose()
                .subscribe(null, null,
                        () -> REQUESTER_MAP.remove(clientId, rSocketRequester));
        REQUESTER_MAP.put(clientId, rSocketRequester);
    }

    @RequestMapping("test-rsocket")
    void testRsocket() {
        REQUESTER_MAP.keySet().forEach(clientId -> {
            RSocketRequester requester = REQUESTER_MAP.get(clientId);

            ArrayList<Info> aaa = new ArrayList<>();
            aaa.add(new Info("aaaa", "bbb"));
            aaa.add(new Info("aaaa", "bbb"));
            aaa.add(new Info("aaaa", "bbb"));

            requester.route("aaaaa")
                    .data(new Info("test", "request stream"))
                    .retrieveFlux(Object.class).subscribe(System.out::println);
        });
    }

}
