package Quiz.Tests;

import Quiz.Hasher;
import junit.framework.TestCase;


public class TestHash extends TestCase {

    public void testHash() {
        assertEquals("dc724af18fbdd4e59189f5fe768a5f8311527050", Hasher.generateHash("testing"));
        // testing for most common passwords
        assertEquals("7c222fb2927d828af22f592134e8932480637c0d", Hasher.generateHash("12345678"));
        assertEquals("b1b3773a05c0ed0176787a4f1574ff0075f7521e", Hasher.generateHash("qwerty"));
        assertEquals("5cec175b165e3d5e62c9e13ce848ef6feac81bff", Hasher.generateHash("qwerty123"));
        assertEquals("5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", Hasher.generateHash("password"));
    }
}
