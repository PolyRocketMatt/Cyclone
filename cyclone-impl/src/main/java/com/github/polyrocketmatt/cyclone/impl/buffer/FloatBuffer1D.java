package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import org.jetbrains.annotations.NotNull;

public class FloatBuffer1D extends AbstractFloatBuffer {

    protected FloatBuffer1D(int size) {
        this(size, 0.0f);
    }

    protected FloatBuffer1D(int size, float value) {
        super(size, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <G extends CycloneBuffer<Float>> @NotNull G as() {
        return (G) this;
    }

    @Override
    public @NotNull String info() {
        StringBuilder builder = new StringBuilder();
        builder.append("Buffer Type: ").append(CycloneBufferType.FLOAT.name()).append("\n");
        builder.append("Buffer Size: ").append(size).append("\n");
        builder.append("Buffer Data: ").append("\n\n");

        builder.append("| ");
        int threshold = 3;
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

            builder.append(get(i));
            builder.append(" ");
        }

        builder.append("|");
        return builder.toString();
    }
}
