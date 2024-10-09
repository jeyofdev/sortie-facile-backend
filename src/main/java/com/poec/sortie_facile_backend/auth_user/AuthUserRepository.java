package com.poec.sortie_facile_backend.auth_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByEmail(String email);

    Optional<AuthUser> findByVerificationToken(String verificationToken);

    Optional<AuthUser> findByResetToken(String resetToken);
}
