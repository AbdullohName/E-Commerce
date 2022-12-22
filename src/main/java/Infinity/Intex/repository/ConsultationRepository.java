package Infinity.Intex.repository;

import Infinity.Intex.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    Optional<Consultation> findFirstByFullName(String name);
}