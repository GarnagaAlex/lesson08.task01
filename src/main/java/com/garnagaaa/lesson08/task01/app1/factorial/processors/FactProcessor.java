package com.garnagaaa.lesson08.task01.app1.factorial.processors;

import com.garnagaaa.lesson08.task01.app1.factorial.Node;
import java.math.BigInteger;

/**
 * Класс процессор для расчета интервала, в задачи вычисления факториала как первая стадия.
 * @author Aleksei Garnaga
 */
public class FactProcessor implements Runnable {

    private Node node;

    public FactProcessor(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        if (!node.isTempResult() && !node.isFinal()) {
            BigInteger temp = new BigInteger("1");
            for (int i = node.getStart(); i <= node.getBaseValue(); i++) {
                temp = temp.multiply(BigInteger.valueOf(i));
            }
            node.setFactResult(temp);
            node.setTempResult(true);
        }
    }
}
