package com.garnagaaa.lesson08.task01.app1.factorial;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aleksei Garnaga
 * Класс нода одного из чисел факториала
 */
public class Node {

    private final AtomicInteger baseValue;
    private final AtomicInteger start;
    private final Object sync = new Object();
    private volatile AtomicBoolean isFinal = new AtomicBoolean(false);
    private volatile AtomicBoolean isTempResult = new AtomicBoolean(false);
    private volatile BigInteger FactResult = new BigInteger("-1");

    public Node(int value, int startValue) {
        baseValue = new AtomicInteger(value);
        start = new AtomicInteger(startValue);
    }

    public int getBaseValue() {
        return baseValue.intValue();
    }

    public int getStart() {
        return start.intValue();
    }

    public BigInteger getFactResult() {
        synchronized (sync) {
            return FactResult;
        }
    }

    public void setFactResult(BigInteger value) {
        synchronized (sync) {
            FactResult = new BigInteger(value.toString());
        }
    }

    public boolean isFinal() {
        return isFinal.get();
    }

    public void setFinal(boolean aFinal) {
        isFinal.set(aFinal);
    }

    public boolean isTempResult() {
        return isTempResult.get();
    }

    public void setTempResult(boolean tempResult) {
        isTempResult.set(tempResult);
    }

}
