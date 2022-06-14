package com.coders.laundry.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TokenServiceTest {

  TokenService tokenService = new TokenService();

  @Test
  void issue() {
    String expected = "issue";

    String token = tokenService.issue();

    assertEquals(expected, token);
  }
}
