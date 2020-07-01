package br.com.ath.service;

import br.com.ath.entities.Client;
import br.com.ath.entities.dto.ClientDTO;

public interface ClientService {


    boolean registerClient(ClientDTO client);
}
