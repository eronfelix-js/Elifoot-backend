package dev.felix.elifoot.Service;

import dev.felix.elifoot.Controller.Response.PLayerDetailReponse;
import dev.felix.elifoot.Controller.Response.PlayerResponse;
import dev.felix.elifoot.Mapper.PlayerMapper;
import dev.felix.elifoot.REpository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class findPLayerSErvice {

    private final PlayerRepository repository;
    private final PlayerMapper mapper;

    public Page<PlayerResponse> execute(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public PLayerDetailReponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDetailResponse)
                .orElseThrow(()-> new RuntimeException("Player not found"));
    }

    public List<PLayerDetailReponse> findPlayersByClubId(Long clubId){
        return repository.findByClubId(clubId)
                .stream()
                .map(mapper::toDetailResponse)
                .toList();
    }


}
