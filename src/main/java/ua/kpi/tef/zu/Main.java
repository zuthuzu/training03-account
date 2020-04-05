package ua.kpi.tef.zu;

import ua.kpi.tef.zu.controller.ReadController;
import ua.kpi.tef.zu.controller.WriteController;
import ua.kpi.tef.zu.model.Model;
import ua.kpi.tef.zu.view.View;

/**
 * Created by Anton Domin on 2020-02-11
 */

public class Main
{
    public static void main(String[] args) {
        /*ReadController readController = new ReadController(new Model(), new View());
        readController.startRecordOutput();*/

        WriteController writeController = new WriteController(new Model(), new View());
        writeController.startRecordInput();
    }
}
