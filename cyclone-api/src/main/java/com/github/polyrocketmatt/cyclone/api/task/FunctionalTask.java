package com.github.polyrocketmatt.cyclone.api.task;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;

public interface FunctionalTask extends BufferTask {

    void resolve(@NotNull TaskGraph graph, @NotNull CycloneBufferType type);

}
