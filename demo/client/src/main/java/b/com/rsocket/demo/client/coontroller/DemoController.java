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
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICItTFhaTUo1a25zc1I4Ql9uX25WNjNnMFQ3SEVWdXpycmxvakFOVVFEeGlZIn0.eyJleHAiOjE2MTMwNjQwNzEsImlhdCI6MTYxMzA2Mzc3MSwiYXV0aF90aW1lIjoxNjEzMDYzNzY5LCJqdGkiOiIxMmJhYzhhZi00ZDRlLTQxZmItYTAzYi04ZThkYTlkNWRkYmUiLCJpc3MiOiJodHRwczovL2F1dGguYW55dGhpbmd2bi5jb20vYXV0aC9yZWFsbXMvQXV0aG9yaXphdGlvbiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2NDFmMDRhMy1iZjllLTQzNTMtYmUwNS0xMWI0OWUxZWM4MTgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vMSIsInNlc3Npb25fc3RhdGUiOiI4ZTNiMGFhNy0xODVlLTQxMTctOTFmZS1mNjcwOGQ4ZTlhYTQiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6NDIwMCIsImh0dHA6Ly9sb2NhbGhvc3Q6ODEwMCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJiYXUgbmd1eWVuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiYmF1IiwiZmFtaWx5X25hbWUiOiJuZ3V5ZW4iLCJlbWFpbCI6InVzZXIxQGdtYWlsLmNvbSJ9.bnq1yEhlpDYtGn9i-s143nW7GPjtyuoPFWVAEo5STXgzdSiVPAkx5w27cd1ciLGRzUSsX9qWgBigdxW-RAOMno3c5sxMvXzUA4vFCGWs6vp7xRpU81k1a9TAxpAXZFzg-kXCE0-CzrK9NI8XJsyzBpTc-WwUWaWyzqfBlnUXjwnnTXWTMD2LXIg_q-KWLoe6Atunwr_jUVh12Ue6fB_GCFyPoKlKzjm-58uGll1UZFVvlEazIBsY3KFeqvT2Dcc3Y7LJlGBi5zD01Fty8-70XoUssIxOWErOMLvvrp7LCX0_ptAu8L-_cAUSjHqigzrlI-JOnVr7yXBC04iVntMBqQ";
        return rSocketRequester.route("endpoint-1")

                .metadata(token, authenticationMimeType)
                .data(new Info("ccc", "ddd"))
                .retrieveFlux(Info.class);
    }
}
