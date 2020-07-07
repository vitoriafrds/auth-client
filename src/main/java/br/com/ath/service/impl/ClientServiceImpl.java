package br.com.ath.service.impl;

import br.com.ath.entities.Client;
import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.exception.DuplicateClientException;
import br.com.ath.exception.NotAuthenticatedException;
import br.com.ath.repository.ClientRepository;
import br.com.ath.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerClient(ClientDTO request) throws DuplicateClientException {
        Client client = new Client(request);

        if (verifyEmail(request.getLogin())) {
            throw new DuplicateClientException("login already registered");
        }
        repository.save(client);
        log.info("USER CREATED");

        return true;
    }

    @Override
    public boolean authenticate(String login, String password) throws NotAuthenticatedException {

        Optional<Client> informations = repository.findByLogin(login);

        if (informations.isPresent()) {
            if (informations.get().getPassword().equals(password)) {
                log.info("USER AUTENTICATE");
                return true;
            }
        } else {
            throw new NotAuthenticatedException("INCORRECT PASSWORD OR LOGIN");
        }
        return false;
    }


    private boolean verifyEmail(String login) {
        return repository.existsByLogin(login);
    }
}
