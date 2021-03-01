package b.com.rsocket.demo.client.config;

import io.rsocket.RSocket;
import io.rsocket.core.Resume;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.metadata.WellKnownMimeType;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
//import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;
//import org.springframework.security.rsocket.metadata.BearerTokenMetadata;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.util.retry.Retry;

import java.net.URI;
import java.time.Duration;

@Configuration
public class RsocketConfig {

    RSocketStrategies strategies = RSocketStrategies.builder()
            .encoders(encoders -> encoders.add(new Jackson2JsonEncoder()))
            .decoders(decoders -> decoders.add(new Jackson2JsonDecoder()))
            .build();


    @Bean
    RSocketRequester rSocketRequester() {
        MimeType authenticationMimeType =
                MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.getString());
//                MimeTypeUtils.parseMimeType("message/x.rsocket.authentication.v0");
//        BearerTokenMetadata token = new BearerTokenMetadata("...");
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICItTFhaTUo1a25zc1I4Ql9uX25WNjNnMFQ3SEVWdXpycmxvakFOVVFEeGlZIn0.eyJleHAiOjE2MTMyMzE4OTAsImlhdCI6MTYxMzIzMTU5MCwiYXV0aF90aW1lIjoxNjEzMjMxNTg2LCJqdGkiOiI2ZjU0MGVkZC0xN2FiLTQ2NjctODI0OC04ZWJjZTU4YjMyNWMiLCJpc3MiOiJodHRwczovL2F1dGguYW55dGhpbmd2bi5jb20vYXV0aC9yZWFsbXMvQXV0aG9yaXphdGlvbiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2NDFmMDRhMy1iZjllLTQzNTMtYmUwNS0xMWI0OWUxZWM4MTgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vMSIsInNlc3Npb25fc3RhdGUiOiJkYjdhMzFhZS1lY2M1LTRlNjYtYTMyYS01OTJjZTcyYTA2MjgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6NDIwMCIsImh0dHA6Ly9sb2NhbGhvc3Q6ODEwMCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJiYXUgbmd1eWVuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiYmF1IiwiZmFtaWx5X25hbWUiOiJuZ3V5ZW4iLCJlbWFpbCI6InVzZXIxQGdtYWlsLmNvbSJ9.dl484LO52P_M8YVSP9eZsAfRWQDFRoMsU9N0AeTqfv3c7TTzxulLznirzRR5djeM3cm7lB0E7mJbGkCKJWbEHLEgIlJYinIESYfD1w35rLBa8Hk56vJxdCaERMJDTjO4gI-ojk0dO3lqScY897rYLaKx1wiQrHQrs2yLkxRtTWV0pQoMHVoHZiWZXNvemC5RXjqi6QoLcKqdDtWIAU2f7X5T6GYF3Lg5n_sGl4qJb8kPlRg_0_foMDA8EgcXmlJZY7seubetf1tT2XDuF53MySzRNNrUtd_DEy6FVgD121lgFrVKjFtoO5ILArHcEYCqZLDmy_JwhW7mlGUllBkGyA";

        URI url = URI.create("http://localhost:5000/test");
        return RSocketRequester.builder()
                .setupData(MimeTypeUtils.parseMimeType(WellKnownMimeType.APPLICATION_JSON.getString()))
                .setupMetadata(token, authenticationMimeType)
                .rsocketConnector(connector -> connector.reconnect(Retry.fixedDelay(Integer.MAX_VALUE, Duration.ofSeconds(1))))
//                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .rsocketStrategies(strategies)
//                .tcp("172.17.0.2", 5000);
                .websocket(url);
    }
}
