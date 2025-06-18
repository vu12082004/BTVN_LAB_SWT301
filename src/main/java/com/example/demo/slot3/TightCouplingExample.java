package com.example.demo.slot3;

// Interface để tách phụ thuộc
interface OutputDevice {
    void print(String message);
}

// Class Printer implements OutputDevice
class Printer implements OutputDevice {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}

// Class Report nhận OutputDevice thông qua constructor
class Report {
    private final OutputDevice outputDevice;

    public Report(OutputDevice outputDevice) {
        this.outputDevice = outputDevice;
    }

    public void generate() {
        outputDevice.print("Generating report...");
    }

    public static void main(String[] args) {
        OutputDevice printer = new Printer();
        Report report = new Report(printer);
        report.generate();
    }
}
