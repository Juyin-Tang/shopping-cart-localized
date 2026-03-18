package com.example.shoppingcart;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testAppWithEnglish() {
        String simulatedInput = "1\n2\n10.5\n3\n5.0\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        App.main(new String[0]);

        String output = outContent.toString();

        assertTrue(output.contains("Item 1 total:"));
        assertTrue(output.contains("Item 2 total:"));
        assertTrue(output.contains("Total cost of all items:"));

        assertTrue(output.contains("31.50") || output.contains("31,5"));  // 10.5 * 3 = 31.5
        assertTrue(output.contains("10.00") || output.contains("10,0"));  // 5.0 * 2 = 10.0
        assertTrue(output.contains("41.50") || output.contains("41,5"));  // 31.5 + 10 = 41.5
    }

    @Test
    public void testAppWithFinnish() {
        String simulatedInput = "2\n1\n15.0\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        App.main(new String[0]);

        String output = outContent.toString();

        assertTrue(output.contains("Tuotteen 1 kokonaishinta:"));
        assertTrue(output.contains("Kaikkien tuotteiden kokonaishinta:"));
        assertTrue(output.contains("30") && (output.contains("€") || output.contains("EUR") || output.contains("30,00")));
    }

    @Test
    public void testAppWithSwedish() {
        String simulatedInput = "3\n1\n20.0\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        App.main(new String[0]);

        String output = outContent.toString();
        assertTrue(output.contains("Vara 1 total kostnad:"));
        assertTrue(output.contains("Total kostnad för alla varor:"));
        assertTrue(output.contains("80") || output.contains("80,00"));  // 20 * 4 = 80
    }


    @Test
    public void testAppWithJapanese() {
        String simulatedInput = "4\n2\n100\n1\n200\n2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        outContent.reset();
        App.main(new String[0]);

        String output = outContent.toString();
        System.err.println("=== Japanese test output ===");
        System.err.println(output);

        assertTrue(output.contains("商品 1 の合計:"));
        assertTrue(output.contains("商品 2 の合計:"));
        assertTrue(output.contains("全商品の合計金額:"));
        assertTrue(output.contains("100") && output.contains("400") && output.contains("500"));
    }
}