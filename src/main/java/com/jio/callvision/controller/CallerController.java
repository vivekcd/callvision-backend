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
    // Remove all spaces, dashes, and plus signs
    String cleanPhone = phone.replaceAll("[\\s-+]", "");
    
    // Get all callers and find by phone number (ignoring spaces)
    List<Caller> allCallers = callerService.getAllCallers();
    for (Caller c : allCallers) {
        String dbPhone = c.getPhoneNumber().replaceAll("[\\s-+]", "");
        if (dbPhone.equals(cleanPhone) || dbPhone.endsWith(cleanPhone) || cleanPhone.endsWith(dbPhone)) {
            System.out.println("✅ Found: " + c.getCompanyName() + " for phone: " + phone);
            return c;
        }
    }
    
    // If no match found, return random
    System.out.println("⚠️ No caller found for: " + phone + ", returning random");
    return callerService.getRandomCaller();
}

    // Get all callers
    @GetMapping("/api/callers")
    public List<Caller> getAllCallers() {
        return callerService.getAllCallers();
    }

    // Create caller
    @PostMapping("/api/callers")
    public Caller createCaller(@RequestBody Caller caller) {
        return callerService.saveCaller(caller);
    }

    // Update caller
    @PutMapping("/api/callers/{id}")
    public Caller updateCaller(@PathVariable Long id,
                               @RequestBody Caller caller) {
        return callerService.updateCaller(id, caller);
    }

    // Delete caller
    @DeleteMapping("/api/callers/{id}")
    public void deleteCaller(@PathVariable Long id) {
        callerService.deleteCaller(id);
    }
    
    @GetMapping("/api/callers/search")
    public Caller getCallerByPhoneNumber(@RequestParam String phone) {
        return callerService.getCallerByPhoneNumber(phone);
    }
}