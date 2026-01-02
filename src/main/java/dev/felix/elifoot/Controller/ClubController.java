package dev.felix.elifoot.Controller;

import dev.felix.elifoot.Controller.Request.ClubCreateRequest;
import dev.felix.elifoot.Controller.Response.ClubDetailResponse;
import dev.felix.elifoot.Controller.Response.ClubResponse;
import dev.felix.elifoot.Service.ClubService;
import dev.felix.elifoot.Service.CreateClubService;
import dev.felix.elifoot.Service.findPLayerSErvice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService service;
    private final CreateClubService create;
    private final findPLayerSErvice findPLayerSErvice;

    @PreAuthorize("hasAnyAuthority('SCOPE_club:read', 'SCOPE_admin:all')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClubResponse> findAll(Pageable pageable) {
        return service.findALL(pageable);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_club:read', 'SCOPE_admin:all')")
    @GetMapping("/{id}")
    public void findById(@PathVariable Long id) {
        service.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_club:write', 'SCOPE_admin:all')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClubDetailResponse create(@RequestBody ClubCreateRequest request) {
       return create.execute(request);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_club:read', 'SCOPE_admin:all')")
    @GetMapping("/{id}/players")
    @ResponseStatus(HttpStatus.OK)
    public void findPlayersByClubId(@PathVariable Long id) {
        findPLayerSErvice.findPlayersByClubId(id);
    }

}
