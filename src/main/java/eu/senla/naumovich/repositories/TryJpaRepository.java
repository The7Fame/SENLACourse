package eu.senla.naumovich.repositories;

import eu.senla.naumovich.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TryJpaRepository extends JpaRepository<Address, Integer> {
}
