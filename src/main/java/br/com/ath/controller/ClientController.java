package br.com.ath.controller;

import br.com.ath.entities.dto.ClientDTO;
import br.com.ath.exception.DuplicateClientException;
import br.com.ath.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/create")
public class ClientController {

    private ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Boolean> createLogin(@RequestBody(required = true) ClientDTO request) throws DuplicateClientException {
       return ResponseEntity.ok().body(service.registerClient(request));
    }

    @GetMapping()
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok().body("TESTANDO API");
    }
}
