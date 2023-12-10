package com.flipkart.dao.Impl;

import com.flipkart.custom_exceptions.DatabaseException;
import com.flipkart.dao.DBConnection;
import com.flipkart.model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl {

    private static OrderDAOImpl orderDAOInstance;

    private OrderDAOImpl() {}

    public static OrderDAOImpl getInstance() {
        if (null == orderDAOInstance) {
            orderDAOInstance = new OrderDAOImpl();
        }

        return orderDAOInstance;
    }

    public void addOrder(final int userId, final Order order) {
        final int productId = order.getProductId();
        final String productName = order.getProductName();
        final int quantity = order.getQuantity();
        final float price = order.getTotalAmount();
        final String address = order.getAddress();
        final String paymentMode = order.getPaymentMode();

        try(final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into orders(user_id, product_id, product_name, address, paymentMode) values (?,?,?,?,?) returning id")) {
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,productId);
            preparedStatement.setString(3,productName);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,paymentMode);
            final int orderId = preparedStatement.executeUpdate();

            PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement("insert into Order_details values (?,?,?)");
            preparedStatement2.setInt(1, orderId);
            preparedStatement2.setInt(2, quantity);
            preparedStatement2.setFloat(3, price);

            preparedStatement2.executeUpdate();

        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public List<Order> getOrders(final int userId) {
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select product_id, product_name, quantity, price, address, paymentMode from orders where user_id =? ")) {
            preparedStatement.setInt(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int productId = resultSet.getInt(1);
                final String productName = resultSet.getString(2);
                final int quantity = resultSet.getInt(3);
                final float price = resultSet.getFloat(4);
                final String address = resultSet.getString(5);
                final String paymentMode = resultSet.getString(6);
                final Order order = new Order.OrderBuilder(userId, productId, paymentMode).setProductName(productName).setTotalAmount(price).setQuantity(quantity).setAddress(address).buildOrder();

                orders.add(order);
            }

        } catch (SQLException exception) {
                throw new DatabaseException(exception.getMessage());
            }

        return orders;
    }

}
