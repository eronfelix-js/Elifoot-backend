package dev.felix.elifoot.Entity;

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
    private String position;

    @Enumerated(EnumType.STRING)
    private Position shirtNumber;
    private String urlImg;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;
}
