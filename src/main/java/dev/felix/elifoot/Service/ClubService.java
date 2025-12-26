package dev.felix.elifoot.Service;

import dev.felix.elifoot.Controller.Request.ClubCreateRequest;
import dev.felix.elifoot.Controller.Response.ClubDetailResponse;
import dev.felix.elifoot.Controller.Response.ClubResponse;
import dev.felix.elifoot.Controller.Response.PLayerDetailReponse;
import dev.felix.elifoot.Controller.Response.PlayerResponse;
import dev.felix.elifoot.Mapper.ClubMapper;
import dev.felix.elifoot.REpository.ClubRepository;
import dev.felix.elifoot.REpository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubMapper mapper;
    private final ClubRepository repository;

    public Page<ClubResponse> findALL(Pageable page){
        return repository.findAll(page)
                .map(mapper::toResponse);
    }

    public ClubDetailResponse findById(Long id){
        return repository.findById(id)
                .map(mapper::toDetailResponse)
                .orElseThrow(()-> new RuntimeException("Club not found"));
    }



}
