package EasyChef4.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Struttura messaggio
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message implements Serializable {
    private Long idChef;
    private Long idRecipe;
    private Boolean enableLike;
}

