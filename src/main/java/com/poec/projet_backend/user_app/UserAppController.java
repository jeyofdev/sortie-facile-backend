package com.poec.projet_backend.user_app;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.activity.ActivityDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAppController {

    private final UserAppRepository userAppRepository;

    @GetMapping("/email/{email}")
    public ResponseEntity<UserApp> getUserByEmail(@PathVariable String email, HttpServletRequest request) throws AccessDeniedException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (username.equals(email) || roles.equals("[ROLE_ADMIN]")) {
            return ResponseEntity.ok(userAppRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("email " + email + " not found"))
            );
        } else {
            throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");
        }
    }

    @GetMapping("/all")
    public List<UserApp> getAll(HttpServletRequest request) throws AccessDeniedException {
        String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (roles.equals("[ROLE_ADMIN]")) {
            return userAppRepository.findAll();
        } else {
            throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");

        }
    }

    @GetMapping(ID)
    public UserAppDTO getById(@PathVariable Long id) {
        UserApp user = userAppRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
        return UserAppDTO.mapFromEntity(user);
        
        // String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        //if (roles.equals("[ROLE_USER]") || roles.equals("[ROLE_ADMIN]")) {
        //    return userAppRepository.findAll();
        //   } else {
        //     throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");

        // }
    }
}
