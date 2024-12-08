package org.Saga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.Saga.OrderMService;
import org.Saga.controller.OrderController;
import org.Saga.dto.OrderRequestDto;
import org.Saga.entity.PurchaseOrder;
import org.Saga.event.OrderStatus;
import org.Saga.event.PaymentStatus;
import org.Saga.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;
    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp(){
        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1);
        orderRequestDto.setProductId(2);
        orderRequestDto.setAmount(3);
        orderRequestDto.setOrderId(121);
    }
    @Test
    public void testCreateOrder() throws Exception {

        PurchaseOrder orderToSave = new PurchaseOrder();

        orderToSave.setUserId(orderRequestDto.getUserId());
        orderToSave.setProductId(orderRequestDto.getProductId());
        orderToSave.setPrice(orderRequestDto.getAmount());
        orderToSave.setOrderStatus(OrderStatus.ORDER_CREATED);
        orderToSave.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);

        given(orderService.createOrder(any(OrderRequestDto.class))).willReturn(orderToSave);
        mockMvc.perform(post("/order/create")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(orderRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(orderToSave.getUserId()))
                .andExpect(jsonPath("$.productId").value(orderToSave.getProductId()))
                .andExpect(jsonPath("$.price").value(orderToSave.getPrice()))
                .andExpect(jsonPath("$.orderStatus").value(orderToSave.getOrderStatus().toString()))
                .andExpect(jsonPath("$.paymentStatus").value(orderToSave.getPaymentStatus().toString()));
    }


}
