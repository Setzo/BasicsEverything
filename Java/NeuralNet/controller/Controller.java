package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

import controller.listeners.InputToGetListener;
import controller.neural.Connection;
import controller.neural.Net;
import controller.uti.Utilitiess;

public class Controller {

    private static InputToGetListener inputListener;

    public static void saveToFile(File file, StringBuilder sb) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            String[] lines = sb.toString().split("\n");
            sb.delete(0, sb.length());
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConnectionWeightsToFile(File file, Vector<Vector<Vector<Connection>>> connectionWeights) {

        if (!Utilitiess.getExtension(file.getName()).equals("bin")) {
            System.err.println("Wrong file extension");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            oos.writeObject(connectionWeights);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings( "unchecked" )
    public static Vector<Vector<Vector<Connection>>> loadConnectionWeightsFromFile(File file) {

        Vector<Vector<Vector<Connection>>> connWeights = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            connWeights = (Vector<Vector<Vector<Connection>>>) ois.readObject();

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }

        return connWeights;
    }

    public static void showInputFile(File file) {

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                if (inputListener != null) {

                    inputListener.lineToAppend(String.format("%s\n", sc.nextLine()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setInputToGetListener(InputToGetListener listener) {

        inputListener = listener;
    }

    public static void loadWeightsToNetwork(Vector<Integer> topology, File connectionWeightsFile, Net neuralNetwork) {

        if (!Utilitiess.getExtension(connectionWeightsFile.getName()).equals("bin")) {
            System.err.println("Wrong file extension");
            return;
        }

        Vector<Vector<Vector<Connection>>> connectionWeights =
                Controller.loadConnectionWeightsFromFile(connectionWeightsFile);

        neuralNetwork.setConnectionWeights(topology, connectionWeights);
    }
}