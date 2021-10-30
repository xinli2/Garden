
/* 
 * program name: Tree.java
 * author: Xin Li
 * email: xinli2@email.arizona.edu
 */

public class Tree extends Plant {

    private String treeName;
    private int index;

    Tree(String name) {
        treeName = name;
        index = 4;
    }

    public String getName() {
        return treeName;
    }

    public void plant() {
        plant[index][2] = treeName.substring(0, 1);
    }

    public void grow() {
        index--;
        if (index >= 0) {
            plant[index][2] = treeName.substring(0, 1);
        }
    }

}
