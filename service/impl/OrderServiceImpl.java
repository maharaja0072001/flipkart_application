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

    /**
     * <p>
     * Default constructor of the OrderServiceImpl class. kept private to restrict from creating object from outside of this class.
     * </p>
     */
    private OrderServiceImpl() {}

    /**
     * <p>
     * Creates a single object of OrderServiceImpl Class and returns it.
     * </p>
     *
     * @return returns the single instance of OrderServiceImpl Class.
     */
    public static OrderService getInstance() {
        if (null == orderServiceInstance) {
            orderServiceInstance = new OrderServiceImpl();
        }

        return orderServiceInstance;
    }

    /**
     * <p>
     * Adds the order placed by the user.
     * </p>
     *
     * @param userId Refers the id of the user
     * @param order Refers the {@link Order} to be added.
     */
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

    /**
     * <p>
     * Gets all the orders placed by the user.
     * </p>
     *
     * @param userId Refers the id of the user
     * @return  all the {@link Order} of the user.
     */
    public List<Order> getOrders(final int userId) {
        return ORDERS.get(userId);
    }

}
