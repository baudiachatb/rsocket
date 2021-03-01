package b.com.rsocket.demo.client.coontroller;

import b.com.rsocket.demo.client.Model.Info;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    final RSocketRequester rSocketRequester;

    DemoController(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @RequestMapping("/demo")
    Flux<Info> demo() {


        MimeType authenticationMimeType =
//                MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.getString());
                MimeTypeUtils.parseMimeType("message/x.rsocket.authentication.v0");
//        BearerTokenMetadata token = new BearerTokenMetadata("...");
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICItTFhaTUo1a25zc1I4Ql9uX25WNjNnMFQ3SEVWdXpycmxvakFOVVFEeGlZIn0.eyJleHAiOjE2MTMyMzY4OTYsImlhdCI6MTYxMzIzNjU5NiwiYXV0aF90aW1lIjoxNjEzMjM2NTkyLCJqdGkiOiIzYzQwM2NjMi0xZjgyLTRiZTAtODBjNS04MzNlNmNhNTJlZjkiLCJpc3MiOiJodHRwczovL2F1dGguYW55dGhpbmd2bi5jb20vYXV0aC9yZWFsbXMvQXV0aG9yaXphdGlvbiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2NDFmMDRhMy1iZjllLTQzNTMtYmUwNS0xMWI0OWUxZWM4MTgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vMSIsInNlc3Npb25fc3RhdGUiOiIyMzJiMmY2Mi1kOTg2LTRjMjEtODIwZS1mYmIwM2I1Mjg2M2EiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6NDIwMCIsImh0dHA6Ly9sb2NhbGhvc3Q6ODEwMCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJiYXUgbmd1eWVuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiYmF1IiwiZmFtaWx5X25hbWUiOiJuZ3V5ZW4iLCJlbWFpbCI6InVzZXIxQGdtYWlsLmNvbSJ9.KmUdAn65qAh9_HcRJMUz9PiQLewuhhmWYA51hJ_maR3z1Ujke5RwrpRZuFSLiYN4Tv7H_xwIgDPxvDV5g8acIabJDd0pzpAqEUTrwIXZ6kNat68oThIZDNPFBRhc-BOAIADjrELDKrnya94hOcI3N8RG55Gg8kVjFFMWjYoXoSfg4U2nz06BxgQBO5yZlCPsLcwiLDWu8onp0rGqtaqg-d75hTLhSNKh5XyemwSChBFhShX_aJTQnuv5y__jvWXRT3wuH-t2wo2vUnKVMe8iheIuCjj4m2aXDCbNSGF12zJk2d8EU28sFOERa84Yz8ETD4DJEG7DZpZ7LIBwV5ONtQ";
        return rSocketRequester.route("endpoint-1")

//                .metadata(token, )
                .data(new Info("ccc", "ddd"))
                .retrieveFlux(Info.class)
                .map(res -> {
                    System.out.println(res);
                    return res;
                });
    }
}
