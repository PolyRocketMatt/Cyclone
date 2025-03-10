package com.github.polyrocketmatt.cyclone.impl.kernel;

import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.math.TornadoMath;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class ArithmeticKernels {

    private static final float LOG_10 = TornadoMath.log(10);

    public static void addFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) + value);
    }

    public static void subtractFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) - value);
    }

    public static void multiplyFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) * value);
    }

    public static void divideFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) / value);
    }

    public static void moduloFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) % value);
    }

    public static void powFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.pow(array.get(i), value));
    }

    public static void rootFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.pow(array.get(i), 1 / value));
    }

    public static void expFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.exp(array.get(i)));
    }

    public static void lnFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.log(array.get(i)));
    }

    public static void log2Float(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.log2(array.get(i)));
    }

    public static void log10Float(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.log(array.get(i)) / LOG_10);
    }

    public static void logXFloat(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.log(array.get(i)) / TornadoMath.log(value));
    }

    public static void negateFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, -array.get(i));
    }

    public static void floorFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.floor(array.get(i)));
    }

    public static void ceilFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.ceil(array.get(i)));
    }

    public static void absFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.abs(array.get(i)));
    }

    public static void sinFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.sin(array.get(i)));
    }

    public static void cosFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.cos(array.get(i)));
    }

    public static void tanFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.tan(array.get(i)));
    }

    public static void cscFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, 1 / TornadoMath.sin(array.get(i)));
    }

    public static void secFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, 1 / TornadoMath.cos(array.get(i)));
    }

    public static void cotFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, 1 / TornadoMath.tan(array.get(i)));
    }

    public static void asinFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.asin(array.get(i)));
    }

    public static void acosFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.acos(array.get(i)));
    }

    public static void atanFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.atan(array.get(i)));
    }

    public static void atan2Float(FloatArray array, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, TornadoMath.atan2(array.get(i), value));
    }

    public static void sinhFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = array.get(i);
            array.set(i, ((TornadoMath.exp(x) - TornadoMath.exp(-x)) / 2));
        }
    }

    public static void coshFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = array.get(i);
            array.set(i, ((TornadoMath.exp(x) + TornadoMath.exp(-x)) / 2));
        }
    }

    public static void tanhFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = array.get(i);
            array.set(i, ((TornadoMath.exp(x) - TornadoMath.exp(-x)) / (TornadoMath.exp(x) + TornadoMath.exp(-x))));
        }
    }

    public static void asinhFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = array.get(i);
            array.set(i, TornadoMath.log(x + TornadoMath.sqrt(x * x + 1)));
        }
    }

    public static void acoshFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = array.get(i);
            array.set(i, TornadoMath.log(x + TornadoMath.sqrt(x * x - 1)));
        }
    }

    public static void atanhFloat(FloatArray array, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            float x = array.get(i);
            array.set(i, (float) (0.5 * TornadoMath.log((1 + x) / (1 - x))));
        }
    }

}
