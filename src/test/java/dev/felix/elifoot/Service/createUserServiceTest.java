package dev.felix.elifoot.Service;

import dev.felix.elifoot.Controller.Request.CreateUserRequest;
import dev.felix.elifoot.Exception.ResourceAlreadyExists;
import dev.felix.elifoot.Exception.ResourceNotFoundException;
import dev.felix.elifoot.Mapper.UserMapper;
import dev.felix.elifoot.REpository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class createUserServiceTest {

    @InjectMocks
    createUserService createUserService;

    @Mock
    UserMapper mapper;
    @Mock
    UserRepository repository;
    @Mock
    findScopeService scopeService;
    @Mock
    PasswordEncoder encoder;



    @Test
    void testaSeAExcessaoDeUsuarioComEmailExistenteELancada() {

        //Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .email("teste@gmail.com")
                .name("teste")
                .password("123456")
                .scopes(List.of(1L, 2L))
                .build();

        Mockito.when(repository.existsByEmail(request.getEmail())).thenReturn(true);
        ResourceNotFoundException resourceAlreadyExists = assertThrows(ResourceNotFoundException.class, () -> {
            createUserService.execute(request);
        });
        assertEquals(resourceAlreadyExists.getMessage(), "Email already in use: " + request.getEmail());



    }

}