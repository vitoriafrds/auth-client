package br.com.ath.service.impl;

import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.repository.ClientRepository;
import br.com.ath.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean registerClient(ClientDTO client) {
        return false;
    }
}
