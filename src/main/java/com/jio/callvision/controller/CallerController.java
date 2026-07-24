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
        // Normalize the phone number by removing spaces, dashes, and plus signs
        String normalizedPhone = phone.replaceAll("[\\s-]", "");
        
        // Try to find by normalized phone
        Caller caller = callerService.getCallerByPhoneNumber(normalizedPhone);
        
        if (caller != null) {
            return caller;
        }
        
        // Try with the original phone number (in case it has spaces)
        if (!phone.equals(normalizedPhone)) {
            caller = callerService.getCallerByPhoneNumber(phone);
            if (caller != null) {
                return caller;
            }
        }
        
        // Remove country code and try partial match
        String phoneWithoutCountryCode = normalizedPhone.replaceFirst("^\\+91", "");
        if (!phoneWithoutCountryCode.isEmpty() && !phoneWithoutCountryCode.equals(normalizedPhone)) {
            List<Caller> allCallers = callerService.getAllCallers();
            for (Caller c : allCallers) {
                String dbPhone = c.getPhoneNumber().replaceAll("[\\s-]", "");
                if (dbPhone.contains(phoneWithoutCountryCode) || 
                    phoneWithoutCountryCode.contains(dbPhone.replaceFirst("^\\+91", ""))) {
                    return c;
                }
            }
        }
        
        // Only return random as last resort, but log it
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