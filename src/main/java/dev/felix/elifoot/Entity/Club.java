package dev.felix.elifoot.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_seq")
    @SequenceGenerator(name = "club_seq", sequenceName = "club_seq", allocationSize = 1)
    private Long id;
    private String name;
    private LocalDate founded;
    private String urlImg;
    private LocalDateTime createdAt;
    private Boolean active;
    @OneToOne
    @JoinColumn(name = "stadium_id", unique = true)
    private Stadium stadium;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Player> players;
}
