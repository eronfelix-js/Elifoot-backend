package dev.felix.elifoot.Mapper;

import dev.felix.elifoot.Controller.Request.StadiumRequest;
import dev.felix.elifoot.Controller.Response.StadiumResponse;
import dev.felix.elifoot.Entity.Stadium;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StadiumMapper {
    StadiumResponse toResponse(Stadium stadium);
    StadiumRequest toRequest(Stadium stadium);
}