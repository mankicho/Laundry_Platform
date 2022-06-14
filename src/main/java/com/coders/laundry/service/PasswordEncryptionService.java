package com.coders.laundry.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionService {

  public String encrypt(String password) {
    return "encrypted";
  }
}
