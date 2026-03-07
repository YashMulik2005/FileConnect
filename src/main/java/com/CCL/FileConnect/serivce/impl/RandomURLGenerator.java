package com.CCL.FileConnect.serivce.impl;

import java.security.SecureRandom;

public class RandomURLGenerator {
    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final SecureRandom random = new SecureRandom();

    public static String generateURL(int size){
        StringBuilder url = new StringBuilder();

        for(int i=0;i<size;i++){
            int ind = random.nextInt(CHARACTERS.length());
            url.append(CHARACTERS.charAt(ind));
        }

        return url.toString();
    }
}
