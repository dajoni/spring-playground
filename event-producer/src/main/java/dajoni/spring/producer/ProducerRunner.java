package dajoni.spring.producer;

import dajoni.spring.schema.AnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
@Slf4j
public class ProducerRunner implements CommandLineRunner {

    private final KafkaTemplate<Integer, AnEvent> kafkaTemplate;

    @Autowired
    public ProducerRunner(KafkaTemplate<Integer, AnEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application started with command-line arguments: {}", Arrays.toString(args));
        Random r = new Random();
        while (true) {
            Thread.sleep(100);
            int id = r.nextInt(100);
            AnEvent data = AnEvent.newBuilder().setParticipantA(id)
                    .setParticipantB(r.nextInt(100))
                    .setSet("MySet" + r.nextInt(3))
                    .setTimestamp(System.currentTimeMillis())
                    .setId(r.nextInt(20) + "-" + r.nextInt(10))
                    .build();
            log.info("Sending message with id {}", id);
            this.kafkaTemplate.send("events", id, data);
        }
    }
}
