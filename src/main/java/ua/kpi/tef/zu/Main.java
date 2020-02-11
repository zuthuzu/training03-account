package ua.kpi.tef.zu;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Main
{
    public static void main(String[] args) {
        // Initialization
        Controller controller = new Controller(new Model(), new View());
        // Run
        controller.startAccountInput();
    }
}
