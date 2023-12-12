package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.OrderStatus;
import com.flipkart.PaymentMode;
import com.flipkart.controller.OrderController;
import com.flipkart.model.Order;
import com.flipkart.model.User;
import com.flipkart.model.product.Product;
import com.flipkart.view.datavalidation.UserDataValidator;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Responsible for viewing the orders placed by the user.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class OrderView {

    private static final UserDataValidator USER_DATA_VALIDATOR = UserDataValidator.getInstance();
    private static final OrderController ORDER_CONTROLLER = OrderController.getInstance();
    private final Scanner scanner = InputHandler.getScanner();
    private static OrderView orderViewInstance;

    /**
     * <p>
     * Default constructor of OrderView class. Kept private to restrict from creating object outside this class.
     * </p>
     */
    private OrderView() {}

    /**
     * <p>
     * Creates a single object of OrderView class and returns it.
     * </p>
     *
     * @return the single instance of OrderView class.
     */
    public static OrderView getInstance() {
        if (null == orderViewInstance) {
            orderViewInstance = new OrderView();
        }

        return orderViewInstance;
    }

    /**
     * <p>
     * Shows the order details of the user.
     * </p>
     *
     * @param user Refers {@link User} whose orders will be shown.
     */
    public void viewAndCancelOrder(final User user) {
        final List<Order> orders = ORDER_CONTROLLER.getOrders(user.getUserId());

        if (null == orders || orders.isEmpty()) {
            System.out.println("No orders found");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println(String.format("[%s]", orders.get(i)));
            }
        }
        final int index = getIndex(orders);

        if (0 == index) {
            return;
        }
        final Order order = orders.get(index - 1) ;

        order.setOrderStatus(OrderStatus.CANCELLED);
        System.out.println("Your order has been cancelled");
        System.out.println(order);
    }

    private int getIndex(final List<Order> orders) {

        System.out.println("Enter the index of order to cancel: [Press '$' to go back");
         try {
             final String index = scanner.nextLine();

             if (USER_DATA_VALIDATOR.containsToNavigateBack(index)) {
                 return 0;
             }

             if (orders.size() < Integer.parseInt(index) || Integer.parseInt(index) <= 0) {
                 System.out.println("Enter a valid index");
                 return getIndex(orders);
             }

             return Integer.parseInt(index);
         } catch (NumberFormatException exception) {
             System.out.println("Enter a valid index");
         }

        return getIndex(orders);
    }

    /**
     * <p>
     * Places the order for the user.
     * </p>
     *
     * @param product Refers the product to be placed.
     * @param user Refers the current {@link User}
     */
    public void placeOrder(final Product product, final User user){
        int productQuantity;

        System.out.println("Enter the quantity : [Press '$' to go back");
        final String quantity = scanner.nextLine().trim();

        if (USER_DATA_VALIDATOR.containsToNavigateBack(quantity)) {
            return;
        }

        try {
            productQuantity = Integer.parseInt(quantity);
        } catch (NumberFormatException exception) {
            System.out.println("Enter a valid quantity");
            placeOrder(product, user);
            return;
        }
        System.out.println("Enter your address");
        final String address = scanner.nextLine().trim();

        if (USER_DATA_VALIDATOR.containsToNavigateBack(address)) {
            return;
        }
        String paymentMode;
        
        loop :
        while (true) {
            System.out.println("Choose your payment mode : \n1.Cash On Delivery\n2.Credit or Debit card\n3.Net Banking\n4.UPI");
            final String choice = scanner.nextLine().trim();

            if (USER_DATA_VALIDATOR.containsToNavigateBack(choice)) {
                return;
            }

            try {
                switch (Integer.parseInt(choice)) {
                    case 1 :
                        paymentMode = PaymentMode.CASH_ON_DELIVERY.toString();
                        break loop;
                    case 2 :
                        paymentMode = PaymentMode.CREDIT_OR_DEBIT_CARD.toString();
                        break loop;
                    case 3 :
                        paymentMode = PaymentMode.NET_BANKING.toString();
                        break loop;
                    case 4 :
                        paymentMode = PaymentMode.UPI.toString();
                        break loop;
                    default:
                        System.out.println("Enter a valid choice");
                }
            } catch (NumberFormatException exception) {
               System.out.println("Choice is invalid. Enter a valid number");
           }
        }
        ORDER_CONTROLLER.addOrder(user.getUserId(), new Order.OrderBuilder(user.getUserId(), product.getProductId(), paymentMode).setAddress(address).setQuantity(productQuantity).setTotalAmount(productQuantity * product.getPrice()).setProductName(product.toString()).setOrderStatus(OrderStatus.PLACED).buildOrder());
        System.out.println("Order placed successfully");
    }
}
