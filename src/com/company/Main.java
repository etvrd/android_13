package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static final int CARS_COUNT = 4;
    public static CountDownLatch cdl1 = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch cdl2 = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch cdl3 = new CountDownLatch(CARS_COUNT);
    public static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
    public static boolean win = false;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            cdl2.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cdl3.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}
