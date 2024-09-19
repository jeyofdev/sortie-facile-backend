package com.poec.sortie_facile_backend.auth_user;

import com.poec.sortie_facile_backend.auth_user.dto.AuthUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserRepository authUserRepository;
    private final AuthUserService authUserService;

    @GetMapping("/all")
    public ResponseEntity<List<AuthUserDTO>> getAll(HttpServletRequest request) throws AccessDeniedException {
        List<AuthUser> authUserList = authUserService.findAll();
        List<AuthUserDTO> authUserDTOs = authUserList.stream().map(AuthUserMapper::mapFromEntity).toList();
        return new ResponseEntity<>(authUserDTOs, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AuthUserDTO> getUserByEmail(@PathVariable String email, HttpServletRequest request) throws AccessDeniedException {
        AuthUser authUser = authUserService.findUserByEmail(email);
        AuthUserDTO authUserDTO = AuthUserMapper.mapFromEntity(authUser);
        return new ResponseEntity<>(authUserDTO, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<AuthUserDTO> getById(@PathVariable Long id) {
        AuthUser authUser = authUserService.findUserById(id);
        AuthUserDTO authUserDTO = AuthUserMapper.mapFromEntity(authUser);
        return new ResponseEntity<>(authUserDTO, HttpStatus.OK);
    }
}
