package EasyChef4.controllers;

import EasyChef4.models.Chef;
import EasyChef4.rabbit.Message;
import EasyChef4.rabbit.RabbitMqConfig;
import EasyChef4.repositories.ChefRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ChefController {

    @Autowired
    ChefRepository repository;

    @Autowired
    RabbitTemplate template;
    @GetMapping("/chefs/allchef")
    public List<Chef> getAllChefs() {
        System.out.println("Get all chefs...");

        List<Chef> chefs = new ArrayList<>();
        repository.findAll().forEach(chefs::add);

        return chefs;
    }

    @GetMapping(value = "/chefs/findById/{id}")
    public Optional<Chef> findByName(@PathVariable Long id) {

        Optional<Chef> customer = repository.findById(id);
        return customer;
    }

    @GetMapping(value = "/chefs/findByMail/{mail}")
    public List<Chef> findByMail(@PathVariable String mail) {

        List<Chef> customer = repository.findByMail(mail);
        return customer;
    }

    @PostMapping(value = "/chefs/createchef")
    public Chef postChef(@RequestBody Chef chef) {
        return repository.save(new Chef(chef.getUsername(), chef.getMail()));
    }

    @DeleteMapping("/chefs/deletchef/{id}")
    public ResponseEntity<String> deleteChef(@PathVariable("id") long id) {
        System.out.println("Delete Chef with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Chef has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/chefs/deleteallchef")
    public ResponseEntity<String> deleteAllChefs() {
        System.out.println("Delete All Chefs...");

        repository.deleteAll();

        return new ResponseEntity<>("All chefs have been deleted!", HttpStatus.OK);
    }

    @PutMapping("/chefs/editchef/{id}")
    public ResponseEntity<Chef> updateChef(@PathVariable("id") long id, @RequestBody Chef chef) {
        System.out.println("Update Chef with ID = " + id + "...");
        Optional <Chef> chefData = repository.findById(id);

        if (chefData.isPresent()) {
            Chef c = chefData.get();
            c.setUsername(chef.getUsername());
            //c.setMail(chef.getMail());
            return new ResponseEntity<>(repository.save(c), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/chefs/msgToRecipe")
    public ResponseEntity<String> postEnableLike(@RequestBody Message msg) {
        System.out.println("Try to send user like to RecipeService...");
        template.convertAndSend(
                RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.ROUTING_KEY,
                msg);
        System.out.println("Message sent.");
        return new ResponseEntity<>("Recipe liked", HttpStatus.OK);
    }
}
