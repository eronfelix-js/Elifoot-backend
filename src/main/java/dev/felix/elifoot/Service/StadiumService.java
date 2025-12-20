package dev.felix.elifoot.Service;

import dev.felix.elifoot.Controller.Response.StadiumResponse;
import dev.felix.elifoot.Entity.Stadium;
import dev.felix.elifoot.Mapper.StadiumMapper;
import dev.felix.elifoot.REpository.StadiumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StadiumService {


    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;

    public StadiumService(StadiumRepository stadiumRepository, StadiumMapper stadiumMapper) {
        this.stadiumRepository = stadiumRepository;
        this.stadiumMapper = stadiumMapper;
    }

    public Stadium create(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    public Stadium findById(Long id) {
        return stadiumRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Stadium not found"));
    }

    public Page<StadiumResponse> findAll(Pageable pageable) {
        return stadiumRepository.findAll(pageable)
                .map(stadium -> stadiumMapper.toResponse(stadium));
    }

    public void delete(Long id) {
        if (stadiumRepository.existsById(id)) {
            stadiumRepository.deleteById(id);
        }
    }

}
