package com.jio.callvision.repository;

import com.jio.callvision.entity.Caller;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CallerRepository extends JpaRepository<Caller, Long> {
        Optional<Caller> findByPhoneNumber(String phoneNumber);
}