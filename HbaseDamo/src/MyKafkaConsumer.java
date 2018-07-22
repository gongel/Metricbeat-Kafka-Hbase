import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
 
public class MyKafkaConsumer extends Thread {
	private final String topic;
 
	public MyKafkaConsumer(String topic) {
		this.topic = topic;
	}
 
	private KafkaConsumer<String, String> createConsumer() {
		Properties props=new Properties();
		props.put("bootstrap.servers", "10.141.211.131:9092");
		props.put("group.id", "test");
	    props.put("enable.auto.commit", "true");
	    props.put("auto.commit.interval.ms", "1000");
	    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	    return new KafkaConsumer<String,String>(props);
	} 
	@Override
	public void run() {
		KafkaConsumer<String, String> consumer=createConsumer();
		consumer.subscribe(Arrays.asList(this.topic));
		while(true) {
			ConsumerRecords<String,String> records=consumer.poll(100);
			for(ConsumerRecord<String, String>record :records) {
				System.out.printf("offset=%d,key=%s,value=%s%n",record.offset(),record.key(),record.value());
			}
		}
		
	}
}
