package com.github.polyrocketmatt.cyclone.impl.task.util;

import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class FillTask {

   public static void fillFloat(FloatArray input, FloatArray output,
                                float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            output.set(i, value);
    }

}
