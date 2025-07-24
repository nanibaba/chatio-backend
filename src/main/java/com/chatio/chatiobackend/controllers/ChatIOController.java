package com.chatio.chatiobackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatIOController {
  @GetMapping("/")
  public String getHealth() {
    return "healthy";
  }
}
