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
    public void shouldShowAllProducts() {
        List<Order> mockOrders = List.of(
                new Order(1L, 8L, "Phone", 5L, LocalDateTime.now()),
                new Order(2L, 10L, "Phone", 1L, LocalDateTime.now()),
                new Order(3L, 12L, "Phone", 10L, LocalDateTime.now())
        );
        Mockito.when(orderService.findAll()).thenReturn(mockOrders);

        RestAssuredMockMvc.when()
                .get("/orders/all")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[1].item", Matchers.equalTo("Phone"))
                .body("[2].price", Matchers.equalTo(12))
                .body("[0].quantity", Matchers.equalTo(5));
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
                .body("[0].price", Matchers.equalTo(12))
                .body("[0].item", Matchers.equalTo("Phone"))
                .body("[0].quantity", Matchers.equalTo(5));
    }
}
