package dev.felix.elifoot.Controller.Response;

import dev.felix.elifoot.Controller.Request.CreateUserRequest;
import dev.felix.elifoot.Service.createUserService;
import dev.felix.elifoot.Service.findScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final findScopeService service;
    private final createUserService create;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(CreateUserRequest request){
        return create.execute(request);
    }
}
