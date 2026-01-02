package dev.felix.elifoot.Service;

import dev.felix.elifoot.Controller.Request.CreateUserRequest;
import dev.felix.elifoot.Controller.Response.UserResponse;
import dev.felix.elifoot.Entity.Scope;
import dev.felix.elifoot.Entity.User;
import dev.felix.elifoot.Mapper.UserMapper;
import dev.felix.elifoot.REpository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class createUserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final findScopeService scopeService;
    private final PasswordEncoder encoder;

    public UserResponse execute(CreateUserRequest request){
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + request.getEmail());
        }

        List<Scope> scope = request.getScopes().stream()
                .map(scopeService::findById)
                .toList();

        User entity = mapper.toEntity(request);
        entity.setPassword(encoder.encode(entity.getPassword()));
        User save = repository.save(entity);
        return mapper.toResponse(save);
    }
}
