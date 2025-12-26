package dev.felix.elifoot.Controller.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponse {

    private Long id;
    private String name;
    private String position;
    private Integer number;
    private Long clubId;
}
