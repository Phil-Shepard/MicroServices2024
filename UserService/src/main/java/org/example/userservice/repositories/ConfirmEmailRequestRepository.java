package org.example.userservice.repositories;

import org.example.userservice.models.entities.ConfirmEmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmEmailRequestRepository extends JpaRepository<ConfirmEmailRequest, Long> {
    Optional<ConfirmEmailRequest> findByEmailAndCode(String email, String code);
    boolean existsByEmailAndCode(String email, String code);
}
