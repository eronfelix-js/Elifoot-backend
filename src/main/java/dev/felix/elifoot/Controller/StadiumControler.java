package dev.felix.elifoot.Controller;
import dev.felix.elifoot.Controller.Response.StadiumResponse;
import dev.felix.elifoot.Entity.Stadium;
import dev.felix.elifoot.Service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stadiums")
public class StadiumControler {

    @Autowired
    private StadiumService stadiumService;

    @PostMapping
    public ResponseEntity<Stadium> createStadium(@RequestBody Stadium stadium) {
        Stadium newStadium = stadiumService.create(stadium);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStadium);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumResponse> getAllStadiums(Pageable pageable) {
        return stadiumService.findAll(pageable);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable Long id) {
        stadiumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
