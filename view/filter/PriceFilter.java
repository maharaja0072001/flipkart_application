package com.flipkart.view.filter;

import com.flipkart.InputHandler;
import com.flipkart.product.ProductImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PriceFilter implements ProductFilter {

    private static final List<ProductImpl> FILTERED_ITEMS = new ArrayList<>();
    private static PriceFilter priceFilterInstance;

    private PriceFilter() {}

    public static synchronized PriceFilter getInstance() {
        if (priceFilterInstance == null) {
            priceFilterInstance = new PriceFilter();
        }
        return priceFilterInstance;
    }

    public List<ProductImpl> getFilteredItems(final Map<String, ArrayList<ProductImpl>> inventory, final String productType) {
        final Scanner scanner = InputHandler.getScanner();
        String fromAmount;
        String toAmount;

        while (true) {
            System.out.println("Enter Minimum amount : [Enter '%' to go back]");
            fromAmount = scanner.nextLine().trim();

            if (fromAmount.equals("%")) {
                return null;
            }

            if (!validateAmount(fromAmount)) {
                System.out.println("Input is invalid");
                continue;
            }
            System.out.println("Enter Maximum amount : [Enter '%' to go back]");
            toAmount = scanner.nextLine().trim();

            if (toAmount.equals("%")) {
                return null;
            }

            if (!validateAmount(toAmount)) {
                System.out.println("Input is invalid");
                continue;
            }

            if (toAmount.compareTo(fromAmount) < 0) {
                System.out.println("Maximum amount is smaller than minimum amount");
            } else {
                break;
            }
        }
        FILTERED_ITEMS.clear();
        final List<ProductImpl> products = inventory.get(productType);

            for (final ProductImpl product : products) {
                if ((product.getPrice() >= Integer.parseInt(fromAmount))
                        && (product.getPrice() <= Integer.parseInt(toAmount))) {
                    FILTERED_ITEMS.add(product);
                }
            }

        if (FILTERED_ITEMS.isEmpty()) {
            System.out.println("No items found!");
        }
        return FILTERED_ITEMS;
    }

    private boolean validateAmount(final String input) {
        return Pattern.matches("^\\d+$", input);
    }
}