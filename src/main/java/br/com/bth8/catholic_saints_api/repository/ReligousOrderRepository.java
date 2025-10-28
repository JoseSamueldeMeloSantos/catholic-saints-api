package br.com.bth8.catholic_saints_api.repository;

import br.com.bth8.catholic_saints_api.model.ReligiousOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReligousOrderRepository extends JpaRepository<ReligiousOrder, UUID> {

}
