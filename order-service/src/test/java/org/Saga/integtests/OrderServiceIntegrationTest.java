package org.Saga.integtests;


import org.Saga.dto.OrderRequestDto;
import org.Saga.entity.PurchaseOrder;
import org.Saga.event.OrderStatus;
import org.Saga.event.PaymentStatus;
import org.Saga.repository.OrderRepository;
import org.Saga.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp(){
        orderRepository.deleteAll();

        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1);
        orderRequestDto.setProductId(101);
        orderRequestDto.setOrderId(2);
        orderRequestDto.setAmount(299);
    }

    @Test
    @DisplayName("Should create and save a new order successfully")
    public void testCreateOrder_Success() {
        PurchaseOrder createdOrder = orderService.createOrder(orderRequestDto);

        assertThat(createdOrder).isNotNull();
        assertThat(createdOrder.getUserId()).isEqualTo(orderRequestDto.getUserId());
        assertThat(createdOrder.getProductId()).isEqualTo(orderRequestDto.getProductId());
        assertThat(createdOrder.getPrice()).isEqualTo(orderRequestDto.getAmount());
        assertThat(createdOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDER_CREATED);
        assertThat(createdOrder.getPaymentStatus()).isEqualTo(PaymentStatus.PAYMENT_COMPLETED);

    }

}
