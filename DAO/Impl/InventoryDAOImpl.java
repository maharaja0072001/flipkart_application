package com.flipkart.dao.Impl;

import com.flipkart.custom_exceptions.DatabaseException;
import com.flipkart.dao.DBConnection;
import com.flipkart.ProductCategory;
import com.flipkart.model.product.Product;
import com.flipkart.model.product.Clothes;
import com.flipkart.model.product.Laptop;
import com.flipkart.model.product.Mobile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Responsible for storing all the products in the database.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class InventoryDAOImpl {

    private static InventoryDAOImpl inventoryDAOInstance;

    private InventoryDAOImpl() {}

    public static synchronized InventoryDAOImpl getInstance() {
        if (null == inventoryDAOInstance) {
            inventoryDAOInstance = new InventoryDAOImpl();
        }

        return inventoryDAOInstance;
    }

    public void addItemToInventory(final List<Product> products) {
        int productId;

        for (final Product product : products) {
            try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into product (product_category, price) values(?,?) returning id")) {
                preparedStatement.setString(1, product.getProductType());
                preparedStatement.setFloat(2, product.getPrice());

                final ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                productId = resultSet.getInt(1) ;

            } catch (SQLException exception) {
                System.out.println("continuing for ");
                continue;
                //throw new DuplicateEntryException(e.getMessage());
            }
            String query = null ;

            switch (ProductCategory.valueOf(product.getProductType().toUpperCase())) {
                case MOBILE:
                case LAPTOP:
                    query = "insert into electronics_inventory(product_id, brand, model) values(?, ?, ?)" ;
                    break;
                case CLOTHES:
                    query = "insert into clothes_inventory(product_id, brand, clothes_type, gender, size) values(?, ?, ?, ?, ?)";
                    break;
            }
            try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                preparedStatement.setString(2,product.getBrandName());

                switch (ProductCategory.valueOf(product.getProductType().toUpperCase())) {
                    case MOBILE:
                        preparedStatement.setString(3, ((Mobile) product).getModel());
                        break;
                    case LAPTOP:
                        preparedStatement.setString(3, ((Laptop) product).getModel());
                        break;
                    case CLOTHES:
                        preparedStatement.setString(3, ((Clothes) product).getClothesType());
                        preparedStatement.setString(4, ((Clothes) product).getGender());
                        preparedStatement.setString(5, ((Clothes) product).getSize());
                }
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
            }
        }
    }

    public void removeItemFromInventory(final Product product) {
        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("delete from product where id = ?")) {

            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public List<Product> getMobileItems() {
        final List<Product> mobileCollection = new ArrayList<>();

        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select p.id, e.brand, e.model, p.price from electronics_inventory e join product p on p.id = e.product_id where p.product_category='Mobile'")) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int productId = resultSet.getInt(1);
                final String brand = resultSet.getString(2);
                final String model = resultSet.getString(3);
                final float price = resultSet.getFloat(4);
                final Mobile mobile = new Mobile(brand, model, price);

                mobile.setProductId(productId);
                mobileCollection.add(mobile);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return mobileCollection;
    }

    public List<Product> getLaptopItems() {
        final List<Product> laptopCollection = new ArrayList<>();

        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select p.id, e.brand, e.model, p.price from electronics_inventory e join product p on p.id = e.product_id where p.product_category='Laptop'")) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int product_id = resultSet.getInt(1);
                final String brand = resultSet.getString(2);
                final String model = resultSet.getString(3);
                final float price = resultSet.getFloat(4);
                final Laptop laptop = new Laptop(brand, model, price);

                laptop.setProductId(product_id);
                laptopCollection.add(laptop);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return laptopCollection;
    }

    public List<Product> getClothesItems() {
        final List<Product> clothesCollection = new ArrayList<>();

        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select p.id, c.clothes_type ,c.brand, c.gender, c.size, p.price from clothes_inventory c join product p on p.id = c.product_id where p.product_category = 'Clothes'")) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final int productId = resultSet.getInt(1);
                final String clothesType = resultSet.getString(2);
                final String brand = resultSet.getString(3);
                final String gender = resultSet.getString(4);
                final String size = resultSet.getString(5);
                final float price = resultSet.getFloat(6);
                final Clothes clothes = new Clothes(clothesType, gender, size, price, brand);

                clothes.setProductId(productId);
                clothesCollection.add(clothes);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return clothesCollection;
    }
}
