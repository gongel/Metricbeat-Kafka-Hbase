
public class KafkaProducerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Producer starts!");
		MyKafkaProducer producer = new MyKafkaProducer("metric-test");
        producer.start();
	}

}
