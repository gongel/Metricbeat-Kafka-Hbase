package cn.gongel;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class MyEventTimeExtractor implements TimestampExtractor {

        public long extract(ConsumerRecord<Object, Object> record, long previousTimestamp) {

            return 0;
    }
}
