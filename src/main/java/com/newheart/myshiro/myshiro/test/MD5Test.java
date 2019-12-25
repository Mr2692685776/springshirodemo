package com.newheart.myshiro.myshiro.test;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author hanjie
 * @date 2019/12/20 9:30
 */
public class MD5Test {
    public static void main(String[] args) {
        String hashName = "md5";
        String pwd ="123456";
        SimpleHash simpleHash = new SimpleHash(hashName, pwd, null, 1);
        System.out.println(simpleHash.toString());
    }
}
