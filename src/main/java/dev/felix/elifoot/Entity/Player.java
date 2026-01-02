package dev.felix.elifoot.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.felix.elifoot.Enum.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq_gen")
    @SequenceGenerator(name = "player_seq_gen", sequenceName = "player_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    private int shirtNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String urlImg;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

}
