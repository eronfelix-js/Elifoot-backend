package dev.felix.elifoot.Mapper;

import dev.felix.elifoot.Controller.Request.CreateUserRequest;
import dev.felix.elifoot.Controller.Response.UserResponse;
import dev.felix.elifoot.Entity.Scope;
import dev.felix.elifoot.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopes" )
    User toEntity(CreateUserRequest createUserRequest);

    @Mapping(target = "scopes", source = "scopes",qualifiedByName = "mapScopesEntityToString" )
    UserResponse toResponse(User user);

    @Named("mapScopesEntityToString")
    default List<String> mapScopesEntityToString(List<Scope> scopes){
        if (scopes == null) {
            return List.of();
        } else {
            return scopes.stream()
                    .map(Scope::getName)
                    .toList();
        }
    }

    @Named("mapScopes")
    default List<Scope> mapScopes(List<Long> scopes){
        if (scopes == null) {
            return List.of();
        } else {
            return scopes.stream()
                    .map(id -> {
                        Scope scope = new Scope();
                        scope.setId(id);
                        return scope;
                    })
                    .toList();

        }
    }
}
