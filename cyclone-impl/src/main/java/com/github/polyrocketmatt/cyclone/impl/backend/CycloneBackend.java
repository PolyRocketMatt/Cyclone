package com.github.polyrocketmatt.cyclone.impl.backend;

import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TornadoBackend;
import uk.ac.manchester.tornado.api.common.TornadoDevice;
import uk.ac.manchester.tornado.api.runtime.TornadoRuntimeProvider;

public class CycloneBackend {

    private final TornadoBackend backend;
    private final TornadoDevice device;
    private final String name;

    public CycloneBackend(int index) {
        this.backend = TornadoRuntimeProvider.getTornadoRuntime().getBackend(index);
        this.device = backend.getDevice(0);
        this.name = backend.getName();
    }

    public @NotNull TornadoDevice getDevice() {
        return device;
    }

    public @NotNull String getBackendName() {
        return name;
    }
}
