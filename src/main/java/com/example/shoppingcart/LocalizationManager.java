package com.example.shoppingcart;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private ResourceBundle bundle;
    private Locale currentLocale;

    public void setLanguage(int choice) {
        switch (choice) {
            case 1: currentLocale = new Locale("en", "US"); break;
            case 2: currentLocale = new Locale("fi", "FI"); break;
            case 3: currentLocale = new Locale("sv", "SE"); break;
            case 4: currentLocale = new Locale("ja", "JP"); break;
            default: currentLocale = Locale.US;
        }
        bundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public String getMessage(String key, Object... args) {
        String pattern = bundle.getString(key);
        return MessageFormat.format(pattern, args);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}