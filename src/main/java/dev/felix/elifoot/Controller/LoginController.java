package dev.felix.elifoot.Controller;

import dev.felix.elifoot.Controller.Request.LoginRequest;
import dev.felix.elifoot.Controller.Response.LoginResponse;
import dev.felix.elifoot.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public LoginResponse login(LoginRequest request){
        return service.login(request);
    }
}
