package util;

public class UserPrompts {

    public void askForCarId() {
        System.out.println("Enter car ID:");
    }

    public void askForParameter() {
        System.out.println("Select the parameter you wish to edit:\n" +
                "1 - brand,\n" +
                "2 - model,\n" +
                "3 - body type,\n" +
                "4 - color,\n" +
                "5 - mileage,\n" +
                "6 - production date,\n" +
                "0 - Save and exit.");
    }
}
