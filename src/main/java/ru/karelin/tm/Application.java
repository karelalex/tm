package ru.karelin.tm;

import java.security.NoSuchAlgorithmException;

public class Application {

    public static void main(String[] args) {
        MD5Generator mg =new MD5Generator();
        String s = "popa";
        System.out.println(mg.generate(s.toCharArray()));
        new Bootstrap().init();

    }
}
