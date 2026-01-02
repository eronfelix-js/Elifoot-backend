package dev.felix.elifoot.Service;

import dev.felix.elifoot.Entity.Scope;
import dev.felix.elifoot.REpository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class findScopeService {

    private final ScopeRepository repository;

    public Scope findById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Scope nao encontrado" + id));
    }
}
