package utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    public static void main(String[] args) 
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // List of raw passwords to encode
        String[] rawPasswords = {"password1", "password2"};

        for (String raw : rawPasswords) 
        {
            String encoded = encoder.encode(raw);
            System.out.println(raw + " -> " + encoded);
        }
    }
}