package com.example.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class LocalizationManagerTest {
    private LocalizationManager l10n;

    @BeforeEach
    void setUp() {
        l10n = new LocalizationManager();
    }

    @Test
    void testSetLanguageEnglish() {
        l10n.setLanguage(1);
        assertEquals(new Locale("en", "US"), l10n.getCurrentLocale());
        String msg = l10n.getMessage("item.count.prompt");
        assertTrue(msg.contains("Enter the number"));
    }

    @Test
    void testSetLanguageFinnish() {
        l10n.setLanguage(2);
        assertEquals(new Locale("fi", "FI"), l10n.getCurrentLocale());
        String msg = l10n.getMessage("item.count.prompt");
        assertTrue(msg.contains("Syötä ostettavien"));
    }

    @Test
    void testSetLanguageSwedish() {
        l10n.setLanguage(3);
        assertEquals(new Locale("sv", "SE"), l10n.getCurrentLocale());
        String msg = l10n.getMessage("item.count.prompt");
        assertTrue(msg.contains("Ange antalet"));
    }

    @Test
    void testSetLanguageJapanese() {
        l10n.setLanguage(4);
        assertEquals(new Locale("ja", "JP"), l10n.getCurrentLocale());
        String msg = l10n.getMessage("item.count.prompt");
        assertTrue(msg.contains("購入する商品の数"));
    }

    @Test
    void testGetMessageWithArguments() {
        l10n.setLanguage(1);
        String msg = l10n.getMessage("item.price.prompt", 5);
        assertEquals("Enter the price for item 5:", msg);
    }
}