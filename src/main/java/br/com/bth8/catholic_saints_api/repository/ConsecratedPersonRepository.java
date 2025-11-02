package br.com.bth8.catholic_saints_api.repository;

import br.com.bth8.catholic_saints_api.model.ConsecratedPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsecratedPersonRepository extends JpaRepository<ConsecratedPerson, UUID> {
}
