package Model;

import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;

public class TestHash extends TestCase {

    public void testHash() throws NoSuchAlgorithmException {
        Hash temp = new Hash("testing");
        assertEquals("dc724af18fbdd4e59189f5fe768a5f8311527050", temp.generateHash());
        // testing for most common passwords
        temp = new Hash("12345678");
        assertEquals("7c222fb2927d828af22f592134e8932480637c0d", temp.generateHash());
        temp = new Hash("qwerty");
        assertEquals("b1b3773a05c0ed0176787a4f1574ff0075f7521e", temp.generateHash());
        temp = new Hash("qwerty123");
        assertEquals("5cec175b165e3d5e62c9e13ce848ef6feac81bff", temp.generateHash());
        temp = new Hash("password");
        assertEquals("5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", temp.generateHash());
    }
}
