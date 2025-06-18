package com.example.demo.slot3;

import java.io.*;

public class ResourceLeakExample {
    public static void main(String[] args) {
        // try-with-resources đảm bảo reader sẽ được đóng tự động
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
