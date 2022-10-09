package userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservice.models.User;
import userservice.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")   //si concatenerà api/v1/customers per restituire la lista di tutti gli utenti
    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("userbyid/{idUser}")// per ottenere l'ogetto utente passando l'id
    public User userById(@PathVariable Long idUser){ return userRepository.findUserById(idUser);}

    @PostMapping(value = "/user/create")// per aggiungere un nuovo utente
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody User user){
        userRepository.save(user);
        System.out.println("New user created!");
    }

    // TODO - fare metodo per cambiare il nome dell'utente (OPZIONALE)

    @DeleteMapping("/user/delete/{id}") // per eliminare un utente fornendo l'id
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        System.out.println("Delete user with id = " + id + "...");
        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }
}
