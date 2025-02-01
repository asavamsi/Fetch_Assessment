package com.receipt.processor.service;

import com.receipt.processor.model.Receipt;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class ReceiptService {
    private final Map<String, Receipt> store = new HashMap<>();

    public Map<String, String> processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        store.put(id, receipt);
        return Collections.singletonMap("id", id);
    }

    public Map<String, Integer> getPoints(String id) {
        Receipt receipt = store.get(id);
        if (receipt == null) {
            throw new NoSuchElementException("Receipt not found");
        }
        return Collections.singletonMap("points", calculatePoints(receipt));
    }

    private int calculatePoints(Receipt receipt) {
        int points = receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
        if (receipt.getTotal().endsWith(".00")) points += 50;
        double total = Double.parseDouble(receipt.getTotal());
        if (total % 0.25 == 0) points += 25;
        points += (receipt.getItems().size() / 2) * 5;
        for (var item : receipt.getItems()) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                points += (int) Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }
        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 == 1) points += 6;
        int hour = Integer.parseInt(receipt.getPurchaseTime().split(":")[0]);
        if (hour == 14) points += 10;
        return points;
    }
}