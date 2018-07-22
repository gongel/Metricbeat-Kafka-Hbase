import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
public class MyKafkaProducer extends Thread{
  
 
    private String topic;
    private KafkaProducer<String, String> producer;
    public  MyKafkaProducer(String topic){
        this.topic = topic;
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.141.211.131:9092,10.141.211.132:9092,10.141.211.133:9092,10.141.211.134:9092,10.141.211.135:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
    }
 
    @Override
    public void run(){
        int messageNo = 1;
        while (true){
            String message = "message_" + messageNo;
            producer.send(new ProducerRecord<String, String>(topic, message));
            System.out.println("Send: " + message);
            messageNo ++;
            try{
                Thread.sleep(200);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
