import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TabuSearch {

    private TabuList tabuList;
    private final Matrix matrix;

    int[] currSolution;
    int numberOfIterations;
    int problemSize;

    private int[] bestSolution;
    private int bestCost;
    private int lossCounter = 0;

    public TabuSearch(Matrix matrix) {
        this.matrix = matrix;
        problemSize = matrix.getEdgeCount();
        numberOfIterations = 100000;
        tabuList = new TabuList(problemSize);
        setupCurrentSolution();
        setupBestSolution();


    }

    private void setupBestSolution() {
        bestSolution = new int[problemSize + 1];
        System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
        bestCost = matrix.calculateDistance(bestSolution);
    }

    private void setupCurrentSolution() {
        // wypelnienie obecnego rozwiazania 0,1,2,...
        currSolution = new int[problemSize + 1];
        for (int i = 0; i < problemSize; i++)
            currSolution[i] = i;
        currSolution[problemSize] = 0;
        // ustalenie nowego losowego rozwiazania
        Random r = new Random();
        for(int k = problemSize-1, j, buf; k > 1; k--)
        {
                j = r.nextInt(k);
                buf = currSolution[k];
                currSolution[k] = currSolution[j];
                currSolution[j] = buf;
        }
        for (int m = 0; m < problemSize; m++){
            if (currSolution[m] == 0){
                currSolution[m] = currSolution[0];
            }
        }
        currSolution[0] = 0;
        currSolution[problemSize] = 0;
    }


    private void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
        System.out.println();
    }

    public void invoke() {

        //for (int i = 0; i < numberOfIterations; i++) {
        Timer timer = new Timer();
        timer.start();
        long timeEnd = 250000000000L; // 60000000000 = 60 sec
        while (timer.getElapsedTime() < timeEnd){
            // Dywersyfikacja
            if (lossCounter > 150){
                //System.out.println("DYWERSYFIKACJA");
                setupCurrentSolution();
                int currCost = matrix.calculateDistance(currSolution);
                //System.out.print("chu koszt = " + currCost + "\n");
                //printSolution(currSolution);
                lossCounter = 0;
            }
            int city1 = 0;
            int city2 = 0;
            int currCostLocal = matrix.calculateDistance(currSolution);
            //System.out.println("lokalny koszt = " + currCostLocal);
            //System.out.println("globalny koszt = " + bestCost);
            if (currCostLocal < bestCost){
                System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
                bestCost = currCostLocal;
            }
            for (int j = 1; j < currSolution.length - 1; j++) {
                for (int k = 2; k < currSolution.length - 1; k++) {
                    if (j != k) {
                        swap(j, k);
                        int currCost = matrix.calculateDistance(currSolution);
                        //System.out.println("loklany koszt po swap = " + currCost);
                        if (currCost < currCostLocal){
                            currCostLocal = currCost;
                            if ((currCost < bestCost) && tabuList.tabuList[j][k] == 0 || currCost < 0.95*bestCost) {
                                city1 = j;
                                city2 = k;
                                System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
                                bestCost = currCost;
                                System.out.println((float)timer.getElapsedTime()/600000000L + " \t\t" + bestCost);
                            }
                        }else {
                            lossCounter++;
                            swap(k, j);
                        }

                    }
                }
            }


            if (city1 != 0) {
                tabuList.decrementTabu();
                tabuList.tabuMove(city1, city2);
            }
            //tabuList.printTabu();
            //System.out.println("1. Local Best Solution cost found = " + bestCost + "\nBest Solution :");
            //printSolution(bestSolution);
        }

        System.out.println("2. Search done! \nBest Solution cost found = " + bestCost + "\nBest Solution :");
        printSolution(bestSolution);
    }

    private void swap(int i, int k) {
        int temp = currSolution[i];
        currSolution[i] = currSolution[k];
        currSolution[k] = temp;
    }
}
