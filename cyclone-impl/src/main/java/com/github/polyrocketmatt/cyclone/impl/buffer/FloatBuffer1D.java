package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.api.buffer.BufferBase;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.buffer.dimension.Buffer1D;
import com.github.polyrocketmatt.cyclone.impl.utils.BufferUtils;
import org.jetbrains.annotations.NotNull;

public class FloatBuffer1D extends AbstractFloatBuffer implements Buffer1D {

    protected FloatBuffer1D(int size) {
        this(size, 0.0f);
    }

    protected FloatBuffer1D(int size, float value) {
        super(size, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <G extends BufferBase<Float>> @NotNull G as() {
        return (G) this;
    }

    @Override
    public @NotNull String info() {
        StringBuilder builder = new StringBuilder();
        builder.append("Buffer Type: ").append(CycloneBufferType.FLOAT.name()).append("\n");
        builder.append("Buffer Size: ").append(size).append("\n");
        builder.append("Buffer State: ").append(tmpIsMain).append("\n");
        builder.append("Buffer Planned Tasks: ").append(tasks.size()).append("\n");

        //  Build the MAIN buffer data
        builder.append("Buffer Data Main: ").append("\n\n");
        builder.append(BufferUtils.buildBuffer1D(this));
        builder.append("\n");

        return builder.toString();
    }
}
