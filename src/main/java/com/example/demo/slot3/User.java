package com.example.demo.slot3;

public class User {
    private String name;
    private int age;

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    // Hàm main để kiểm thử
    public static void main(String[] args) {
        User user = new User();
        user.setName("Alice");
        user.setAge(25);
        user.display();
        System.out.println("GetName: " + user.getName());
        System.out.println("GetAge: " + user.getAge());
    }
}
