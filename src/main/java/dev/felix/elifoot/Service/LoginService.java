package dev.felix.elifoot.Service;

import com.nimbusds.jwt.JWTClaimsSet;
import dev.felix.elifoot.Controller.Request.LoginRequest;
import dev.felix.elifoot.Controller.Response.LoginResponse;
import dev.felix.elifoot.Entity.Scope;
import dev.felix.elifoot.Entity.User;
import dev.felix.elifoot.REpository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponse login(LoginRequest request){
        Optional<User> email = repository.findByEmail(request.getEmail());
        if (email.isEmpty() || isPasswordCorrect(request.getPassword(), email.get().getPassword())) {
            throw new BadCredentialsException("usuario ou senha invalidos");
        }
        User user = email.get();
        List<String> scopes = user.getScopes().stream()
                .map(Scope::getName)
                .toList();
        long expirationTime = 3600L; // 1 hora
        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("Elifoot")
                .subject(user.getEmail())
                .expiresAt(Instant.now().plusSeconds(expirationTime))
                .issuedAt(Instant.now())
                .claim("scopes", scopes)
                .claim("email", user.getEmail())
                .build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();
        return LoginResponse.builder()
                .accesseToken(token)
                .expiresIn(expirationTime)
                .build();
    }

    public Boolean isPasswordCorrect(String Password, String savedPassword) {
        return passwordEncoder.matches(Password, savedPassword);
    }

}
