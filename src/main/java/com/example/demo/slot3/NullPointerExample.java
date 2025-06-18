package com.example.demo.slot3;

public class NullPointerExample {
    public static void main(String[] args) {
        String text = getInput(); // ví dụ mô phỏng người dùng nhập

        if (!text.isEmpty()) {
            System.out.println("Text is not empty");
        } else {
            System.out.println("Text is null or empty");
        }
    }

    static String getInput() {
        return "Hello";        // thử với chuỗi hợp lệ
    }
}
