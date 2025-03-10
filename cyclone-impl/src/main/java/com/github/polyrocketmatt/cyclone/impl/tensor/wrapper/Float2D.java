package com.github.polyrocketmatt.cyclone.impl.tensor.wrapper;

import com.github.polyrocketmatt.cyclone.api.TriFunction;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor2D;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Float2D extends LinearizedFloatTensor implements Tensor2D<Float> {

    private final int[] shape;

    public Float2D(int[] shape) {
        super(shape[0] * shape[1], 0.0f);
        this.shape = shape;
    }

    public Float2D(int[] shape, float value) {
        super(shape[0] * shape[1], value);
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
    public @NotNull Float get(int x, int y) throws IndexOutOfBoundsException {
        return get(x * shape[0] + y);
    }

    @Override
    public void set(int x, int y, @NotNull Float value) throws IndexOutOfBoundsException {
        set(x * shape[0] + y, value);
    }

    @Override
    public @NotNull Float2D add(@NotNull Float value) {
        return (Float2D) super.add(value);
    }

    @Override
    public @NotNull Float2D subtract(@NotNull Float value) {
        return (Float2D) super.subtract(value);
    }

    @Override
    public @NotNull Float2D multiply(@NotNull Float value) {
        return (Float2D) super.multiply(value);
    }

    @Override
    public @NotNull Float2D divide(@NotNull Float value) {
        return (Float2D) super.divide(value);
    }

    @Override
    public @NotNull Float2D modulo(@NotNull Float value) {
        return (Float2D) super.modulo(value);
    }

    @Override
    public @NotNull Float2D power(@NotNull Float value) {
        return (Float2D) super.power(value);
    }

    @Override
    public @NotNull Float2D sqrt() {
        return (Float2D) super.sqrt();
    }

    @Override
    public @NotNull Float2D cbrt() {
        return (Float2D) super.cbrt();
    }

    @Override
    public @NotNull Float2D root(@NotNull Float value) {
        return (Float2D) super.root(value);
    }

    @Override
    public @NotNull Float2D exp() {
        return (Float2D) super.exp();
    }

    @Override
    public @NotNull Float2D log() {
        return (Float2D) super.log();
    }

    @Override
    public @NotNull Float2D log2() {
        return (Float2D) super.log2();
    }

    @Override
    public @NotNull Float2D log10() {
        return (Float2D) super.log10();
    }

    @Override
    public @NotNull Float2D logx(@NotNull Float value) {
        return (Float2D) super.logx(value);
    }

    @Override
    public @NotNull Float2D sin() {
        return (Float2D) super.sin();
    }

    @Override
    public @NotNull Float2D cos() {
        return (Float2D) super.cos();
    }

    @Override
    public @NotNull Float2D tan() {
        return (Float2D) super.tan();
    }

    @Override
    public @NotNull Float2D csc() {
        return (Float2D) super.csc();
    }

    @Override
    public @NotNull Float2D sec() {
        return (Float2D) super.sec();
    }

    @Override
    public @NotNull Float2D cot() {
        return (Float2D) super.cot();
    }

    @Override
    public @NotNull Float2D asin() {
        return (Float2D) super.asin();
    }

    @Override
    public @NotNull Float2D acos() {
        return (Float2D) super.acos();
    }

    @Override
    public @NotNull Float2D atan() {
        return (Float2D) super.atan();
    }

    @Override
    public @NotNull Float2D atan2(@NotNull Float value) {
        return (Float2D) super.atan2(value);
    }

    @Override
    public @NotNull Float2D sinh() {
        return (Float2D) super.sinh();
    }

    @Override
    public @NotNull Float2D cosh() {
        return (Float2D) super.cosh();
    }

    @Override
    public @NotNull Float2D tanh() {
        return (Float2D) super.tanh();
    }

    @Override
    public @NotNull Float2D asinh() {
        return (Float2D) super.asinh();
    }

    @Override
    public @NotNull Float2D acosh() {
        return (Float2D) super.acosh();
    }

    @Override
    public @NotNull Float2D atanh() {
        return (Float2D) super.atanh();
    }

    @Override
    public @NotNull Float2D negate() {
        return (Float2D) super.negate();
    }

    @Override
    public @NotNull Float2D floor() {
        return (Float2D) super.floor();
    }

    @Override
    public @NotNull Float2D ceil() {
        return (Float2D) super.ceil();
    }

    @Override
    public @NotNull Float2D abs() {
        return (Float2D) super.abs();
    }

    @Override
    public @NotNull Float2D fill(@NotNull Float value) {
        return (Float2D) super.fill(value);
    }

    @Override
    public @NotNull Float2D random() {
        return (Float2D) super.random();
    }

    @Override
    public @NotNull Float2D random(int seed) {
        return (Float2D) super.random(seed);
    }

    @Override
    public @NotNull Float2D map(@NotNull Function<Float, Float> mapper) {
        return (Float2D) super.map(mapper);
    }

    @Override
    public @NotNull Float2D mapIndexed(@NotNull BiFunction<Integer, Float, Float> mapper) {
        return (Float2D) super.mapIndexed(mapper);
    }

    @Override
    public @NotNull Float2D zipWith(@NotNull Tensor<Float> other, @NotNull BiFunction<Float, Float, Float> zipper) {
        return (Float2D) super.zipWith(other, zipper);
    }

    @Override
    public @NotNull Float2D zipWithIndexed(@NotNull Tensor<Float> other, @NotNull TriFunction<Integer, Float, Float, Float> zipper) {
        return (Float2D) super.zipWithIndexed(other, zipper);
    }

    @Override
    public @NotNull Float2D add(@NotNull Tensor<Float> other) {
        return (Float2D) super.add(other);
    }

    @Override
    public @NotNull Float2D subtract(@NotNull Tensor<Float> other) {
        return (Float2D) super.subtract(other);
    }

    @Override
    public @NotNull Float2D multiply(@NotNull Tensor<Float> other) {
        return (Float2D) super.multiply(other);
    }

    @Override
    public @NotNull Float2D divide(@NotNull Tensor<Float> other) {
        return (Float2D) super.divide(other);
    }

}
