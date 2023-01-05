package Recipe4.rabbit;

import Recipe4.models.Recipe;
import Recipe4.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Funzione di ricezione del messaggio.
 * TODO
 * Da qui bisogna poi effettuare le operazioni di repository per
 * salvare/eliminare l'id dell'utente dalla lista di Recipe.
 */

@Component
@Slf4j
public class Receiver {

    @Autowired
    RecipeRepository repository;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    @Transactional
    public void listener(Message msg) throws Exception {
        System.out.println("Get Message.");
        Recipe _recipe = repository.findById(msg.getIdRecipe()).get();
        if (msg.getEnableLike()) {
            log.info("Found Recipe. To insert in likeList");
            _recipe.addUserLike(msg.getIdChef());
            repository.save(_recipe);
            log.info("Inserted.");
        }else {
            log.info("Found Recipe. To remove in likeList");
            _recipe.removeUserLike(msg.getIdChef());
            repository.save(_recipe);
            log.info("Deleted.");
        }
    }
}
