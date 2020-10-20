package com.garnagaaa.lesson08.task01.app1.factorial.processors;

import com.garnagaaa.lesson08.task01.app1.factorial.Node;
import java.math.BigInteger;
import java.util.List;

/**
 * Класс постпроцессора, выполняет финальную стадию вычисления факториала
 * @author Aleksei Garnaga
 */
public class FactPostProcessor implements Runnable {

    private List<Node> base;

    public FactPostProcessor(List<Node> base) {
        this.base = base;
    }

    @Override
    public void run() {
        // Проходит по уже посчитаным интервалам и вычисляет финальное значение
        BigInteger temp = new BigInteger("0");
        for (int i = 0; i < base.size(); i++) {
            Node node = base.get(i);
            boolean run = true;
            while (run) {
                if (node.isTempResult() && !node.isFinal()) {
                    if (i == 0) {
                        temp = temp.add(node.getFactResult());
                    } else {
                        temp = temp.multiply(node.getFactResult());
                        node.setFactResult(temp);
                    }
                    node.setFinal(true);
                    run = false;
                } else {
                    Thread.yield();
                }
            }
        }
    }
}
