package com.jio.callvision.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Caller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String phoneNumber;
    private String callReason;
    private Boolean verified;
    private String logo;
    private String banner;
    private String videoRingback;  // ✅ ADD THIS

    // Default constructor (required by JPA)
    public Caller() {
    }

    // Constructor for inserting data
    public Caller(String phoneNumber, String companyName, Boolean verified, 
                  String callReason, String logo, String banner, String videoRingback) {  // ✅ ADD videoRingback
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.verified = verified;
        this.callReason = callReason;
        this.logo = logo;
        this.banner = banner;
        this.videoRingback = videoRingback;  // ✅ ADD THIS
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallReason() {
        return callReason;
    }

    public void setCallReason(String callReason) {
        this.callReason = callReason;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    // ✅ ADD GETTER AND SETTER FOR videoRingback
    public String getVideoRingback() {
        return videoRingback;
    }

    public void setVideoRingback(String videoRingback) {
        this.videoRingback = videoRingback;
    }
}