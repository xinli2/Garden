
/* 
 * program name: Plant.java
 * author: Xin Li
 * email: xinli2@email.arizona.edu
 */

public class Plant {

    protected String[][] plant;

    Plant() {
        plant = new String[5][5];
        setPlant();
    }

    public String[][] getPlant() {
        return plant;
    }

    public String getName() {
        return "";
    }

    public void setPlant() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                plant[i][j] = ".";
            }
        }
    }

    public void printPlant(int index) {
        for (int i = 0; i < 5; i++) {
            System.out.print(plant[index][i]);
        }
    }

    public void plant() {

    }

    public void grow() {

    }

    public void harvest() {
        setPlant();
    }

}
