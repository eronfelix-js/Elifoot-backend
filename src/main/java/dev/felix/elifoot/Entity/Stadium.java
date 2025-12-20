package dev.felix.elifoot.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stadium_seq_gen")
    @SequenceGenerator(name = "stadium_seq_gen", sequenceName = "stadium_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String city;
    private Integer capacity;
    private String urlImg;
    @OneToOne(mappedBy = "stadium")
    private Club club;
}
