package dev.felix.elifoot.Controller.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class LoginResponse {

    private String accesseToken;
    private Long expiresIn;
}
