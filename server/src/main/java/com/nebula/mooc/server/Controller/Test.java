/**
 * Test class
 *
 * @author Zhanghh
 * @date 2019/3/29
 */
package com.nebula.mooc.server.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Test {

    @GetMapping("/hello")
    public Mono<String> test() {
        return Mono.just("Hello");
    }
}
