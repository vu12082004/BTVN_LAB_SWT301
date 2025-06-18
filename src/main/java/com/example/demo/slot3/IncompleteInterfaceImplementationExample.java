package com.example.demo.slot3;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing square");
    }

    @Override
    public void resize() {
        System.out.println("Resizing square");
    }

    public static void main(String[] args) {
        Shape square = new Square();
        square.draw();
        square.resize();
    }
}
