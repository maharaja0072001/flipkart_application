package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.model.User;
import com.flipkart.product.clothes.ClothesInventory;
import com.flipkart.product.laptop.LaptopInventory;
import com.flipkart.product.mobile.MobileInventory;
import java.util.Scanner;

public class HomePageView {

    private static final Scanner SCANNER = InputHandler.getScanner();
    private static final MobileInventory MOBILE_INVENTORY = MobileInventory.getInstance();
    private static final LaptopInventory LAPTOP_INVENTORY = LaptopInventory.getInstance();
    private static final ClothesInventory CLOTHES_INVENTORY = ClothesInventory.getInstance();
    private static final CartView CART_VIEW = CartView.getInstance();
    private static final WishlistView WISHLIST_VIEW = WishlistView.getInstance();
    private static final FilterMenuView FILTER_MENU_VIEW = FilterMenuView.getFilterMenuViewInstance();
    private static HomePageView homePageViewInstance ;

    private HomePageView() {}

    static {
        MOBILE_INVENTORY.addToInventory();
        LAPTOP_INVENTORY.addToInventory();
        CLOTHES_INVENTORY.addToInventory();
    }

    public static synchronized HomePageView getHomePageViewInstance() {
        if (homePageViewInstance == null) {
            homePageViewInstance = new HomePageView();
        }
        return homePageViewInstance;
    }

    public void showHomePage(final User user) {
        int input;
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("1.Mobiles\n2.Laptops\n3.Clothes\n4.Cart\n5.Remove from cart\n6.Wishlist\n7.Remove from wishlist\n8.Logout");
                input = Integer.parseInt(SCANNER.nextLine().trim());

                switch (input) {
                    case 1:
                        FILTER_MENU_VIEW.showFilter(user, "Mobile");
                        break;
                    case 2:
                        FILTER_MENU_VIEW.showFilter(user, "Laptop");
                        break;
                    case 3:
                        FILTER_MENU_VIEW.showFilter(user, "Clothes");
                        break;
                    case 4:
                        CART_VIEW.viewCart(user);
                        break;
                    case 5:
                        CART_VIEW.removeItemFromCart(user);
                        break;
                    case 6:
                        WISHLIST_VIEW.viewWishlist(user);
                        break;
                    case 7:
                        WISHLIST_VIEW.removeItemFromWishlist(user);
                        break;
                    case 8:
                        System.out.println("Logged out successfully");
                        exit = true;
                        break;
                    default:
                        System.out.println("Enter a valid choice!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input is invalid.Enter a number!");
            }
        }
    }
}