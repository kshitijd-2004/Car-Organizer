interface CarInterface extends Comparable<CarInterface> {

    //Constructor Parameters: brand, model, year, price and mileage.
    // Initializes a Car object with the given properties.

    // Getter methods for the car properties.
    String getBrand();
    String getModel();
    int getYear();
    double getPrice();
    double getMileage();

}