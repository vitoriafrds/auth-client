package br.com.ath.repository;

import br.com.ath.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByLogin(String login);

    Optional<Client> findByLogin(String login);
}
