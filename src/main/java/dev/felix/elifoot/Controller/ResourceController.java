package dev.felix.elifoot.Controller;

import dev.felix.elifoot.Controller.Response.PositionResponse;
import dev.felix.elifoot.Enum.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    @GetMapping("/positions")
    @ResponseStatus(HttpStatus.OK)
    public void getPositions() {
        Arrays.stream(Position.values())
                .map(position -> new PositionResponse(position.name(), position.getLabel()));
    }
}
