package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.impl.backend.CycloneBackend;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.runtime.TornadoRuntimeProvider;

import java.util.Arrays;
import java.util.HashMap;

public class Cyclone {

    private static final HashMap<String, Integer> BACKEND_MAP = getBackends();

    public static boolean PROFILER = false;
    public static CycloneBackend BACKEND = new CycloneBackend(0);

    public static void setProfiler(boolean profiler) {
        PROFILER = profiler;
    }

    public static void setBackend(@NotNull String backend) {
        if (BACKEND_MAP.keySet().stream().noneMatch(s -> s.equals(backend)))
            throw new IllegalArgumentException("Invalid backend \"%s\", available backends: %s".formatted(backend, Arrays.toString(BACKEND_MAP.keySet().toArray())));
        Cyclone.BACKEND = new CycloneBackend(BACKEND_MAP.get(backend));
    }

    protected static HashMap<String, Integer> getBackends() {
        HashMap<String, Integer> backends = new HashMap<>();
        for (int i = 0; i < TornadoRuntimeProvider.getTornadoRuntime().getNumBackends(); i++)
            backends.put(TornadoRuntimeProvider.getTornadoRuntime().getBackend(i).getName(), i);
        return backends;
    }

}
