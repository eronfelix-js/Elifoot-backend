package dev.felix.elifoot.Service;

import dev.felix.elifoot.Controller.Request.CreatePlayerRequest;
import dev.felix.elifoot.Controller.Response.PLayerDetailReponse;
import dev.felix.elifoot.Mapper.PlayerMapper;
import dev.felix.elifoot.REpository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePlayerService {

    private final PlayerRepository repository;
    private final PlayerMapper mapper;
    private final ClubService clubService;


    public PLayerDetailReponse execute(CreatePlayerRequest request) {
        var newPlayer = mapper.toRequest(request);
        //newPlayer.setClub(clubService.findById(request.getClubId()));
        var savedPlayer = repository.save(newPlayer);
        return mapper.toDetailResponse(savedPlayer);
    }

}
