package com.github.polyrocketmatt.cyclone.impl.tensor.wrapper;

import com.github.polyrocketmatt.cyclone.api.TriFunction;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor1D;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Float1D extends LinearizedFloatTensor implements Tensor1D<Float>{

    public Float1D(int size) {
        super(size, 0.0f);
    }

    public Float1D(int size, float value) {
        super(size, value);
    }

    @Override
    public @NotNull Float1D add(@NotNull Float value) {
        return (Float1D) super.add(value);
    }

    @Override
    public @NotNull Float1D subtract(@NotNull Float value) {
        return (Float1D) super.subtract(value);
    }

    @Override
    public @NotNull Float1D multiply(@NotNull Float value) {
        return (Float1D) super.multiply(value);
    }

    @Override
    public @NotNull Float1D divide(@NotNull Float value) {
        return (Float1D) super.divide(value);
    }

    @Override
    public @NotNull Float1D modulo(@NotNull Float value) {
        return (Float1D) super.modulo(value);
    }

    @Override
    public @NotNull Float1D power(@NotNull Float value) {
        return (Float1D) super.power(value);
    }

    @Override
    public @NotNull Float1D sqrt() {
        return (Float1D) super.sqrt();
    }

    @Override
    public @NotNull Float1D cbrt() {
        return (Float1D) super.cbrt();
    }

    @Override
    public @NotNull Float1D root(@NotNull Float value) {
        return (Float1D) super.root(value);
    }

    @Override
    public @NotNull Float1D exp() {
        return (Float1D) super.exp();
    }

    @Override
    public @NotNull Float1D log() {
        return (Float1D) super.log();
    }

    @Override
    public @NotNull Float1D log2() {
        return (Float1D) super.log2();
    }

    @Override
    public @NotNull Float1D log10() {
        return (Float1D) super.log10();
    }

    @Override
    public @NotNull Float1D logx(@NotNull Float value) {
        return (Float1D) super.logx(value);
    }

    @Override
    public @NotNull Float1D sin() {
        return (Float1D) super.sin();
    }

    @Override
    public @NotNull Float1D cos() {
        return (Float1D) super.cos();
    }

    @Override
    public @NotNull Float1D tan() {
        return (Float1D) super.tan();
    }

    @Override
    public @NotNull Float1D csc() {
        return (Float1D) super.csc();
    }

    @Override
    public @NotNull Float1D sec() {
        return (Float1D) super.sec();
    }

    @Override
    public @NotNull Float1D cot() {
        return (Float1D) super.cot();
    }

    @Override
    public @NotNull Float1D asin() {
        return (Float1D) super.asin();
    }

    @Override
    public @NotNull Float1D acos() {
        return (Float1D) super.acos();
    }

    @Override
    public @NotNull Float1D atan() {
        return (Float1D) super.atan();
    }

    @Override
    public @NotNull Float1D atan2(@NotNull Float value) {
        return (Float1D) super.atan2(value);
    }

    @Override
    public @NotNull Float1D sinh() {
        return (Float1D) super.sinh();
    }

    @Override
    public @NotNull Float1D cosh() {
        return (Float1D) super.cosh();
    }

    @Override
    public @NotNull Float1D tanh() {
        return (Float1D) super.tanh();
    }

    @Override
    public @NotNull Float1D asinh() {
        return (Float1D) super.asinh();
    }

    @Override
    public @NotNull Float1D acosh() {
        return (Float1D) super.acosh();
    }

    @Override
    public @NotNull Float1D atanh() {
        return (Float1D) super.atanh();
    }

    @Override
    public @NotNull Float1D negate() {
        return (Float1D) super.negate();
    }

    @Override
    public @NotNull Float1D floor() {
        return (Float1D) super.floor();
    }

    @Override
    public @NotNull Float1D ceil() {
        return (Float1D) super.ceil();
    }

    @Override
    public @NotNull Float1D abs() {
        return (Float1D) super.abs();
    }

    @Override
    public @NotNull Float1D fill(@NotNull Float value) {
        return (Float1D) super.fill(value);
    }

    @Override
    public @NotNull Float1D random() {
        return (Float1D) super.random();
    }

    @Override
    public @NotNull Float1D random(int seed) {
        return (Float1D) super.random(seed);
    }

    @Override
    public @NotNull Float1D map(@NotNull Function<Float, Float> mapper) {
        return (Float1D) super.map(mapper);
    }

    @Override
    public @NotNull Float1D mapIndexed(@NotNull BiFunction<Integer, Float, Float> mapper) {
        return (Float1D) super.mapIndexed(mapper);
    }

    @Override
    public @NotNull Float1D zipWith(@NotNull Tensor<Float> other, @NotNull BiFunction<Float, Float, Float> zipper) {
        return (Float1D) super.zipWith(other, zipper);
    }

    @Override
    public @NotNull Float1D zipWithIndexed(@NotNull Tensor<Float> other, @NotNull TriFunction<Integer, Float, Float, Float> zipper) {
        return (Float1D) super.zipWithIndexed(other, zipper);
    }

    @Override
    public @NotNull Float1D add(@NotNull Tensor<Float> other) {
        return (Float1D) super.add(other);
    }

    @Override
    public @NotNull Float1D subtract(@NotNull Tensor<Float> other) {
        return (Float1D) super.subtract(other);
    }

    @Override
    public @NotNull Float1D multiply(@NotNull Tensor<Float> other) {
        return (Float1D) super.multiply(other);
    }

    @Override
    public @NotNull Float1D divide(@NotNull Tensor<Float> other) {
        return (Float1D) super.divide(other);
    }
}
