package com.jio.callvision.service;

import com.jio.callvision.entity.Caller;
import com.jio.callvision.repository.CallerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CallerService {

    private final CallerRepository repository;

    public CallerService(CallerRepository repository) {
        this.repository = repository;
    }

    public Caller getRandomCaller() {
        List<Caller> callers = repository.findAll();

        if (callers.isEmpty()) {
            return null;
        }

        return callers.get(new Random().nextInt(callers.size()));
    }
    public List<Caller> getAllCallers() {
        return repository.findAll();
    }

    public Caller saveCaller(Caller caller) {
        return repository.save(caller);
    }

    public Caller updateCaller(Long id, Caller caller) {
        caller.setId(id);
        return repository.save(caller);
    }

    public void deleteCaller(Long id) {
        repository.deleteById(id);
    }
   
    public Caller getCallerByPhoneNumber(String phoneNumber) {
    return repository.findByPhoneNumber(phoneNumber)
            .orElse(null);
    }

}