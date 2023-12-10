package com.flipkart.dao.Impl;

import com.flipkart.custom_exceptions.DatabaseException;
import com.flipkart.custom_exceptions.DuplicateEntryException;
import com.flipkart.dao.DBConnection;
import com.flipkart.ProductCategory;
import com.flipkart.product.Product;
import com.flipkart.product.Clothes;
import com.flipkart.product.Laptop;
import com.flipkart.product.Mobile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into product (product_category, brand ,price) values(?,?,?) returning id")) {
                preparedStatement.setString(1, product.getProductType());
                preparedStatement.setString(2, product.getBrandName());
                preparedStatement.setFloat(3, product.getPrice());

                final ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                productId = resultSet.getInt(1) ;

            } catch (SQLException e) {
                throw new DuplicateEntryException(e.getMessage());
            }

            String query = null ;
            switch (ProductCategory.valueOf(product.getProductType().toUpperCase())) {
                case MOBILE:
                case LAPTOP:
                    query = "insert into electronics_inventory(product_id, model) values(?, ?)" ;
                    break;
                case CLOTHES:
                    query = "insert into clothes(product_id,type, gender, size) values(?, ?, ?, ?)";
            }
            try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                switch (ProductCategory.valueOf(product.getProductType().toUpperCase())) {
                    case MOBILE:
                        preparedStatement.setString(2, ((Mobile) product).getModel());
                        break;
                    case LAPTOP:
                        preparedStatement.setString(2, ((Laptop) product).getModel());
                        break;
                    case CLOTHES:
                        preparedStatement.setString(2, ((Clothes) product).getClothesType());
                        preparedStatement.setString(3, ((Clothes) product).getGender());
                        preparedStatement.setString(4, ((Clothes) product).getSize());
                }
            } catch (SQLException exception) {
                throw new DatabaseException(exception.getMessage());
            }
        }
    }

//    public void addItemToInventory(final List<Product> products) {
//        int productId;
//        for (final Product product : products) {
//            try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into product (product_type) values(?) returning id")) {
//                preparedStatement.setString(1, product.getProductType());
//                final ResultSet resultSet = preparedStatement.executeQuery();
//
//                resultSet.next();
//                productId = resultSet.getInt(1) ;
//            } catch (SQLException exception) {
//                throw new DuplicateEntryException(exception.getMessage());
//            }
//
//            if (ProductCategory.MOBILE.name().equals(product.getProductType().toUpperCase())) {
//                addItemsToMobiles((Mobile) product, productId);
//            }
//
//            if (ProductCategory.LAPTOP.name().equals(product.getProductType().toUpperCase())) {
//                addItemsToLaptops((Laptop) product, productId);
//            }
//
//            if (ProductCategory.CLOTHES.name().equals(product.getProductType().toUpperCase())) {
//                addItemsToClothes((Clothes) product, productId);
//            }
//        }
//    }

    public void removeItemFromInventory(final Product product) {
        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("delete from products where id = ?")) {

            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

//    private void addItemsToMobiles(final Mobile mobile, final int productId) {
//        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into mobiles(product_id, brand, model, price) values(?, ?, ?, ?)")) {
//
//            preparedStatement.setInt(1, productId);
//            preparedStatement.setString(2, mobile.getBrandName());
//            preparedStatement.setString(3, mobile.getModel());
//            preparedStatement.setFloat(4, mobile.getPrice());
//            preparedStatement.executeUpdate();
//        } catch (SQLException exception) {
//            throw new DuplicateEntryException(exception.getMessage());
//        }
//    }
//
//    private void addItemsToLaptops(final Laptop laptop, final int productId) {
//        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into laptops(product_id, brand, model, price) values(?, ?, ?, ?)")) {
//
//            preparedStatement.setInt(1, productId);
//            preparedStatement.setString(2, "laptop.getBrandName()");
//            preparedStatement.setString(3, laptop.getModel());
//            preparedStatement.setFloat(4, laptop.getPrice());
//            preparedStatement.executeUpdate();
//        } catch (SQLException exception) {
//            throw new DuplicateEntryException(exception.getMessage());
//        }
//    }
//
//    private void addItemsToClothes(final Clothes clothes, final int productId) {
//        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into clothes(product_id, type, brand, gender, size, price) values(?, ?, ?, ?, ?, ?)")) {
//
//            preparedStatement.setInt(1, productId);
//            preparedStatement.setString(2, clothes.getClothesType());
//            preparedStatement.setString(3, clothes.getBrandName());
//            preparedStatement.setString(4, clothes.getGender());
//            preparedStatement.setString(5, clothes.getSize());
//            preparedStatement.setFloat(6, clothes.getPrice());
//            preparedStatement.executeUpdate();
//        } catch (SQLException exception) {
//            throw new DuplicateEntryException(exception.getMessage());
//        }
//    }

    public List<Product> getMobileItems() {
        final List<Product> mobileCollection = new ArrayList<>();

        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select p.brand, e.model, p.price, p.id from electronics_inventory e join product p on p.id = e.product_id where p.product_category='Mobile'")) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final String brand = resultSet.getString(1);
                final String model = resultSet.getString(2);
                final float price = resultSet.getFloat(3);
                final int productId = resultSet.getInt(4);
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

        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select p.brand, e.model, p.price, p.id from electronics_inventory e join product p on p.id = e.product_id where p.product_category = 'Laptop'")) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final String brand = resultSet.getString(1);
                final String model = resultSet.getString(2);
                final float price = resultSet.getFloat(3);
                final int product_id = resultSet.getInt(4);
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

        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select c.clothes_type ,p.brand, c.gender, c.size, p.price, p.id from clothes_inventory c join product p on p.id = c.product_id where p.product_category = 'Clothes'")) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final String clothesType = resultSet.getString(1);
                final String brand = resultSet.getString(2);
                final String gender = resultSet.getString(3);
                final String size = resultSet.getString(4);
                final float price = resultSet.getFloat(5);
                final int productId = resultSet.getInt(6);
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
