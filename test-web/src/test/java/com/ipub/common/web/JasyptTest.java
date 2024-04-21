package com.ipub.common.web;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JasyptTest
 *
 * @author wen.zhou
 * @since 2024/4/21
 */
class JasyptTest {
    @Test
    void enc() {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("atpingan");
        String userName = encryptor.encrypt("ipub");
        String passWord = encryptor.encrypt("Zhouwen0510");
        System.out.println("userName:" + userName);
        System.out.println("passWord:" + passWord);
        Assertions.assertEquals("ipub", encryptor.decrypt(userName));
        Assertions.assertEquals("Zhouwen0510", encryptor.decrypt(passWord));
    }
}
