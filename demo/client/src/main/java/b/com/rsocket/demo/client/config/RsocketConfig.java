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
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICItTFhaTUo1a25zc1I4Ql9uX25WNjNnMFQ3SEVWdXpycmxvakFOVVFEeGlZIn0.eyJleHAiOjE2MTMwNzAzMjUsImlhdCI6MTYxMzA3MDAyNSwiYXV0aF90aW1lIjoxNjEzMDY4NTgzLCJqdGkiOiI3MDA5YzAwMS0wMmZhLTQ0NjAtOTg1Zi01OGIwMThjYzA2NzUiLCJpc3MiOiJodHRwczovL2F1dGguYW55dGhpbmd2bi5jb20vYXV0aC9yZWFsbXMvQXV0aG9yaXphdGlvbiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2NDFmMDRhMy1iZjllLTQzNTMtYmUwNS0xMWI0OWUxZWM4MTgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vMSIsInNlc3Npb25fc3RhdGUiOiIzYzYyYWQ5Ni0xNWU3LTRhZTItYjkyNS04ODAwZmFjZGIyNmQiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6NDIwMCIsImh0dHA6Ly9sb2NhbGhvc3Q6ODEwMCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJiYXUgbmd1eWVuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiYmF1IiwiZmFtaWx5X25hbWUiOiJuZ3V5ZW4iLCJlbWFpbCI6InVzZXIxQGdtYWlsLmNvbSJ9.dxNk6RDBwF8nZnGImF-JH75Qq8VMJNIuZIZNp-liUAl3Vjwn1ZcCpxaPQj8C65ZLcNsmTOx8ahvQ2sXPf0tgiYMDzO9sWGTA_Nlkrok0DJmbScGFw5zW5XvCTY3gqa6-XXqF9A3-QS_bTdz_56_j22dUAyNftEWYT-M3GRW6lx4LpzYyhu1OEa8-cIoDa_W9sn3DmNEb6Ke4tTVtfGVA6c5mSLXRfcoEN8N1FWQ33ppVMRm97qB8Klb-XLpDTmExo28kLtuAinsY9Fn4TTZx8W6Bboo6SteVY_tRSgLyr2MFtzEGXZ4jRsHdmF2mIecvu7D5FP2eU4RW6di7rJq-hg";

        URI url = URI.create("http://localhost:5000/test");
        return RSocketRequester.builder()
                .setupMetadata(token, authenticationMimeType)
                .rsocketConnector(connector -> connector.reconnect(Retry.fixedDelay(Integer.MAX_VALUE, Duration.ofSeconds(1))))
//                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .rsocketStrategies(strategies)
//                .tcp("172.17.0.2", 5000);
                .websocket(url);
    }
}
