package com.sfe.superHero.Exception;

public class SuperHeroException extends RuntimeException {
    public SuperHeroException(String message) {
        super(message);
    }

    public SuperHeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
