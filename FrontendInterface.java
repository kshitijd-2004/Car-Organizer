/**
 * Shared Frontend Interface
 *
 */
public interface FrontendInterface {

    /**
     * Method that shows the menu to choose an option
     */
    public void menu();

    /**
     * Method that runs the commands and re-prompts the user after every command
     */
    public void run();

    /**
     * Method that loads the file from the user
     *
     * @param fileName - file
     */
    public void loadData(String fileName);

    /**
     * Lists cars with the lowest mileage
     *
     */
    public void listLowMileage();

    /**
     * Lists cars that are within the mileage range with a given threshold
     *
     * @param threshold
     */
    public void listRange(double threshold);

}
