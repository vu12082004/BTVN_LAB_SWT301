package com.example.demo.slot3;

class Animal {
    void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog barks");
    }
}

public class MissingOverrideAnnotationExample {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.speak(); // Output: Dog barks
    }
}
