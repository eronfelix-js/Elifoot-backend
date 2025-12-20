package dev.felix.elifoot.Mapper;

import dev.felix.elifoot.Controller.Request.ClubCreateRequest;
import dev.felix.elifoot.Controller.Response.ClubDetailResponse;
import dev.felix.elifoot.Controller.Response.ClubResponse;
import dev.felix.elifoot.Entity.Club;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    ClubResponse toResponse(Club club);
    ClubDetailResponse toDetailResponse(Club club);

    @Mapping(target = "stadium.id", source = "stadiumId")
    Club toEntity(ClubCreateRequest clubCreateRequest);
}
