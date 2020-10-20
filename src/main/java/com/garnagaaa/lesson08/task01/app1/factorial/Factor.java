package com.garnagaaa.lesson08.task01.app1.factorial;

import com.garnagaaa.lesson08.task01.app1.factorial.processors.FactPostProcessor;
import com.garnagaaa.lesson08.task01.app1.factorial.processors.FactProcessor;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Aleksei Garnaga
 * Класс вычисляющий факториалы чисел
 */
public class Factor {

    private volatile List<Node> base = new CopyOnWriteArrayList<>();
    private final int MAX_Th;
    private ExecutorService pool;

    /**
     * Конструктор класса, получает от системы количество доступных ядер процессора и задает пул потоков на основе этого.
     */
    public Factor() {
        MAX_Th = Runtime.getRuntime().availableProcessors() * 2;
        pool = Executors.newFixedThreadPool(MAX_Th);
    }

    /**
     * Метод загрузки данных в класс
     * @param data Массив чисел
     */
    public void inputData(int[] data) {
        Integer[] temp = new Integer[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        List<Integer> uniq = new ArrayList<>(new HashSet<>(Arrays.asList(temp)));
        Collections.sort(uniq);

        base.add(new Node(uniq.get(0), 1));
        for (int i = 1; i < uniq.size(); i++) {
            base.add(new Node(uniq.get(i), uniq.get(i - 1) + 1));
        }
    }

    /**
     *  Метод запуска параллельных вычислений факториалов
     */
    public void Start() {
        pool.execute(new FactPostProcessor(base));
        for (Node node : base) {
            pool.execute(new FactProcessor(node));
        }
    }

    /**
     *  Блокирующий метод, осуществляет блокировку вызывающего до завершения вычислений
     */
    private void join() {
        boolean run = true;
        while (run) {
            for (int i = 0; i < base.size(); i++) {
                if (!base.get(i).isFinal()) {
                    break;
                }
                if (i == base.size() - 1) {
                    run = false;
                }
            }
            Thread.yield();
        }
        pool.shutdown();
    }

    /**
     * Метод вывода результата на консоль
     */
    public void Show() {
        join();
        for (Node node : base) {
            System.out.println(node.getBaseValue() + "! = " + node.getFactResult());
        }
    }


}
