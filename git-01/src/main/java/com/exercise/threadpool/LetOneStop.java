package com.exercise.threadpool;

public class LetOneStop implements Animal.CalltoBack {
    public Animal animal;

    public LetOneStop(Animal animal) {
        this.animal = animal;
    }

    public void win() {
        animal.stop();
    }
}
