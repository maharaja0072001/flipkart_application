package com.flipkart.service.impl;

import com.flipkart.model.Order;
import com.flipkart.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Provides the service for the Order.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {

    private static OrderService orderServiceInstance;
    private static final Map<Integer, List<Order>> ORDERS = new HashMap<>();

    private OrderServiceImpl() {}

    public static OrderService getInstance() {
        if (null == orderServiceInstance) {
            orderServiceInstance = new OrderServiceImpl();
        }

        return orderServiceInstance;
    }

    public void addOrder(final int userId, final Order order) {

        if (ORDERS.containsKey(userId)) {
            final List<Order> existingOrderList = ORDERS.get(userId);

            existingOrderList.add(order);
        } else {
            final List<Order> newOrderList = new ArrayList<>();
            ORDERS.put(userId, newOrderList);
            newOrderList.add(order);
        }
    }

    public List<Order> getOrders(final int userId) {
        return ORDERS.get(userId);
    }

}
