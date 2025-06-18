package com.example.demo.slot3;

interface Drawable {
    void draw();
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle...");
    }
}
public class TestDrawable {
    public static void main(String[] args) {
        Drawable d = new Circle();
        d.draw(); // Gọi phương thức để tránh cảnh báo
    }
}
