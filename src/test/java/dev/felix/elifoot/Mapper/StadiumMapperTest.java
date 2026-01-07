package dev.felix.elifoot.Mapper;

import dev.felix.elifoot.Controller.Request.StadiumRequest;
import dev.felix.elifoot.Controller.Response.StadiumResponse;
import dev.felix.elifoot.Entity.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class StadiumMapperTest {

    private final StadiumMapper mapper = Mappers.getMapper(StadiumMapper.class);

    @Test
    @DisplayName("Deve converter Stadium para StadiumResponse")
    void toResponse() {
        //Arrange
        Stadium stadium = Stadium.builder()
                .id(1l)
                .capacity(1000)
                .name("neo quimica arena")
                .city("são paulo")
                .urlImg("img.com")
                .build();
        //Act
        StadiumResponse response = mapper.toResponse(stadium);
        //Assert
        Assertions.assertEquals(stadium.getId(), response.getId());
        Assertions.assertEquals(stadium.getName(), response.getName());
        Assertions.assertEquals(stadium.getCity(), response.getCity());
        Assertions.assertEquals(stadium.getCapacity(), response.getCapacity());
        Assertions.assertEquals(stadium.getUrlImg(), response.getUrlImg());
    }

}