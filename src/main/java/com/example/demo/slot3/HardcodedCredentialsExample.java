package com.example.demo.slot3;

public class HardcodedCredentialsExample {
    public static void main(String[] args) {
        // Lấy thông tin đăng nhập từ biến môi trường (đảm bảo bảo mật)
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");

        if (authenticate(username, password)) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Sample authentication logic
        return "admin".equals(user) && "123456".equals(pass);
    }
}
