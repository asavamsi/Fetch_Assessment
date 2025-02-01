package com.receipt.processor.controller;

import com.receipt.processor.model.Receipt;
import com.receipt.processor.service.ReceiptService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt) {
        return receiptService.processReceipt(receipt);
    }

    @GetMapping("/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable String id) {
        return receiptService.getPoints(id);
    }
}
