package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.api.CycloneApiTest;

public class CycloneImplTest implements CycloneApiTest {

    public static void main(String[] args) {
        new CycloneImplTest().testMe();
    }

    @Override
    public void testMe() {
        System.out.println("Test me!");
    }
}
