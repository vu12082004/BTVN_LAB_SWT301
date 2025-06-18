package com.example.demo.slot3;

public class UnreachableCodeExample {
    public static int getNumber() {
        System.out.println("Preparing to return value...");
        return 42; // return phải là dòng cuối cùng trong phương thức
    }

    public static void main(String[] args) {
        System.out.println(getNumber());
    }
}
