package com.poec.sortie_facile_backend.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/all")
    public ResponseEntity<String> getAdminsOnly() {
        return new ResponseEntity<>("ACCESSIBLE BY ANY ROLE", HttpStatus.OK);
    }

    @GetMapping("/admins-only")
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>("ACCESSIBLE ONLY BY A ROLE ADMIN", HttpStatus.OK);
    }

    @GetMapping("/users-only")
    public ResponseEntity<String> getUsersOnly() {
        return new ResponseEntity<>("ACCESSIBLE ONLY BY A ROLE USER", HttpStatus.OK);
    }

}
