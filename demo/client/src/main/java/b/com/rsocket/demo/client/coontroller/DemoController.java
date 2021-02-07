package b.com.rsocket.demo.client.coontroller;

import b.com.rsocket.demo.client.Model.Info;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {
    final RSocketRequester rSocketRequester;

    DemoController(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @RequestMapping("/demo")
    Mono<Info> demo() {
        return rSocketRequester.route("endpoint-1")
                .metadata("aaaaaa", MimeTypeUtils.APPLICATION_JSON)
                .data(new Info("ccc", "ddd"))
                .retrieveMono(Info.class);
    }
}
