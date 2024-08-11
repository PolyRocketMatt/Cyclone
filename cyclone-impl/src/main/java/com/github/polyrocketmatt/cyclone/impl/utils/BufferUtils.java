package com.github.polyrocketmatt.cyclone.impl.utils;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;

public class BufferUtils {

    public static <T> String buildBuffer1D(CycloneBuffer<T> buffer) {
        StringBuilder builder = new StringBuilder();
        builder.append("| ");

        int threshold = 3;
        int size = buffer.size();
        boolean skip = size > threshold * 2;
        for (int i = 0; i < size; i++) {
            if (skip && (i > (threshold - 1) && i < size - threshold)) {
                if (i == threshold) {
                    builder.append("... ");
                }

                // Update the index to the last element
                if (i < size - threshold)
                    i = size - threshold - 1;

                continue;
            }

            builder.append(buffer.get(i));
            builder.append(" ");
        }

        builder.append("|");
        return builder.toString();
    }

}
