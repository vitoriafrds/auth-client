package br.com.ath.service.impl;

import br.com.ath.entities.Client;
import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.exception.DuplicateClientException;
import br.com.ath.exception.NotAuthenticatedException;
import br.com.ath.repository.ClientRepository;
import br.com.ath.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
    }

    @Override
    public boolean registerClient(ClientDTO request) throws DuplicateClientException {
        Client client = new Client(request);

        if(verifyEmail(request.getLogin())) {
            throw new DuplicateClientException("login already registered");
        }

        client.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(client);

        return true;
    }

    @Override
    public boolean authenticate(String login, String password) throws NotAuthenticatedException {

        boolean authentication = repository.existsByLoginAndPassword(login, password);

        if(!authentication) {
            throw new NotAuthenticatedException("incorrect password or login");
        }

        return true;
    }

    private boolean verifyEmail(String login) {
        return repository.existsByLogin(login);
    }
}
