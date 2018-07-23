
public class KafkaConsumerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Consumer starts!");
		MyKafkaConsumer consumer = new MyKafkaConsumer("metric-m");
        consumer.start();
	}

}
