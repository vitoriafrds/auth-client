package br.com.ath.service.impl;

import br.com.ath.entities.Client;
import br.com.ath.entities.dto.AuthClientResponseDTO;
import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.enumerators.AuthResponseEnum;
import br.com.ath.exception.DuplicateClientException;
import br.com.ath.exception.NotAuthenticatedException;
import br.com.ath.repository.ClientRepository;
import br.com.ath.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
            throw new DuplicateClientException(AuthResponseEnum.USER_ALREADY_EXISTS.getMessage());
        }
        repository.save(client);
        log.info("USER CREATED");

        return true;
    }

    @Override
    public AuthClientResponseDTO authenticate(String login, String password) throws NotAuthenticatedException {

        Optional<Client> informations = repository.findByLogin(login);
        AuthClientResponseDTO response = new AuthClientResponseDTO();


        if (informations.isPresent()) {
            if (informations.get().getPassword().equals(password)) {
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
