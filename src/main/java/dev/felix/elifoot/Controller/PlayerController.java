package dev.felix.elifoot.Controller;

import dev.felix.elifoot.Controller.Request.CreatePlayerRequest;
import dev.felix.elifoot.Controller.Response.PLayerDetailReponse;
import dev.felix.elifoot.Controller.Response.PlayerResponse;
import dev.felix.elifoot.Service.CreatePlayerService;
import dev.felix.elifoot.Service.findPLayerSErvice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final findPLayerSErvice service;
    private final CreatePlayerService createService;

    @GetMapping
    public Page<PlayerResponse> findAll(Pageable pageable) {
        return service.execute(pageable);
    }

    @GetMapping("/{id}")
    public PLayerDetailReponse findById(Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PLayerDetailReponse createPlayer(@Valid @RequestBody CreatePlayerRequest request) {
        return createService.execute(request);
    }


}
