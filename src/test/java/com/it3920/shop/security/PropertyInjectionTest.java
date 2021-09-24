package com.it3920.shop.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.properties")
public class PropertyInjectionTest {
    @Test
    void testXXX(){
        Assertions.assertEquals(10, 10);
        Assertions.assertFalse(false);
    }
}
