package producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Properties;

public class ProducerWithCallback {

    private static Logger logger = LoggerFactory.getLogger(ProducerWithCallback.class.getSimpleName());


    public static void main(String[] args) {
        // create producer properties.
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("key.serializer", StringSerializer.class.getName());
        props.setProperty("value.serializer", StringSerializer.class.getName());

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // create producer record
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "hello world");

        // sending the data

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {

                if (e == null) {
                    logger.info("callback received");
                    logger.info("\ntopic: " + metadata.topic() + " \n" +
                            "partition: " + metadata.partition() + " \n" +
                            "offset: " + metadata.offset() + " \n" +
                            "ts: " + metadata.timestamp() + " \n");
                } else {
                    logger.error("error publishing message", e);
                }
            }
        });
        producer.flush();
        producer.close();
    }
}
