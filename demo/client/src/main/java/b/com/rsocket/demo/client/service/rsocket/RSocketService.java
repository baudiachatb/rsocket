package b.com.rsocket.demo.client.service.rsocket;

import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;

//@Service
public class RSocketService {

    private Mono<RSocketRequester> requester;
    private RSocketStrategies strategies = RSocketStrategies.builder()
            .encoders(encoders -> encoders.add(new Jackson2JsonEncoder()))
            .decoders(decoders -> decoders.add(new Jackson2JsonDecoder()))
            .build();

    RSocketService(RSocketRequester.Builder builder) {
        URI uri = URI.create("http://localhost:5000/test");
        this.requester = builder.connectWebSocket(uri).retry(5).cache();
    }
}
