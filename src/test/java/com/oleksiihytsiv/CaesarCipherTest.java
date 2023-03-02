package com.oleksiihytsiv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.oleksiihytsiv.algorithms.CaesarCipher;
import com.oleksiihytsiv.algorithms.CryptoAlgorithm;
import com.oleksiihytsiv.attack.CaesarCipherAttack;

public class CaesarCipherTest {
    CryptoAlgorithm cryptoAlgorithm = new CaesarCipher();

    @Test
    public void testEncrypt() {
        String text = "Hello World";
        int key = 3;
        String expected = "Khoor#Zruog";
        String actual = cryptoAlgorithm.encrypt(text, key);
        assertEquals(expected, actual);
    }

    @Test
    public void testDecrypt() {
        String expected = "Hello World";
        int key = 3;
        String text = "Khoor#Zruog";
        String actual = cryptoAlgorithm.decrypt(text, key);
        assertEquals(expected, actual);
    }

    @Test
    public void testAttack() {
        CaesarCipherAttack c = new CaesarCipherAttack();
        String text = "Hello World";
        int key = 3;
        String actual = cryptoAlgorithm.encrypt(text, key);
        var key1 = c.attack(text, actual);
        assertEquals(key, key1.getKey());
    }
}
