package com.hcl.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.hcl.rabbitmq.config.MessagingConfig;
import com.hcl.rabbitmq.model.OrderStatus;

@Component
public class OrderConsumer {
	
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus status) {
		System.out.println("Message Received from Queue : "+status);
	}
}
