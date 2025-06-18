package com.example.demo.slot3;

interface Constants {
    int MAX_USERS = 100; // mặc định là public static final
}

public class InterfaceFieldModificationExample {
    public static void main(String[] args) {
        // Sử dụng giá trị hằng số thay vì cố gắng thay đổi nó
        System.out.println("Maximum allowed users: " + Constants.MAX_USERS);
    }
}
