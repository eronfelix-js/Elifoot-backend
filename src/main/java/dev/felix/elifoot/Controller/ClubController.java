package dev.felix.elifoot.Controller;

import dev.felix.elifoot.Controller.Request.ClubCreateRequest;
import dev.felix.elifoot.Controller.Response.ClubDetailResponse;
import dev.felix.elifoot.Controller.Response.ClubResponse;
import dev.felix.elifoot.Service.ClubService;
import dev.felix.elifoot.Service.CreateClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService service;
    private final CreateClubService create;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClubResponse> findAll(Pageable pageable) {
        return service.findALL(pageable);
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable Long id) {
        service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClubDetailResponse create(@RequestBody ClubCreateRequest request) {
       return create.execute(request);
    }

}
