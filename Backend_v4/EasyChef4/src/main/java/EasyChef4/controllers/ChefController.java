package EasyChef4.controllers;

import EasyChef4.models.Chef;
import EasyChef4.repositories.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ChefController {

    @Autowired
    ChefRepository repository;

    @GetMapping("/chefs")
    public List<Chef> getAllChefs() {
        System.out.println("Get all chefs...");

        List<Chef> chefs = new ArrayList<>();
        repository.findAll().forEach(chefs::add);

        return chefs;
    }

    @PostMapping(value = "/chefs/create")
    public Chef postChef(@RequestBody Chef chef) {

        Chef _chef = repository.save(new Chef(chef.getName()));
        return _chef;
    }

    @DeleteMapping("/chefs/{id}")
    public ResponseEntity<String> deleteChef(@PathVariable("id") long id) {
        System.out.println("Delete Chef with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Chef has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/chefs/delete")
    public ResponseEntity<String> deleteAllChefs() {
        System.out.println("Delete All Chefs...");

        repository.deleteAll();

        return new ResponseEntity<>("All chefs have been deleted!", HttpStatus.OK);
    }

    @PutMapping("/chefs/{id}")
    public ResponseEntity<Chef> updateChef(@PathVariable("id") long id, @RequestBody Chef chef) {
        System.out.println("Update Chef with ID = " + id + "...");

        Optional<Chef> chefData = repository.findById(id);

        if (chefData.isPresent()) {
            Chef _chef = chefData.get();
            _chef.setName(chef.getName());
            return new ResponseEntity<>(repository.save(_chef), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
