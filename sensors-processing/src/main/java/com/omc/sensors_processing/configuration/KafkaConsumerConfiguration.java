package com.omc.sensors_processing.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.omc.sensors_processing.datatypes.SensorData;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {
	
	@Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;
	
	@Bean
	public ConsumerFactory<String, SensorData> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "sensor-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SensorDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
	}
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, SensorData> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SensorData> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
	
}
