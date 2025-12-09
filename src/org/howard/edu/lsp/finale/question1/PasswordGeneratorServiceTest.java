package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordGeneratorServiceTest {
    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "Service instance should not be null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertNotNull(second);
        assertSame(service, second, "Both variables should point to the same Singleton instance");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        try {
            java.lang.reflect.Field field = PasswordGeneratorService.class.getDeclaredField("strategy");
            field.setAccessible(true);
            field.set(service, null);
        } catch (Exception e) {
            fail("Failed to reset strategy for testing exception logic");
        }

        assertThrows(IllegalStateException.class, () -> {
            service.generatePassword(10);
        }, "Should throw IllegalStateException if no algorithm is set");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        assertEquals(10, p.length(), "Password length should be 10");
        assertTrue(p.matches("[0-9]+"), "Basic password should contain digits only");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        assertEquals(12, p.length(), "Password length should be 12");
        assertTrue(p.matches("[A-Za-z0-9]+"), "Enhanced password should contain alphanumeric chars");
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        assertEquals(8, p.length(), "Password length should be 8");
        assertTrue(p.matches("[A-Za-z]+"), "Letters password should contain letters only");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertTrue(p1.matches("[0-9]+"), "First switch should be basic (digits)");

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertTrue(p2.matches("[A-Za-z]+"), "Second switch should be letters only");

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Third switch should be enhanced");
    }
}