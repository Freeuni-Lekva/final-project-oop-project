package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    private final String password;

    public Hash (String password) {
        this.password = password;
    }

    public String hexToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (int aByte : bytes) {
            int val = aByte;
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    public String generateHash() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        return hexToString(md.digest());
    }
}
