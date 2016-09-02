package controller;

import java.io.File;
import java.util.Vector;

import controller.neural.Connection;
import controller.uti.Utilitiess;

@SuppressWarnings( "unused" )
public class Validator {

    public static boolean isEtaAlphaValid(Object e) {

        try {
            Double eta = (Double) e;
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public static boolean isInputValid(String path) {

        if (Utilitiess.getExtension(path).equals("txt")) {
            return true;
        }

        return false;
    }

    public static boolean areConnectionWeightsValid(String path) {

        if (Utilitiess.getExtension(path).equals("bin")) {
            try {
                Vector<Vector<Vector<Connection>>> tmp = Controller.loadConnectionWeightsFromFile(new File(path));

                return tmp == null ? false : true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}
