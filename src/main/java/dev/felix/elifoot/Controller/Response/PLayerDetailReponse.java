package dev.felix.elifoot.Controller.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PLayerDetailReponse {

    private long id;
    private String name;
    private String position;
    private int shirtNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String urlImg;
    private ClubResponse club;
}
