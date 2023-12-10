package com.flipkart.dao.Impl;

import com.flipkart.custom_exceptions.DatabaseException;
import com.flipkart.dao.DBConnection;
import com.flipkart.ProductCategory;
import com.flipkart.model.User;
import com.flipkart.model.Wishlist;
import com.flipkart.product.Clothes;
import com.flipkart.product.Laptop;
import com.flipkart.product.Mobile;
import com.flipkart.product.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistDAOImpl {

    private static WishlistDAOImpl wishlistDAOInstance;

    private WishlistDAOImpl() {}

    public static WishlistDAOImpl getInstance() {
        if (null == wishlistDAOInstance) {
            wishlistDAOInstance = new WishlistDAOImpl();
        }

        return wishlistDAOInstance;
    }

    public boolean addToWishlist(final Product product, final User user) {
         try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into cart values(?,?,?)")) {
             preparedStatement.setInt(1, user.getUserId());
             preparedStatement.setInt(2, product.getProductId());
             preparedStatement.setString(3, product.getProductType());

             return preparedStatement.executeUpdate() > 0;
         } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public boolean removeFromWishlist(final Product item, final User user) {
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("delete from wishlist_products where user_id=? and product_id=?")) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setInt(2, item.getProductId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Wishlist getUserWishlist(final User user) {
        final Wishlist wishlist = new Wishlist(user);

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select user_id, product_id, p.product_type from wishlist_products w join products p on w.product_id = p.id  where user_id= ?")) {
            preparedStatement.setInt(1, user.getUserId());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int userId = resultSet.getInt(1);
                final int productId = resultSet.getInt(2);
                final String productType = resultSet.getString(3);

                if (ProductCategory.MOBILE.name().equalsIgnoreCase(productType)) {
                        try (PreparedStatement preparedStatement3 = DBConnection.getConnection().prepareStatement("select brand, model, price from mobiles where product_id=? ")) {
                            preparedStatement3.setInt(1, productId);
                            final ResultSet resultSet1 = preparedStatement3.executeQuery();
                        }

                        while (resultSet.next()) {
                            final String brand = resultSet.getString(1);
                            final String model = resultSet.getString(2);
                            final float price = resultSet.getFloat(3);

                            wishlist.addItemToWishlist(new Mobile(brand, model, price));
                        }
                    }

                    if (ProductCategory.LAPTOP.name().equalsIgnoreCase(productType)) {
                        try (PreparedStatement preparedStatement2 = DBConnection.getConnection().prepareStatement("select brand, model, price from laptops where product_id=? ")) {

                            preparedStatement2.setInt(1, productId);
                            final ResultSet resultSet2 = preparedStatement2.executeQuery();
                        }

                        while (resultSet.next()) {
                            final String brand = resultSet.getString(1);
                            final String model = resultSet.getString(2);
                            final float price = resultSet.getFloat(3);

                            wishlist.addItemToWishlist(new Laptop(brand, model, price));
                        }
                    }

                    if (ProductCategory.CLOTHES.name().equalsIgnoreCase(productType)) {
                        try (PreparedStatement preparedStatement1 = DBConnection.getConnection().prepareStatement("select type, brand, gender, size, price from clothes where product_id=? ")) {
                            preparedStatement1.setInt(1, productId);
                            final ResultSet resultSet3 = preparedStatement1.executeQuery();
                        }

                        while (resultSet.next()) {
                            final String clothesType = resultSet.getString(1);
                            final String brand = resultSet.getString(2);
                            final String gender = resultSet.getString(3);
                            final String size = resultSet.getString(4);
                            final float price = resultSet.getFloat(5);

                            wishlist.addItemToWishlist(new Clothes(clothesType, gender, size, price, brand));
                        }
                    }
                }

            return wishlist;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
