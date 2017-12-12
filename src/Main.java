import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Dane do algorytmu:
        int dlugosc_tabu;
        int ilosc_porazek;

//        int[][] m = new int[][]{{0, 1, 3, 4, 5},
//                    {1, 0, 1, 4, 8},
//                    {3, 1, 0, 5, 1},
//                    {4, 4, 5, 0, 2},
//                    {5, 8, 1, 2, 0}};

//        int size = 10;
//        while (size < 2000) {
//            Matrix matrix = new Matrix(size);
//            TabuSearch tabuSearch = new TabuSearch(matrix);
//            Long start = System.currentTimeMillis();
//            tabuSearch.invoke();
//            Long stop = System.currentTimeMillis() - start;
//            System.out.println(String.format("Rozmiar: %d\t czas: %d ",size,stop));
//            size +=10;

        int[][] m = new int[][]{  {0, 633, 257,  91, 412, 150,  80, 134, 259, 505, 353, 324,  70, 211, 268, 246, 121},
                {633,   0, 390, 661, 227, 488, 572, 530, 555, 289, 282, 638, 567, 466, 420, 745, 518},
                {257, 390,   0, 228, 169, 112, 196, 154, 372, 262, 110, 437, 191,  74,  53, 472, 142},
                {91, 661, 228,   0, 383, 120,  77, 105, 175, 476, 324, 240,  27, 182, 239, 237,  84},
                {412, 227, 169, 383,   0, 267, 351, 309, 338, 196,  61, 421, 346, 243, 199, 528, 297},
                {150, 488, 112, 120, 267,   0,  63,  34, 264, 360, 208, 329,  83, 105, 123, 364,  35},
                {80, 572, 196,  77, 351,  63,   0,  29, 232, 444, 292, 297,  47, 150, 207, 332,  29},
                {134, 530, 154, 105, 309,  34,  29,   0, 249, 402, 250, 314,  68, 108, 165, 349,  36},
                {259, 555, 372, 175, 338, 264, 232, 249,   0, 495, 352,  95, 189, 326, 383, 202, 236},
                {505, 289, 262, 476, 196, 360, 444, 402, 495,   0, 154, 578, 439, 336, 240, 685, 390},
                {353, 282, 110, 324,  61, 208, 292, 250, 352, 154,   0, 435, 287, 184, 140, 542, 238},
                {324, 638, 437, 240, 421, 329, 297, 314,  95, 578, 435,   0, 254, 391, 448, 157, 301},
                {70, 567, 191,  27, 346,  83,  47,  68, 189, 439, 287, 254,   0, 145, 202, 289,  55},
                {211, 466,  74, 182, 243, 105, 150, 108, 326, 336, 184, 391, 145,   0,  57, 426,  96},
                {268, 420, 53, 239, 199, 123, 207, 165, 383, 240, 140, 448, 202,  57,   0, 483, 153},
                {246, 745, 472, 237, 528, 364, 332, 349, 202, 685, 542, 157, 289, 426, 483,   0, 336},
                {121, 518, 142,  84, 297,  35,  29,  36, 236, 390, 238, 301, 55,  96, 153, 336,   0} };

        int numOfCities = 0;
        JFileChooser chooser = new JFileChooser("C:\\Users\\Luke\\Development\\Java\\TSPv1\\resources2");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT", "txt");
        chooser.setFileFilter(filter);
        int retval = JFileChooser.APPROVE_OPTION;
        chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES);
        retval = chooser.showDialog(null, "Select");
        File file = chooser.getSelectedFile();

        try {
            LineNumberReader lineCounter = new LineNumberReader(
                    new InputStreamReader(new FileInputStream(file)));
            String nextLine = null;

            try {
                while ((nextLine = lineCounter.readLine()) != null) {
                    if (nextLine == null)
                        break;
                    System.out.println(nextLine);
                }
                numOfCities = lineCounter.getLineNumber();
                System.out.println("Total number of line in this file " + numOfCities);
            } catch (Exception done) {
                done.printStackTrace();
            }
        }
        catch (FileNotFoundException e){

        }

        int [][] n = new int [numOfCities][numOfCities];
        try {
            System.out.println("Wybrales plik: " + file.toString());
            Scanner scan = new Scanner(file);

            for (int i = 0; i < numOfCities; i++){
                for (int j = 0; j < numOfCities; j++){
                    n[i][j] = scan.nextInt();
                }
            }
            Matrix matrix = new Matrix(n);
            //matrix.printMatrix();
            TabuSearch tabuSearch = new TabuSearch(matrix);
            //Long start = System.currentTimeMillis();
            Timer timer = new Timer();
            timer.start();
            tabuSearch.invoke();
            //Long stop = System.currentTimeMillis() - start;
            // System.out.println(String.format("czas: %d ",stop));
            System.out.println("Czas = " + timer.getElapsedTime() + "sec");
            //tabuSearch.tabuAlghoritm(17, 4);
        }
        catch (FileNotFoundException e){
            System.out.println("Nie wybrales pliku");
        }
        catch (NullPointerException f){
            System.out.println("Nie wybrales pliku");
        }
    }
}
