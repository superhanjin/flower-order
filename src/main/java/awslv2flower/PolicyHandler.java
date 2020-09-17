package awslv2flower;

import awslv2flower.config.kafka.KafkaProcessor;
import feign.Request.Options;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString) {

    }

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_OrderStatus(@Payload Shipped shipped) {

       /*

        if (shipped.isMe()) {
            System.out.println("##### listener OrderStatus : " + shipped.toJson());

            Optional<Order> orderOptional = orderRepository.findById(shipped.getOrderId());
            if( orderOptional.isPresent()) {

                Order order = orderOptional.get();
                order.setStatus("Delivery"+System.getenv("DeliveryStatus"));

                orderRepository.save(order);
            }    
        }
        */

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentConfirmed_OrderStatus(@Payload PaymentConfirmed paymentConfirmed) {

        if (paymentConfirmed.isMe()) {
            System.out.println("##### listener OrderStatus : " + paymentConfirmed.toJson());

            Optional<Order> orderOptional = orderRepository.findById(paymentConfirmed.getOrderId());
            if( orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setStatus("PaymentConfirmed");

                orderRepository.save(order);
            }
        }
        
    }

}
