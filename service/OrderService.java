package com.flipkart.service;

import com.flipkart.model.Order;

import java.util.List;

/**
 * <p>
 * Provides service for the Order.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public interface OrderService {

   void addOrder(final int userId, final Order order);

   List<Order> getOrders(final int userId);

}
