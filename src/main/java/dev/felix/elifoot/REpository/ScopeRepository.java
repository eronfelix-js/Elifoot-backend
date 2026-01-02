package dev.felix.elifoot.REpository;

import dev.felix.elifoot.Entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends JpaRepository<Scope , Long> {

}
