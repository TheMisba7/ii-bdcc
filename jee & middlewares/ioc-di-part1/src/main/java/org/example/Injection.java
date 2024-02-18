package org.example;

public enum Injection {
    STATIC(0), DYNAMIC(1), SPRING_XML(2), SPRING_ANNOTATION(3);

    private final int type;
    Injection(int type) {
        this.type = type;
    }

    public static Injection parseInt(int type) {
        for (Injection in: Injection.values()) {
            if (in.type == type)
                return in;
        }

        throw new UnsupportedInjectionType();
    }

    private static final class UnsupportedInjectionType extends RuntimeException {
        private static final String message = "could not parse int value";

        public UnsupportedInjectionType() {
            super(message);
        }
    }
}
