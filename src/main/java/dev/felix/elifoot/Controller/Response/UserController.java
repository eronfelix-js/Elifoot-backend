package dev.felix.elifoot.Controller.Response;

import dev.felix.elifoot.Controller.Request.CreateUserRequest;
import dev.felix.elifoot.Service.findScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final findScopeService service;

    public UserResponse create(CreateUserRequest request){
        return
    }
}
