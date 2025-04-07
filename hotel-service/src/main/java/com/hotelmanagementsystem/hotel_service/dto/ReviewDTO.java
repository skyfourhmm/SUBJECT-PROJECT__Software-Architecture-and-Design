package com.hotelmanagementsystem.hotel_service.dto;

import java.time.LocalDateTime;

public class ReviewDTO {
    private String username;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    public ReviewDTO() {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
