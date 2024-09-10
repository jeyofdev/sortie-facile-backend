package com.poec.sortie_facile_backend.auth_user;

import com.poec.sortie_facile_backend.core.interfaces.IAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserService implements IAuthUserService {
    private final AuthUserRepository authUserRepository;

    @Override
    public List<AuthUser> findAll() throws AccessDeniedException {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if(roles.equals("[ROLE_ADMIN]")) {
            return authUserRepository.findAll();
        } else {
            throw new AccessDeniedException("User does not have the correct rights to access to this resource");
        }
    }

    @Override
    public AuthUser findUserByEmail(String email) {
        String username  = SecurityContextHolder.getContext().getAuthentication().getName();
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (username.equals(email) || roles.equals("[ROLE_ADMIN]")) {
            return authUserRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("email " + email +" not found"));
        } else {
            throw new AccessDeniedException("User does not have the correct rights to access to this resource");
        }
    }

    @Override
    public AuthUserDTO findUserById(Long id) {
        AuthUser user = authUserRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );

        return AuthUserDTO.mapFromEntity(user);
    }
}
