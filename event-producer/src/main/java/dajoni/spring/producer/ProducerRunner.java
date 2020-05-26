package dajoni.spring.producer;

import dajoni.spring.schema.AnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

@Component
@Slf4j
public class ProducerRunner implements CommandLineRunner {

    private final KafkaTemplate<Integer, AnEvent> kafkaTemplate;
    private final ProducerProperties properties;
    private Random r = new Random();


    @Autowired
    public ProducerRunner(KafkaTemplate<Integer, AnEvent> kafkaTemplate, ProducerProperties properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.properties = properties;
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = ResourceUtils.getURL("classpath:wiki-Talk.txt").openStream();
        Scanner scanner = new Scanner(inputStream);

        log.info("Reading file");
        int lines = 0;
        int records = 0;
        while (scanner.hasNextLine()) {
            lines++;
            if (scanner.hasNextInt()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                sendRecord(from, to);
                records++;
                if (records % 10000 == 0) log.info("Send {} records", records);
            }
            scanner.nextLine();

        }
        log.info("Send {} records in {} lines", records, lines);
        while (true) Thread.sleep(1000);

    }

    private void sendRecord(int from, int to) {
        AnEvent data = AnEvent.newBuilder().setParticipantA(from)
                .setParticipantB(to)
                .setSet("MySet" + r.nextInt(1000))
                .setTimestamp(System.currentTimeMillis())
                .setId((from % 10000) + "-" + (to % 10000))
                .build();
        this.kafkaTemplate.send("events", from, data);
    }
}
