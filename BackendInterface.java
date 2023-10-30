import java.io.IOException;
import java.util.ArrayList;


interface BackendInterface {

    // Constructor Parameters: path to the CSV file containing car data.
    // Initialize backend to the path of the file.

    // Method that reads data from file and populates the red black tree.
    // No parameters
    void readDataFromFile() throws IOException;

    // Method to get a list of cars with minimum mileage.
    // Parameters: max
    // Mileage - the minimum mileage threshold.
    // Returns a list of cars with mileage equal or lesser to the maximum mileage threshold.
    // If no cars meet the criteria then return an empty list.
    ArrayList<Car> getLowestMileage(double maxMileage);

    // Method to get a list of cars that meet the mileage threshold.
    // Parameters: minMileage - the mileage threshold
    // Returns a list of cars with mileage equal or greater than the threshold.
    // If no cars meet the criteria then return an empty list.
    ArrayList<Car> getHighestMileageCars(double minMileage);

}
