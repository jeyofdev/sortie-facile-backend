package com.poec.sortie_facile_backend.auth_user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poec.sortie_facile_backend.util.Route.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserRepository authUserRepository;
    private final AuthUserService authUserService;

    @GetMapping("/all")
    public ResponseEntity<List<AuthUser>> getAll(HttpServletRequest request) throws AccessDeniedException {
        List<AuthUser> userList = authUserService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AuthUser> getUserByEmail(@PathVariable String email, HttpServletRequest request) throws AccessDeniedException {
        AuthUser user = authUserService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<AuthUserDTO> getById(@PathVariable Long id) {
        AuthUserDTO user = authUserService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
