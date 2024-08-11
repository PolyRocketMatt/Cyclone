package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.api.ArithmeticBuffer;
import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;

public class MainTest {

    public static void main(String[] args) {
        CycloneBuffer<Float> buffer = CycloneBufferFactory.construct(CycloneBufferType.FLOAT, 1, 1024);

        buffer.<ArithmeticBuffer<Float>>as().add(4.0f);

        System.out.println(buffer.info());
    }

}
