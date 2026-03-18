package com.example.shoppingcart;

import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalizationManager l10n = new LocalizationManager();

        System.out.println("1: English, 2: Finnish, 3: Swedish, 4: Japanese");
        System.out.print("Select language (1-4): ");
        int langChoice = scanner.nextInt();
        l10n.setLanguage(langChoice);

        System.out.print(l10n.getMessage("item.count.prompt"));
        int itemCount = scanner.nextInt();

        ShoppingCart cart = new ShoppingCart();

        for (int i = 1; i <= itemCount; i++) {
            System.out.print(l10n.getMessage("item.price.prompt", i));
            double price = scanner.nextDouble();
            System.out.print(l10n.getMessage("item.quantity.prompt", i));
            int quantity = scanner.nextInt();
            cart.addItem(price, quantity);
        }

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(l10n.getCurrentLocale());

        int index = 1;
        for (ShoppingCart.Item item : cart.getItems()) {
            System.out.println(l10n.getMessage("item.total", index, currencyFormatter.format(item.getTotal())));
            index++;
        }

        double total = cart.calculateTotal();
        System.out.println(l10n.getMessage("cart.total", currencyFormatter.format(total)));

        scanner.close();
    }
}