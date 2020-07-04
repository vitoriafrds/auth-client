package br.com.ath.controller;

import br.com.ath.exception.NotAuthenticatedException;
import br.com.ath.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private ClientService clientService;

    @Autowired
    public AuthenticationController(ClientService service) {
        this.clientService = service;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Boolean> autenticate(@RequestParam String login, @RequestParam String password) throws NotAuthenticatedException {
        return ResponseEntity.ok().body(clientService.authenticate(login, password));
    }

}
