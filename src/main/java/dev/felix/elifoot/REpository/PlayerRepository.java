package dev.felix.elifoot.REpository;

import dev.felix.elifoot.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByClubId(Long clubId);
}
