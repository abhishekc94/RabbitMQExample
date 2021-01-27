package com.hcl.rabbitmq.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.rabbitmq.config.MessagingConfig;
import com.hcl.rabbitmq.model.Order;
import com.hcl.rabbitmq.model.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/{resturantName}")
	public String bookOrder(@RequestBody Order order,@PathVariable String resturantName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus status = new OrderStatus(order, "Process", "order placed successfully in "+resturantName);
		rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, status);
		return "Success!!";
		
	}
}
