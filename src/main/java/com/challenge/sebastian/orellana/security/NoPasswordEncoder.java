package com.challenge.sebastian.orellana.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public final class NoPasswordEncoder implements PasswordEncoder {

    private static final PasswordEncoder INSTANCE = new NoPasswordEncoder();

    private NoPasswordEncoder() {
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
