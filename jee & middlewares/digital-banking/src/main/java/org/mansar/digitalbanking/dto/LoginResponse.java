package org.mansar.digitalbanking.dto;

public record LoginResponse(String token, long expiresIn) { }
