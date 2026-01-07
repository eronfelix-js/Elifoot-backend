package dev.felix.elifoot.Controller.Request;

import dev.felix.elifoot.Entity.Club;
import dev.felix.elifoot.Entity.Stadium;
import dev.felix.elifoot.Mapper.ClubMapper;
import dev.felix.elifoot.REpository.ClubRepository;
import dev.felix.elifoot.Service.CreateClubService;
import dev.felix.elifoot.Service.StadiumService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClubCreateRequestTest {

    @InjectMocks
    CreateClubService createClubService;

    @Mock
    ClubMapper mapper;
    @Mock
    ClubRepository repository;
    @Mock
    StadiumService service;

    @Captor
    ArgumentCaptor<Club> clubArgumentCaptor;

    @Test
    @DisplayName("Deve criar um clube corretamente")
    void testExecute() {
        //Arrange
        ClubCreateRequest request = new ClubCreateRequest();
        request.setName("FC Test");
        request.setFounded("Test founded");
        request.setStadiumId(1L);
        request.setUrlImg("http://test.com/logo.png");

        Club clubEntity = Club.builder()
                .name("FC Test")
                .founded(null)
                .urlImg("http://test.com/logo.png")
                .stadium(Stadium.builder().id(10l).build())
                .build();

        Stadium stadium = Stadium.builder()
                .id(10l)
                .name("Test Stadium")
                .capacity(1000)
                .city("Test City")
                .build();

        Mockito.when(mapper.toEntity(request)).thenReturn(clubEntity);
        Mockito.when(service.findById(stadium.getId())).thenReturn(stadium);
        //Act
        createClubService.execute(request);
        //Assert
        Mockito.verify(repository).save(clubArgumentCaptor.capture());
        ArgumentCaptor savedClub = clubArgumentCaptor;
        Assertions.assertNotNull(savedClub);



    }

}