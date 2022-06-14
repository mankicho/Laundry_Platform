package com.coders.laundry.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordEncryptionServiceTest {

  PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();

  @Test
  void encrypt() {
    String userPassword = "asljvb2398";
    String expected = "encrypted";

    String encryptedPassword = passwordEncryptionService.encrypt(userPassword);

    assertEquals(expected, encryptedPassword);
  }
}
