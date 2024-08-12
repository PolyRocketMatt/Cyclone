package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.api.buffer.BufferBase;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.buffer.dimension.Buffer1D;
import com.github.polyrocketmatt.cyclone.impl.utils.BufferUtils;
import com.github.polyrocketmatt.cyclone.impl.utils.TypeUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FloatBuffer1D extends AbstractFloatBuffer implements Buffer1D {

    protected FloatBuffer1D(int size, float value) {
        super(size, value);
    }

    private FloatBuffer1D(int size, List<Float> values) {
        super(size, values);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <G extends BufferBase<Float>> @NotNull G as() {
        return (G) this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> filter(Predicate<Float> predicate) {
        List<Float> filtered = new ArrayList<>();
        TypeUtils.toFloatStream(getMain()).parallel().filter(predicate).forEach(filtered::add);
        return new FloatBuffer1D(filtered.size(), filtered);
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
