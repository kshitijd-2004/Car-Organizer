import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Frontend {
    Scanner scnr;
    BackendInterface backend;

    /**
     * Constructor for FrontendInterface class
     *
     */
    public Frontend(BackendInterface backend, Scanner scnr) {
        this.scnr = scnr;
        this.backend = backend;
        this.run();
    }

    /**
     * Method that shows the menu to choose an option
     */
    public void menu() {
        System.out.println("Menu");
        System.out.println("[1] Load File");
        System.out.println("[2] List Cars with Lowest Mileage");
        System.out.println("[3] List Cars with Certain Amount of Mileage or More");
        System.out.println("[4] Exit Application");
    }

    /**
     * Method that runs the commands and re-prompts the user after every command
     */
    public void run() {
        boolean run = true;
        while (run) {
            menu();
            System.out.println("Please choose your option: ");
            int option;
            if (scnr.hasNext()){
                option = scnr.nextInt();
            } else option = 4;

            switch (option) {
                case 1:
                    System.out.println("Please enter name of file: ");
                    String file = scnr.next();
                    loadData();
                    break;
                case 2:
                    System.out.println("Please enter a max mileage threshold");
                    Double maxThreshold = scnr.nextDouble();
                    listLowMileage(maxThreshold);
                    break;
                case 3:
                    System.out.println("Please enter a threshold of the minimum mileage you would like to see: ");
                    double minThreshold = scnr.nextDouble();
                    listRange(minThreshold);
                    break;
                case 4:
                    run = false;
                    System.out.println("Application has been exited.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again");
            }
        }
    }

    /**
     * Method that loads the file from the user
     *
     */
    public void loadData() {
        try {
            backend.readDataFromFile();
            System.out.println("File has been loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Lists cars with the lowest mileage
     *
     */
    public void listLowMileage(double threshold) {
        List<Car> result = backend.getLowestMileage(threshold);
        System.out.println("These are the cars with the lowest mileage");
        for (Car car : result) {
            System.out.println(car.toString());
        }
    }

    /**
     * Lists cars that are within the mileage range with a given threshold
     *
     * @param threshold threshold for does
     */
    public void listRange(double threshold) {
        try {
            List<Car> result = backend.getHighestMileageCars(threshold);
            if (result.isEmpty()) {
                System.out.println("There are no cars above or equal to the mileage provided");
            } else {
                for (Car car : result) {
                    System.out.println(car.toString());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Threshold");
        }
    }

}