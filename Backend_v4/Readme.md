# Backend

Ordine per avere sempre container aggiornati:
1. Modifico le classi java secondo il bisogno
2. Faccio la build del progetto Java ed ottengo il file `.jar` nella cartella `\target` usando:
   1. Maven > Lifecycle > Clean 
   2. Maven > Lifecycle > Package
   3. >Nota: Per fare la build del progetto Java Maven userà il file `application.properties` con dentro il riferimento al db con `localhost`, ma quando poi creeremo il container nel `docker-compose.yml` gli passeremo i parametri `SPRING_DATASOURCE_URL` con il VERO riferimento al Database che verrà poi sovrascritto quando verrà lanciato il container.
3. Lancio i comandi (dove si trova il docker-compose ed il Dockerfile che devono stare insieme):
   * `docker-compose build` (tutte le volte che modifico il Dockerfile e il jar devo fare la build)
   * `docker-compose up` (se ho già fatto la build posso lanciare il singolo container da qui)

Alternativa ai comandi manuali con Maven si può usare:
1. `.\mvnw clean`
2. `.\mvnw package`
3. `docker-compose build` -> oppure `docker-compose build <nome_container_che_voglio_aggiornare>`
4. `docker-compose up` -> oppure `docker-compose up <nome_container_che_voglio_lanciare>`
5. Tutti questi comandi vanno ovviamente lanciati in ordine e nella cartella dove si trova il file `mvnw` e il `docker-compose.yml` e `Dockerfile`


## Per creare un nuovo "servizio" in Intellij
1. Andare sulla cartella root del progetto
2. Selezionare "New" > "Module" > "Maven" > "Create from archetype"
3. Compilare con i dati del pom.xml di EasyChef4
4. Una volta creata la cartella bisogna cercare di avere un pom.xml simile a quello del progetto di EasyChef4 (con anche i tag XML di parent)
5. Cliccare con il tasto destro sulla cartella del nuovo servizio creato > "Maven" > "Reload Project"
6. Aggiungere i file che servono (Classi Java, application-properties.yml, docker-compose.yml, Dockerfile, etc.)
