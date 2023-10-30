import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;


public class Backend implements BackendInterface {



    private File file;
    public ArrayList<Car> car;
    IterableMultiKeySortedCollectionInterface<CarInterface> tree;

    public static void main(String[] args){
        IterableMultiKeySortedCollectionInterface<CarInterface> tree;
        tree = new IterableMultiKeyRBT<>();
        Scanner scnr = new Scanner(System.in);
        Backend backend = new Backend(new File("test-cars.csv"), tree);
        Frontend frontend = new Frontend(backend, scnr);
    }

    public Backend(File filePath, IterableMultiKeySortedCollectionInterface<CarInterface> tree) {
        this.file = filePath;
        this.car = new ArrayList<>();
        this.tree = tree;
    }


    // Method that reads data from file and populates the red black tree.
    // No parameters
    @Override

    public void readDataFromFile() throws IOException {
        try {

            Scanner scnr = new Scanner(file);

            String[] temp;
            String brand = null;
            String model = null;
            int year = 0;
            double price = 0;
            double mileage = 0;

            while (scnr.hasNextLine()) {
                temp = scnr.nextLine().split(", ");
                System.out.println(Arrays.toString(temp));
                System.out.println(temp.length);
                brand = temp[0];
                model = temp[1];
                year = Integer.valueOf(temp[2]);
                price = Double.valueOf(temp[3]);
                mileage = Double.valueOf(temp[4]);
                tree.insertSingleKey(new Car(brand, model, year, price, mileage));


            }


        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Car> getLowestMileage(double maxMileage) {
        ArrayList<Car> lowestMileageCars = new ArrayList<>();
        Iterator<CarInterface> iterator = tree.iterator();
       while(iterator.hasNext()){
          CarInterface car =  iterator.next();
          if(car.getMileage() < maxMileage){
              lowestMileageCars.add((Car) car);
          }

       }
        return lowestMileageCars;
    }

    @Override
    public ArrayList<Car> getHighestMileageCars(double minMileage) {
        ArrayList<Car> highestMileageCars = new ArrayList<>();

        Iterator<CarInterface> iterator = tree.iterator();
        while(iterator.hasNext()){
            CarInterface car =  iterator.next();
            if(car.getMileage() >= minMileage){
                highestMileageCars.add((Car) car);
            }

        }
        return highestMileageCars;
    }

}





