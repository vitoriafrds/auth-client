package br.com.ath.service;

import br.com.ath.entities.Client;
import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.exception.DuplicateClientException;
import br.com.ath.exception.NotAuthenticatedException;
import org.springframework.web.bind.annotation.RequestParam;

public interface ClientService {


    boolean registerClient(ClientDTO client) throws DuplicateClientException;

    boolean authenticate(String login, String password) throws NotAuthenticatedException;
}
