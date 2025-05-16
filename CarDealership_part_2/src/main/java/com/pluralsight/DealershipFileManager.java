package com.pluralsight;

import java.io.*;

public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split("\\|");
                dealership = new Dealership(parts[0], parts[1], parts[2]);
            }
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);
                dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
            }
        } catch (IOException e) {
            System.out.println("Error loading dealership: " + e.getMessage());
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("inventory.csv"))) {
            for (Vehicle v : dealership.getAllVehicles()) {
                pw.println(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                        v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving dealership: " + e.getMessage());
        }
    }
}
