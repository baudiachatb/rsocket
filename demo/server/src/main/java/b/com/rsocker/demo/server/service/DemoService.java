package b.com.rsocker.demo.server.service;

import b.com.rsocker.demo.server.model.Info;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;


@Service
public class DemoService {


    final private Flux<Info> demoFlux = Flux.push(sink -> {
        generate().doOnNext(sink::next).publish().connect();
    });

    public Flux<Info> getInfo() {
        return demoFlux;
    }

    public Flux<Info> generate() {
        return Flux.interval(Duration.ofSeconds(1)).map(res -> new Info("vvvv", "bbb")).take(5);
    }
}
