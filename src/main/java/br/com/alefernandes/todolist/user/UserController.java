package br.com.alefernandes.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Modificador
 * public - qualquer um pode acessar a classe
 * private - tem uma restrição um pouco maior
 * protected - quando tá na mesma estrutura do pacote
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository; 

    
    /**
     * String (texto)
     * Integer (int) numeros inteiros
     * Double (double) Numeros 0.00000
     * Float (float) O que diferencia é o nmr de caracteres Numeros 0.000
     * Char (A C )
     * Date (data)
     * void - quando não tem retorno do nosso método
     */
    /*
     * Body
     */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null) {
            System.out.println("Usuario já existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
            

        }

        var passwordHashred = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel); 
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

    }     
}