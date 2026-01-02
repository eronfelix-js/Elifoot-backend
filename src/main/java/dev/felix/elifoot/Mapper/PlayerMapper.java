package dev.felix.elifoot.Mapper;

import dev.felix.elifoot.Controller.Request.CreatePlayerRequest;
import dev.felix.elifoot.Controller.Response.PLayerDetailReponse;
import dev.felix.elifoot.Controller.Response.PlayerResponse;
import dev.felix.elifoot.Entity.Player;
import dev.felix.elifoot.Enum.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "position", source = "position", qualifiedByName = "EnumToString")
    PlayerResponse toResponse(Player player);

    PLayerDetailReponse toDetailResponse(Player player);

    @Mapping(target = "club.id", source = "clubId")
    Player toRequest(CreatePlayerRequest player);

    @Named("EnumToString")
    static String positionToString(Position position) {
        return position != null ? position.name() : null;
    }
}
