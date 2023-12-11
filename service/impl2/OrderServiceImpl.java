package com.flipkart.service.impl2;

import com.flipkart.dao.Impl.OrderDAOImpl;
import com.flipkart.model.Order;
import com.flipkart.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final OrderDAOImpl ORDER_DAO = OrderDAOImpl.getInstance();
    private static OrderServiceImpl orderServiceInstance;

    private OrderServiceImpl() {}

    public static final OrderServiceImpl getInstance() {
        if (null == orderServiceInstance) {
            orderServiceInstance = new OrderServiceImpl();
        }

        return orderServiceInstance;
    }

    public List<Order> getOrders(final int userId) {
        return ORDER_DAO.getOrders(userId);
    }

    public void addOrder(final int userId, final Order order) {
        ORDER_DAO.addOrder(userId, order);
    }
}
