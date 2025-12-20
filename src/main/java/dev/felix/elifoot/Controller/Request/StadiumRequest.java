package dev.felix.elifoot.Controller.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StadiumRequest {
    private String name;
    private String city;
    private Integer capacity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String urlImg;
}

