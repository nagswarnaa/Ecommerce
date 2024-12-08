package org.Saga.integtests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.Saga.dto.OrderRequestDto;
import org.Saga.entity.PurchaseOrder;
import org.Saga.event.OrderStatus;
import org.Saga.event.PaymentStatus;
import org.Saga.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp(){
        orderRepository.deleteAll();

        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1);
        orderRequestDto.setProductId(101);
        orderRequestDto.setAmount(299);
    }

    @Test
    @DisplayName("POST /order/create - Success")
    public void testCreateOrder_Success() throws Exception {
        mockMvc.perform(post("/order/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(orderRequestDto.getUserId()))
                .andExpect(jsonPath("$.productId").value(orderRequestDto.getProductId()))
                .andExpect(jsonPath("$.price").value(orderRequestDto.getAmount()))
                .andExpect(jsonPath("$.orderStatus").value(OrderStatus.ORDER_CREATED.toString()))
                .andExpect(jsonPath("$.paymentStatus").value(PaymentStatus.PAYMENT_COMPLETED.toString()));

        assertThat(orderRepository.findAll()).hasSize(1);
        PurchaseOrder savedOrder = orderRepository.findAll().get(0);
        assertThat(savedOrder.getUserId()).isEqualTo(orderRequestDto.getUserId());
        assertThat(savedOrder.getProductId()).isEqualTo(orderRequestDto.getProductId());
        assertThat(savedOrder.getPrice()).isEqualTo(orderRequestDto.getAmount());
        assertThat(savedOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDER_CREATED);
        assertThat(savedOrder.getPaymentStatus()).isEqualTo(PaymentStatus.PAYMENT_COMPLETED);
    }

}
