package dev.felix.elifoot.Controller;
import dev.felix.elifoot.Controller.Response.StadiumResponse;
import dev.felix.elifoot.Entity.Stadium;
import dev.felix.elifoot.Service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stadiums")
public class StadiumControler {

    @Autowired
    private StadiumService stadiumService;

    @PreAuthorize("hasAnyAuthority('SCOPE_stadium:write', 'SCOPE_admin:all')")
    @PostMapping
    public ResponseEntity<Stadium> createStadium(@RequestBody Stadium stadium) {
        Stadium newStadium = stadiumService.create(stadium);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStadium);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_stadium:read', 'SCOPE_admin:all')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumResponse> getAllStadiums(Pageable pageable) {
        return stadiumService.findAll(pageable);

    }

    @PreAuthorize("hasAnyAuthority('SCOPE_stadium:write', 'SCOPE_admin:all')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable Long id) {
        stadiumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
