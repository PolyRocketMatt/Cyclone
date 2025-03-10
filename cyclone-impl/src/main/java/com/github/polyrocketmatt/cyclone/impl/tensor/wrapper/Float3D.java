package com.github.polyrocketmatt.cyclone.impl.tensor.wrapper;

import com.github.polyrocketmatt.cyclone.api.TriFunction;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor3D;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Float3D extends LinearizedFloatTensor implements Tensor3D<Float> {

    private final int[] shape;

    public Float3D(int[] shape) {
        super(shape[0] * shape[1] * shape[2], 0.0f);
        this.shape = shape;
    }

    public Float3D(int[] shape, float value) {
        super(shape[0] * shape[1] * shape[2], value);
        this.shape = shape;
    }

    @Override
    public int[] getShape() {
        return shape;
    }

    @Override
    public int getWidth() {
        return shape[0];
    }

    @Override
    public int getHeight() {
        return shape[1];
    }

    @Override
    public int getDepth() {
        return shape[2];
    }

    @Override
    public @NotNull Float get(int x, int y, int z) throws IndexOutOfBoundsException {
        return get(x * shape[0] + y * shape[1] + z);
    }

    @Override
    public void set(int x, int y, int z, @NotNull Float value) throws IndexOutOfBoundsException {
        set(x * shape[0] + y * shape[1] + z, value);
    }

    @Override
    public @NotNull Float3D add(@NotNull Float value) {
        return (Float3D) super.add(value);
    }

    @Override
    public @NotNull Float3D subtract(@NotNull Float value) {
        return (Float3D) super.subtract(value);
    }

    @Override
    public @NotNull Float3D multiply(@NotNull Float value) {
        return (Float3D) super.multiply(value);
    }

    @Override
    public @NotNull Float3D divide(@NotNull Float value) {
        return (Float3D) super.divide(value);
    }

    @Override
    public @NotNull Float3D modulo(@NotNull Float value) {
        return (Float3D) super.modulo(value);
    }

    @Override
    public @NotNull Float3D power(@NotNull Float value) {
        return (Float3D) super.power(value);
    }

    @Override
    public @NotNull Float3D sqrt() {
        return (Float3D) super.sqrt();
    }

    @Override
    public @NotNull Float3D cbrt() {
        return (Float3D) super.cbrt();
    }

    @Override
    public @NotNull Float3D root(@NotNull Float value) {
        return (Float3D) super.root(value);
    }

    @Override
    public @NotNull Float3D exp() {
        return (Float3D) super.exp();
    }

    @Override
    public @NotNull Float3D log() {
        return (Float3D) super.log();
    }

    @Override
    public @NotNull Float3D log2() {
        return (Float3D) super.log2();
    }

    @Override
    public @NotNull Float3D log10() {
        return (Float3D) super.log10();
    }

    @Override
    public @NotNull Float3D logx(@NotNull Float value) {
        return (Float3D) super.logx(value);
    }

    @Override
    public @NotNull Float3D sin() {
        return (Float3D) super.sin();
    }

    @Override
    public @NotNull Float3D cos() {
        return (Float3D) super.cos();
    }

    @Override
    public @NotNull Float3D tan() {
        return (Float3D) super.tan();
    }

    @Override
    public @NotNull Float3D csc() {
        return (Float3D) super.csc();
    }

    @Override
    public @NotNull Float3D sec() {
        return (Float3D) super.sec();
    }

    @Override
    public @NotNull Float3D cot() {
        return (Float3D) super.cot();
    }

    @Override
    public @NotNull Float3D asin() {
        return (Float3D) super.asin();
    }

    @Override
    public @NotNull Float3D acos() {
        return (Float3D) super.acos();
    }

    @Override
    public @NotNull Float3D atan() {
        return (Float3D) super.atan();
    }

    @Override
    public @NotNull Float3D atan2(@NotNull Float value) {
        return (Float3D) super.atan2(value);
    }

    @Override
    public @NotNull Float3D sinh() {
        return (Float3D) super.sinh();
    }

    @Override
    public @NotNull Float3D cosh() {
        return (Float3D) super.cosh();
    }

    @Override
    public @NotNull Float3D tanh() {
        return (Float3D) super.tanh();
    }

    @Override
    public @NotNull Float3D asinh() {
        return (Float3D) super.asinh();
    }

    @Override
    public @NotNull Float3D acosh() {
        return (Float3D) super.acosh();
    }

    @Override
    public @NotNull Float3D atanh() {
        return (Float3D) super.atanh();
    }

    @Override
    public @NotNull Float3D negate() {
        return (Float3D) super.negate();
    }

    @Override
    public @NotNull Float3D floor() {
        return (Float3D) super.floor();
    }

    @Override
    public @NotNull Float3D ceil() {
        return (Float3D) super.ceil();
    }

    @Override
    public @NotNull Float3D abs() {
        return (Float3D) super.abs();
    }

    @Override
    public @NotNull Float3D fill(@NotNull Float value) {
        return (Float3D) super.fill(value);
    }

    @Override
    public @NotNull Float3D random() {
        return (Float3D) super.random();
    }

    @Override
    public @NotNull Float3D random(int seed) {
        return (Float3D) super.random(seed);
    }

    @Override
    public @NotNull Float3D map(@NotNull Function<Float, Float> mapper) {
        return (Float3D) super.map(mapper);
    }

    @Override
    public @NotNull Float3D mapIndexed(@NotNull BiFunction<Integer, Float, Float> mapper) {
        return (Float3D) super.mapIndexed(mapper);
    }

    @Override
    public @NotNull Float3D zipWith(@NotNull Tensor<Float> other, @NotNull BiFunction<Float, Float, Float> zipper) {
        return (Float3D) super.zipWith(other, zipper);
    }

    @Override
    public @NotNull Float3D zipWithIndexed(@NotNull Tensor<Float> other, @NotNull TriFunction<Integer, Float, Float, Float> zipper) {
        return (Float3D) super.zipWithIndexed(other, zipper);
    }

    @Override
    public @NotNull Float3D add(@NotNull Tensor<Float> other) {
        return (Float3D) super.add(other);
    }

    @Override
    public @NotNull Float3D subtract(@NotNull Tensor<Float> other) {
        return (Float3D) super.subtract(other);
    }

    @Override
    public @NotNull Float3D multiply(@NotNull Tensor<Float> other) {
        return (Float3D) super.multiply(other);
    }

    @Override
    public @NotNull Float3D divide(@NotNull Tensor<Float> other) {
        return (Float3D) super.divide(other);
    }
    
}
