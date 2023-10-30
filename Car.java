public class Car implements CarInterface{
    String brand;
    String model;
    int year;
    double price;
    double mileage;

    public Car(String brand, String model, int year, double price, double mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;

    }
    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getMileage() {
        return mileage;
    }



    @Override
    public int compareTo(CarInterface o) {
        if (o instanceof Car) {
            Car otherCar = (Car) o;
            // Compare by year
            int yearComparison = Integer.compare(this.year, otherCar.getYear());
            if (yearComparison != 0) {
                return yearComparison;
            }
            // If the years are the same, compare by brand and model
            int brandComparison = this.brand.compareTo(otherCar.getBrand());
            if (brandComparison != 0) {
                return brandComparison;
            }
            return this.model.compareTo(otherCar.getModel());
        }
        return 0; // Handle the case when 'o' is not a Car or null.
    }
}
