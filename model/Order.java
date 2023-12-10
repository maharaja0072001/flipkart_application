package com.flipkart.model;

/**
 * <p>
 *     Represents a order placed by the user.
 *     Contains all the order related information.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class Order {
    private static int uniqueOrderId = 1;
    private final int id;
    private final int userId;
    private final int productId;
    private final int quantity;
    private final float totalAmount;
    private final String address;
    private final String paymentMode;
    private final String productName;

    private Order(final OrderBuilder orderBuilder) {
        this.productId = orderBuilder.quantity;
        this.userId = orderBuilder.userId;
        this.address = orderBuilder.address;
        this.quantity = orderBuilder.quantity;
        this.productName = orderBuilder.productName;
        this.totalAmount = orderBuilder.totalAmount;
        this.paymentMode = orderBuilder.paymentMode;
        this.id = orderBuilder.id;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getProductName() {
        return productName;
    }
    @Override
    public String toString() {
        return String.format("Order id :%d\nproduct name : %s\nproduct quantity : %d\ntotal amount : %.2f\nPayment mode : %s\nShipping address : %s",
                id, productName, quantity, totalAmount, paymentMode, address);
    }

    public static class OrderBuilder {
        private final int id;
        private final int userId;
        private final int productId;
        private int quantity;
        private float totalAmount;
        private String address;
        private final String paymentMode;
        private String productName;

        public OrderBuilder(final int userId, final int productId, final String paymentMode) {
           this.userId = userId;
           this.productId = productId;
           this.paymentMode = paymentMode;
           this.id = uniqueOrderId++;
        }

        public OrderBuilder setAddress(final String address) {
            this.address = address;
            return this;
        }

        public OrderBuilder setTotalAmount(final float totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public OrderBuilder setQuantity(final int quantity) {
            this.quantity = quantity;
            return this;
        }
        public OrderBuilder setProductName(final String productName) {
            this.productName = productName;
            return this;
        }

        public Order buildOrder() {
            return new Order(this) ;
        }
    }

}
