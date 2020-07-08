package br.com.ath.service;

import br.com.ath.entities.dto.AuthClientResponseDTO;
import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.exception.UserAlreadyExistsException;
import br.com.ath.exception.NotAuthenticatedException;

public interface ClientService {


    boolean registerClient(ClientDTO client) throws UserAlreadyExistsException;

    AuthClientResponseDTO authenticate(String login, String password) throws NotAuthenticatedException;
}
