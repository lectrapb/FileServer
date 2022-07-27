package com.app.back.infraestructure.entrypoints;

import com.app.back.domain.model.user.User;
import com.app.back.domain.model.user.gateways.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Mono<User> saveUser(@RequestBody Mono<User> userMono){
        return userService.saveUser(userMono);
    }
}
