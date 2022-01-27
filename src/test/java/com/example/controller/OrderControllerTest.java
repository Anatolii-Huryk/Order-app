package com.example.controller;

import com.example.dto.OrderRequestDto;
import com.example.model.Order;
import com.example.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    private List<Order> mockOrders;
    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
         mockOrders = List.of(
                new Order(1L, 12L, "Phone", 5L, LocalDateTime.now()));
    }

    @Test
    public void shouldReturnLowCostItemAndReduceQuantity() {
        String item = "Phone";
        Long quantity = 2L;

        Mockito.when(orderService.findItemWherePriceIsLow(item, quantity))
                .thenReturn(mockOrders);

        RestAssuredMockMvc
                .given()
                .queryParam("item", item)
                .queryParam("quantity", quantity)
                .when()
                .put("/orders/find-by-item")
                .then()
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].price", Matchers.equalTo(12))
                .body("[0].item", Matchers.equalTo("Phone"))
                .body("[0].quantity", Matchers.equalTo(3));
    }

    @Test
    public void shouldCreateOrder() {
        Order orderSave = new Order(12L, "Phone", 5L);
        Mockito.when(orderService.add(orderSave)).thenReturn(mockOrders.get(0));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new OrderRequestDto(orderSave.getItem(), orderSave.getPrice(), orderSave.getQuantity()))
                .when()
                .post("/orders")
                .then()
                .statusCode(200)
                .body("item", Matchers.equalTo("Phone"))
                .body("price", Matchers.equalTo(12))
                .body("quantity", Matchers.equalTo(5));
    }
}
