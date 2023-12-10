package com.flipkart.controller;

import com.flipkart.model.Order;
import com.flipkart.service.OrderService;
import com.flipkart.service.impl.OrderServiceImpl;

import java.util.List;

public class OrderController {

        private static OrderController orderControllerInstance;
        private static final OrderService ORDER_SERVICE = OrderServiceImpl.getInstance();

        private OrderController() {}

        public static synchronized OrderController getInstance() {
            if (null == orderControllerInstance) {
                orderControllerInstance = new OrderController();
            }

            return orderControllerInstance;
        }

        public List<Order> getOrders(final int userId) {
            return ORDER_SERVICE.getOrders(userId);
        }

        public void addOrder(final int userId, final Order order) {
            ORDER_SERVICE.addOrder(userId, order);
        }
    }




