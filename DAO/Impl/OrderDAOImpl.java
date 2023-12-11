package com.flipkart.dao.Impl;

import com.flipkart.ProductCategory;
import com.flipkart.custom_exceptions.DatabaseException;
import com.flipkart.dao.DBConnection;
import com.flipkart.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Responsible for storing all the order details in the database.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
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
        final int quantity = order.getQuantity();
        final float totalAmount = order.getTotalAmount();
        final String address = order.getAddress();
        final String paymentMode = order.getPaymentMode();

        try(final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into orders(user_id, product_id, address, payment_mode, quantity, total_amount) values (?,?,?,?,?,?) ")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, paymentMode);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setFloat(6, totalAmount);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public List<Order> getOrders(final int userId) {
        final List<Order> orders = new ArrayList<>();

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select o.product_id, o.payment_mode,o.quantity,o.total_amount, o.address, p.product_category, e.brand,e.model, p.price,c.clothes_type,c.size,c.gender, c.brand from orders o join product p on o.product_id=p.id  left join electronics_inventory e on o.product_id = e.product_id left join clothes_inventory c on o.product_id=c.product_id where o.user_id = ?")) {
            preparedStatement.setInt(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int productId = resultSet.getInt(1);
                String productName = null;
                final String paymentMode = resultSet.getString(2);
                final int quantity = resultSet.getInt(3);
                final float totalAmount = resultSet.getFloat(4);
                final String address = resultSet.getString(5);
                final String productCategory = resultSet.getString(6);


                if (ProductCategory.MOBILE == ProductCategory.valueOf(productCategory.toUpperCase()) || ProductCategory.LAPTOP == ProductCategory.valueOf(productCategory.toUpperCase())) {
                    final String brand = resultSet.getString(7);
                    final String model = resultSet.getString(8);
                    final float price = resultSet.getFloat(9);
                    productName = String.format("Product name : %s %s - Rs :%.2f", brand,model,price);
                }

                if (ProductCategory.CLOTHES == ProductCategory.valueOf(productCategory.toUpperCase())) {
                    final float price = resultSet.getFloat(9);
                    final String clothesType = resultSet.getString(10);
                    final String size = resultSet.getString(11);
                    final String gender = resultSet.getString(12);
                    final String brand = resultSet.getString(13);

                    productName = String.format("%s brand :%s size : %s gender: %s - Rs :%.2f ",clothesType,brand,size,gender,price);
                }
                final Order order = new Order.OrderBuilder(userId, productId, paymentMode).setProductName(productName).setTotalAmount(totalAmount).setQuantity(quantity).setAddress(address).buildOrder();

                orders.add(order);
            }

        return orders;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }
}
