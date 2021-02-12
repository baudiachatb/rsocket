package b.com.rsocker.demo.server.controller;

import b.com.rsocker.demo.server.model.Info;
import b.com.rsocker.demo.server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RestControllerDemo {

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
}
