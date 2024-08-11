package com.github.polyrocketmatt.cyclone.impl.task.aggregate;

import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.annotations.Reduce;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class SumTask {

    public static void sumFloat(FloatArray input, @Reduce FloatArray output) {
        output.set(0, 0);
        for (@Parallel int i = 0; i < input.getSize(); i++) {
            output.set(0, output.get(0) + input.get(i));
        }
    }

}
