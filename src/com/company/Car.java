package com.company;

import static com.company.Main.*;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            cdl1.countDown();
            cdl1.await();
            System.out.println(this.name + " готов");
            cdl2.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cdl2.await();
            Thread.sleep(50);
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
                }
            if (!win) {
                win = true;
                System.out.println(this.name + " победил.");
            }
            } catch (InterruptedException e){
            e.printStackTrace();
        }
        cdl3.countDown();
    }
}
