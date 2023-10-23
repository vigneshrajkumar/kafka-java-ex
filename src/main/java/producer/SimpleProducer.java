package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SimpleProducer {

    private static Logger logger = LoggerFactory.getLogger(SimpleProducer.class.getSimpleName());

    public static void main(String[] args) {

        logger.info("simple producer");


        // create producer properties.
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("key.serializer", StringSerializer .class.getName());
        props.setProperty("value.serializer", StringSerializer.class.getName());

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // create producer record
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "hello world");

        // sending the data
        producer.send(record);
        producer.flush();
        producer.close();
    }
}
