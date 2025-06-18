package com.example.demo.slot3;

import java.io.*;

public class PathTraversalExample {
    public static void main(String[] args) throws IOException {
        // Chỉ cho phép truy cập file trong thư mục hợp lệ
        String baseDir = "./safe_directory/";
        String userInput = "secret.txt"; // không cho phép ../

        File file = new File(baseDir, userInput).getCanonicalFile();
        File safeBase = new File(baseDir).getCanonicalFile();

        // Kiểm tra path thực sự có nằm trong thư mục cho phép không
        if (!file.getPath().startsWith(safeBase.getPath())) {
            System.out.println("Access denied: Invalid file path");
            return;
        }

        if (file.exists()) {
            try (BufferedReader ignored = new BufferedReader(new FileReader(file))) {
                System.out.println("Reading file: " + file.getPath());
                // Có thể đọc nội dung file tại đây
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
