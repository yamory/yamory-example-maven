package io.yamory.common;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GreeterTest {
    @Test
    public void greetsByName() {
        assertEquals("Hello, World!", new Greeter().greet("World"));
    }
}
