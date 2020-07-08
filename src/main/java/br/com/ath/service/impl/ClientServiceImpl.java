package br.com.ath.service.impl;

import br.com.ath.entities.Client;
import br.com.ath.entities.dto.AuthClientResponseDTO;
import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.enumerators.AuthResponseEnum;
import br.com.ath.exception.DuplicateClientException;
import br.com.ath.exception.NotAuthenticatedException;
import br.com.ath.repository.ClientRepository;
import br.com.ath.service.ClientService;
import br.com.ath.utils.HashingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;
    private HashingUtils hash;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, HashingUtils hash) {
        this.repository = repository;
        this.hash = hash;
    }

    @Override
    public boolean registerClient(ClientDTO request) throws DuplicateClientException {
        Client client = new Client(request);

        if (verifyEmail(request.getLogin())) {
            throw new DuplicateClientException(AuthResponseEnum.USER_ALREADY_EXISTS.getMessage());
        }

        String securityPassword = hash.hashPassword(request.getPassword());
        client.setPassword(securityPassword);

        repository.save(client);
        log.info("USER CREATED");

        return true;
    }

    @Override
    public AuthClientResponseDTO authenticate(String login, String password) throws NotAuthenticatedException {

        AuthClientResponseDTO response = new AuthClientResponseDTO();

        Optional<Client> informations = repository.findByLogin(login);
        String convertedPassword = hash.hashPassword(password);

        if (informations.isPresent()) {
            if (informations.get().getPassword().equals(convertedPassword)) {
                response.setMessage(AuthResponseEnum.USER_AUTHENTICATED.getMessage());
                response.setTimestamp(LocalDate.now());
                return response;
            }
        }

        throw new NotAuthenticatedException(AuthResponseEnum.USER_NOT_AUTHENTICATED.getMessage());
    }


    private boolean verifyEmail(String login) {
        return repository.existsByLogin(login);
    }
}
