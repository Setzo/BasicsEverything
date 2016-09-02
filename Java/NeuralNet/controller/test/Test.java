package controller.test;

import controller.training.CreateSampleData;

public class Test {
    public static void main(String[] args) {

        String t = "2 3 1";
        @SuppressWarnings( "unused" )
        CreateSampleData test = new CreateSampleData("file.txt", CreateSampleData.xor, t, 2000);
    }
}
