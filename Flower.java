
/* 
 * program name: Flower.java
 * author: Xin Li
 * email: xinli2@email.arizona.edu
 */

public class Flower extends Plant {

    private String flowerName;

    Flower(String name) {
        flowerName = name;
    }

    public String getName() {
        return flowerName;
    }

    public void plant() {
        plant[2][2] = flowerName.substring(0, 1);
    }

    public void grow() {
        String symbol = flowerName.substring(0, 1);
        boolean[][] before = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (plant[i][j].equals(symbol)) {
                    before[i][j] = true;
                } else {
                    before[i][j] = false;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (before[i][j] && i - 1 >= 0) {
                    plant[i - 1][j] = symbol;
                }
                if (before[i][j] && i + 1 <= 4) {
                    plant[i + 1][j] = symbol;
                }
                if (before[i][j] && j - 1 >= 0) {
                    plant[i][j - 1] = symbol;
                }
                if (before[i][j] && j + 1 <= 4) {
                    plant[i][j + 1] = symbol;
                }
            }
        }
    }

}
