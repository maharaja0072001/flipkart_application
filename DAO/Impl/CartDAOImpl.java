package com.flipkart.dao.Impl;

import com.flipkart.custom_exceptions.DatabaseException;
import com.flipkart.dao.DBConnection;
import com.flipkart.model.Cart;
import com.flipkart.model.User;
import com.flipkart.product.Clothes;
import com.flipkart.product.Laptop;
import com.flipkart.product.Mobile;
import com.flipkart.product.Product;
import com.flipkart.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAOImpl {
    private int id;
    private float totalAmountInCart;
    private static CartDAOImpl cartDAOImplInstance;

    public CartDAOImpl() {}

    public static CartDAOImpl getInstance() {
        if (null == cartDAOImplInstance) {
            cartDAOImplInstance = new CartDAOImpl();
        }

        return cartDAOImplInstance;
    }

    public boolean addItemToCart(final Product item, final User user) {
        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into cart (user_id , product_id) values(?,?)")) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setInt(2, item.getProductId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public void setIdForCart(final int userId) {
        this.id = userId;
    }

    public boolean removeFromCart(final Product item, final User user) {
            try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("delete from cart where user_id=? and product_id=?")) {

                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setInt(2, item.getProductId());

                return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public Cart getUserCart(final User user) {
        final Cart cart = new Cart(user);

        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select user_id, product_id, p.product_type from cart c join product p on c.product_id = p.id  where user_id= ?")) {
            preparedStatement.setInt(1, user.getUserId());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int userId = resultSet.getInt(1);
                final int productId = resultSet.getInt(2);
                final String productType = resultSet.getString(3);

                if (ProductCategory.MOBILE.name().equals(productType.toUpperCase())) {
                    try (final PreparedStatement preparedStatement1 = DBConnection.getConnection().prepareStatement("select brand, model, price from mobiles where product_id=? ")) {
                        preparedStatement1.setInt(1, productId);
                        ResultSet resultSet1 = preparedStatement1.executeQuery();

                        while (resultSet1.next()) {
                            final String brand = resultSet1.getString(1);
                            final String model = resultSet1.getString(2);
                            final float price = resultSet1.getFloat(3);
                            cart.addToCart(new Mobile(brand, model, price));
                        }
                    } catch (SQLException exception) {
                        throw new DatabaseException(exception.getMessage());
                    }
                }
                if (ProductCategory.LAPTOP == ProductCategory.valueOf(productType.toUpperCase())) {
                    try (final PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement("select brand, model, price from laptops where product_id=? ")) {

                    preparedStatement2.setInt(1,productId);
                    final ResultSet resultSet2 = preparedStatement2.executeQuery();

                    while (resultSet2.next()) {
                        final String brand = resultSet2.getString(1);
                        final String model = resultSet2.getString(2);
                        final float price = resultSet2.getFloat(3);
                        cart.addToCart(new Laptop(brand, model, price));
                    }
                } catch (SQLException exception) {
                        throw new DatabaseException(exception.getMessage());
                    }
                }

                if (ProductCategory.CLOTHES == ProductCategory.valueOf(productType.toUpperCase())) {
                    try (final PreparedStatement preparedStatement3 = DBConnection.getConnection().prepareStatement("select type, brand, gender, size, price from clothes where product_id=? ")) {
                        preparedStatement3.setInt(1,productId);
                        final ResultSet resultSet3 = preparedStatement3.executeQuery();

                        while (resultSet3.next()) {
                            final String clothesType = resultSet3.getString(1);
                            final String brand = resultSet3.getString(2);
                            final String gender = resultSet3.getString(3);
                            final String size = resultSet3.getString(4);
                            final float price = resultSet3.getFloat(5);
                            cart.addToCart(new Clothes(clothesType, gender, size, price, brand));
                        }
                    } catch (SQLException exception) {
                        throw new DatabaseException(exception.getMessage());
                    }
                }
            }

            return cart;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public float getTotalAmountInCart() {
        try(final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select SUM(price) from cart where user_id=? ")) {
            preparedStatement.setInt(1, id);
           final ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            totalAmountInCart = resultSet.getFloat(1);

            return  totalAmountInCart;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public void removeItemFromCart(final Product product, final User user) {

    }
}
