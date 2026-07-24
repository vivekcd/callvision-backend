package com.jio.callvision.controller;

import com.jio.callvision.entity.Caller;
import com.jio.callvision.service.CallerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CallerController {

    private final CallerService callerService;

    public CallerController(CallerService callerService) {
        this.callerService = callerService;
    }

    // Existing API for Android app - FIXED
    @GetMapping("/api/incoming-call")
public Caller getIncomingCall(@RequestParam String phone) {
    // Remove ALL spaces, dashes, plus signs from incoming phone
    String cleanPhone = phone.replaceAll("[\\s-+]", "");
    
    // Get all callers
    List<Caller> allCallers = callerService.getAllCallers();
    
    // Find exact match after cleaning both sides
    for (Caller c : allCallers) {
        String dbPhone = c.getPhoneNumber().replaceAll("[\\s-+]", "");
        if (dbPhone.equals(cleanPhone)) {
            System.out.println("✅ Found: " + c.getCompanyName() + " for phone: " + phone);
            return c;
        }
    }
    
    // No match found
    System.out.println("⚠️ No caller found for: " + phone);
    return null;  // Return null instead of random
}
