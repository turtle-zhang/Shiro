package com.tutle.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 测试Mshiro的 md5操作
 */
public class TestMd5 {
    public static void main(String[] args) {
        // 注意：这里要把需要加密的值放在构造函数中，不能通过.set 设置，会无效
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println("普通的md5加密值：" + md5Hash.toHex());

        Md5Hash md5Hash1 = new Md5Hash("123", "X0*7ps");
        System.out.println("加盐的md5加密值：" + md5Hash1.toHex());

        Md5Hash md5Hash2 = new Md5Hash("123", "X0*7ps", 1024);
        System.out.println("加盐和散列的md5加密值：" + md5Hash2.toHex());
    }
}
