package dev.felix.elifoot.Service;

import dev.felix.elifoot.Controller.Request.ClubCreateRequest;
import dev.felix.elifoot.Controller.Response.ClubDetailResponse;
import dev.felix.elifoot.Controller.Response.ClubResponse;
import dev.felix.elifoot.Mapper.ClubMapper;
import dev.felix.elifoot.REpository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateClubService {

    private final ClubMapper mapper;
    private final ClubRepository repository;
    private final StadiumService service;

    public ClubDetailResponse execute(ClubCreateRequest club){
        var clubEntity = mapper.toEntity(club);
        if (Objects.nonNull(clubEntity.getStadium())) {
            clubEntity.setStadium(service.findById(clubEntity.getStadium().getId()));
        }
        var savedClub = repository.save(clubEntity);
        return mapper.toDetailResponse(savedClub);
    }

}
