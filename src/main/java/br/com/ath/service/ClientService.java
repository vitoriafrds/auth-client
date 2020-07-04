package br.com.ath.service;

import br.com.ath.entities.Client;
import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.exception.DuplicateClientException;

public interface ClientService {


    boolean registerClient(ClientDTO client) throws DuplicateClientException;
}
