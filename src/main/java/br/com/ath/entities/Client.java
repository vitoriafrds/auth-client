package br.com.ath.entities;

import br.com.ath.entities.dto.ClientDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_CLIENT")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String login;
    private String password;
    private String role;

    public Client(ClientDTO dto){
        this.name = dto.getUsername();
        this.login = dto.getLogin();
        this.password = dto.getPassword();
    }

}
