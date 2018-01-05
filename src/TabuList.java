

public class TabuList {

    int [][] tabuList ;

    public TabuList(int numCities){
        tabuList = new int[numCities][numCities];
    }

    public void tabuMove(int city1, int city2, int tabuTenure){ //tabu
        tabuList[city1][city2]+= tabuTenure;
        tabuList[city2][city1]+= tabuTenure;
    }

    public void decrementTabu(){
        for(int i = 0; i<tabuList.length; i++){
            for(int j = 0; j<tabuList.length; j++){
                if(tabuList[i][j]  > 0) {
                    tabuList[i][j]--;
                }
            }
        }
    }

    public void printTabu(){
        for(int i = 0; i < tabuList.length; i++){
            for(int j = 0; j<tabuList.length; j++){
                System.out.print(tabuList[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
