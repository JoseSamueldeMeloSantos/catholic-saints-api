package br.com.bth8.catholic_saints_api.repository;

import br.com.bth8.catholic_saints_api.model.ReligiousOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReligiousOrderRepository extends JpaRepository<ReligiousOrder, UUID> {
    Optional<ReligiousOrder> findByName(String name);
}
