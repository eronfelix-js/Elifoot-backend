package dev.felix.elifoot.Controller.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubCreateRequest {

    private String name;
    private String founded;
    private String urlImg;
    private Long stadiumId;
}
