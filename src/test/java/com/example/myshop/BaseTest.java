package com.example.myshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    @BeforeEach
    protected abstract void setUp();

    /**
     * Clean the data after test
     */
    @AfterEach
    protected void setupAfterEach() {
        cleanAfterEach();
    }

    /**
     * Clean test data after each test
     */
    protected abstract void cleanAfterEach();
}
