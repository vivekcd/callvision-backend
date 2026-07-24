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

   @GetMapping("/api/incoming-call")
public Caller getIncomingCall(@RequestParam String phone) {

    String cleanPhone = phone.replaceAll("\\D", "");

    List<Caller> callers = callerService.getAllCallers();

    for (Caller caller : callers) {

        String dbPhone = caller.getPhoneNumber().replaceAll("\\D", "");

        if (dbPhone.equals(cleanPhone)) {
            return caller;
        }
    }

    return null;
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