package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        init();
        boolean running = true;
        while (running) {
            displayMenu();
            String command = scanner.nextLine();
            //Choice options for the user
            switch (command) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByTypeRequest();
                    break;
                case "7":
                    processGetAllVehicleRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "0":
                    running = false;
                    break;
                default: System.out.println("Invalid option. Please choose valid option");

            }
        }
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        dealership = dfm.getDealership();
    }

    private void displayMenu() {
        System.out.println("-----\nDealership Menu-----");
        System.out.println("===========================");
        System.out.println("1. Search by Price");
        System.out.println("2. Search by Make and Model");
        System.out.println("3. Search by Year");
        System.out.println("4. Search by Color");
        System.out.println("5. Search by Mileage");
        System.out.println("6. Search by Type");
        System.out.println("7. Show All Vehicles");
        System.out.println("8. Add a Vehicle");
        System.out.println("9. Remove a Vehicle");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter max price: ");
        double max = Double.parseDouble(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYearRequest() {
        System.out.print("Enter min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter max year: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileageRequest() {
        System.out.print("Enter min mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter max mileage: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void processGetByTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    private void processGetAllVehicleRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter odometer: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
        new DealershipFileManager().saveDealership(dealership);
        System.out.println("Vehicle added.");
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());
        Vehicle toRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }
        if (toRemove != null) {
            dealership.removeVehicle(toRemove);
            new DealershipFileManager().saveDealership(dealership);
            System.out.println("Vehicle removed.");
        } else {
            System.out.println("Vehicle not found. Please make valid choice");
        }
    }
}
