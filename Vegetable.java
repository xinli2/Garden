
/* 
 * program name: Vegetable.java
 * author: Xin Li
 * email: xinli2@email.arizona.edu
 */

public class Vegetable extends Plant {

    private String vegeName;
    private int index;

    Vegetable(String name) {
        vegeName = name;
        index = 0;
    }

    public String getName() {
        return vegeName;
    }

    public void plant() {
        plant[index][2] = vegeName.substring(0, 1);
    }

    public void grow() {
        index++;
        if (index <= 4) {
            plant[index][2] = vegeName.substring(0, 1);
        }
    }

}
