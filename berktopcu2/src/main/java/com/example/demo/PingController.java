package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
/*
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/auth")
    public ResponseEntity<String> pingMongoDB() {
        try {
            // Check if MongoDB is available by running a simple command
            mongoTemplate.getDb().runCommand(new org.bson.Document("ping", 1));
            return ResponseEntity.ok("Hello World! MongoDB connection is established.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to connect to MongoDB: " + e.getMessage());
        }
    }
*/}
