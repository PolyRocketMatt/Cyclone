package com.github.polyrocketmatt.cyclone.impl.kernel;

import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.math.TornadoMath;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class ArithmeticKernels {

    private static final float LOG_10 = TornadoMath.log(10);

    public static void addFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, buffer.get(i) + value);
    }

    public static void subtractFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, buffer.get(i) - value);
    }

    public static void multiplyFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, buffer.get(i) * value);
    }

    public static void divideFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, buffer.get(i) / value);
    }

    public static void moduloFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, buffer.get(i) % value);
    }

    public static void powFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.pow(buffer.get(i), value));
    }

    public static void rootFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.pow(buffer.get(i), 1 / value));
    }

    public static void expFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.exp(buffer.get(i)));
    }

    public static void lnFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.log(buffer.get(i)));
    }

    public static void log2Float(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.log2(buffer.get(i)));
    }

    public static void log10Float(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.log(buffer.get(i)) / LOG_10);
    }

    public static void logXFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.log(buffer.get(i)) / TornadoMath.log(value));
    }

    public static void negateFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, -buffer.get(i));
    }

    public static void floorFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.floor(buffer.get(i)));
    }

    public static void ceilFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.ceil(buffer.get(i)));
    }

    public static void absFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.abs(buffer.get(i)));
    }

    public static void sinFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.sin(buffer.get(i)));
    }

    public static void cosFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.cos(buffer.get(i)));
    }

    public static void tanFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.tan(buffer.get(i)));
    }

    public static void cscFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, 1 / TornadoMath.sin(buffer.get(i)));
    }

    public static void secFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, 1 / TornadoMath.cos(buffer.get(i)));
    }

    public static void cotFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, 1 / TornadoMath.tan(buffer.get(i)));
    }

    public static void asinFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.asin(buffer.get(i)));
    }

    public static void acosFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.acos(buffer.get(i)));
    }

    public static void atanFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.atan(buffer.get(i)));
    }

    public static void atan2Float(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, TornadoMath.atan2(buffer.get(i), value));
    }

    public static void sinhFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = buffer.get(i);
            buffer.set(i, ((TornadoMath.exp(x) - TornadoMath.exp(-x)) / 2));
        }
    }

    public static void coshFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = buffer.get(i);
            buffer.set(i, ((TornadoMath.exp(x) + TornadoMath.exp(-x)) / 2));
        }
    }

    public static void tanhFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = buffer.get(i);
            buffer.set(i, ((TornadoMath.exp(x) - TornadoMath.exp(-x)) / (TornadoMath.exp(x) + TornadoMath.exp(-x))));
        }
    }

    public static void asinhFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = buffer.get(i);
            buffer.set(i, TornadoMath.log(x + TornadoMath.sqrt(x * x + 1)));
        }
    }

    public static void acoshFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = buffer.get(i);
            buffer.set(i, TornadoMath.log(x + TornadoMath.sqrt(x * x - 1)));
        }
    }

    public static void atanhFloat(FloatArray buffer, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = buffer.get(i);
            buffer.set(i, (float) (0.5 * TornadoMath.log((1 + x) / (1 - x))));
        }
    }

}
