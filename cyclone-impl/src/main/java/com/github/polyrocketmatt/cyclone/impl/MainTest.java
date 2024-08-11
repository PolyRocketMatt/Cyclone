package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.api.buffer.ArithmeticBuffer;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.impl.buffer.CycloneBufferFactory;
import com.github.polyrocketmatt.cyclone.impl.buffer.CycloneBufferType;

public class MainTest {

    public static void main(String[] args) {
        CycloneBuffer<Float> a = CycloneBufferFactory.construct(CycloneBufferType.FLOAT, 1, 1024);
        CycloneBuffer<Float> b = CycloneBufferFactory.construct(CycloneBufferType.FLOAT, 1, 1024);

        a.fill(4.0f);
        b.fill(2.0f);

        a.<ArithmeticBuffer<Float>>as().div(b);

        System.out.println(a.info());
    }

}
