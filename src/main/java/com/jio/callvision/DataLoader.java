package com.jio.callvision;

import com.jio.callvision.entity.Caller;
import com.jio.callvision.repository.CallerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CallerRepository repository;

    public DataLoader(CallerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 DataLoader run() called...");
        insertData();
    }

    @PostConstruct
    public void init() {
        System.out.println("🚀 DataLoader init() called...");
        insertData();
    }

    private void insertData() {
        if (repository.count() == 0) {
            System.out.println("📦 Inserting sample data...");
            
            repository.save(new Caller("+91 9876543210", "Amazon India", true, 
                "Package Delivery", 
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Amazon_logo.svg/2560px-Amazon_logo.svg.png", 
                "https://images.unsplash.com/photo-1523474253046-8cd2748b5fd2",
                null));  // ✅ Added null for videoRingback
            
            repository.save(new Caller("+91 9876543211", "Flipkart", true, 
                "Order Confirmation", 
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Flipkart_logo.svg/2560px-Flipkart_logo.svg.png", 
                "https://images.unsplash.com/photo-1556742049-0cfed4f6a45d",
                null));  // ✅ Added null
            
            repository.save(new Caller("+91 9876543212", "Zomato", true, 
                "Food Delivery", 
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Zomato_logo.svg/2560px-Zomato_logo.svg.png", 
                "https://images.unsplash.com/photo-1504674900247-0877df9cc836",
                null));  // ✅ Added null
            
            repository.save(new Caller("+91 9876543213", "Swiggy", true, 
                "Your Order is Ready", 
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Swiggy_logo.svg/2560px-Swiggy_logo.svg.png", 
                "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38",
                null));  // ✅ Added null
            
            repository.save(new Caller("+91 9876543214", "Uber", true, 
                "Your Ride is Here", 
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Uber_logo_2018.svg/2560px-Uber_logo_2018.svg.png", 
                "https://images.unsplash.com/photo-1546017335-7dba9f1c4cd0",
                null));  // ✅ Added null
            
            repository.save(new Caller("+91 9876543215", "Google", true, 
                "Security Alert", 
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/2560px-Google_2015_logo.svg.png", 
                "https://images.unsplash.com/photo-1579546929518-9e396f3cc809",
                null));  // ✅ Added null
            
            System.out.println("✅ Sample data inserted successfully!");
        } else {
            System.out.println("📊 Data already exists. Count: " + repository.count());
        }
    }
}