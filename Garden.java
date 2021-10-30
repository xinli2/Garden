import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* 
 * program name: Garden.java
 * author: Xin Li
 * email: xinli2@email.arizona.edu
 */

public class Garden {

    private int rows;
    private int cols;
    private HashMap<String, List<String>> plantType;
    private Plant[][] garden;

    Garden(int row, int col) {
        if (col > 16) {
            System.out.println("Too many plot columns.");
            System.exit(0);
        }
        rows = row;
        cols = col;
        garden = new Plant[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                garden[i][j] = new Plant();
            }
        }
        addPlantType();
    }

    public Plant[][] getGarden() {
        return garden;
    }

    public void printGarden() {
        for (int i = 0; i < rows; i++) {
            for (int index = 0; index < 5; index++) {
                for (int j = 0; j < cols; j++) {
                    garden[i][j].printPlant(index);
                }
                System.out.println();
            }
        }
    }

    private void addPlantType() {
        plantType = new HashMap<String, List<String>>();
        plantType.put("flower", new ArrayList<String>());
        plantType.get("flower").add("iris");
        plantType.get("flower").add("lily");
        plantType.get("flower").add("rose");
        plantType.get("flower").add("daisy");
        plantType.get("flower").add("tulip");
        plantType.get("flower").add("sunflower");
        plantType.put("tree", new ArrayList<String>());
        plantType.get("tree").add("oak");
        plantType.get("tree").add("willow");
        plantType.get("tree").add("banana");
        plantType.get("tree").add("coconut");
        plantType.get("tree").add("pine");
        plantType.put("vegetable", new ArrayList<String>());
        plantType.get("vegetable").add("garlic");
        plantType.get("vegetable").add("zucchini");
        plantType.get("vegetable").add("tomato");
        plantType.get("vegetable").add("yam");
        plantType.get("vegetable").add("lettuce");
    }

    public void plant(int row, int col, String name) {
        for (String type : plantType.keySet()) {
            if (plantType.get(type).contains(name)) {
                if (type.equals("flower")) {
                    garden[row][col] = new Flower(name);
                }
                else if (type.equals("tree")) {
                    garden[row][col] = new Tree(name);
                } else {
                    garden[row][col] = new Vegetable(name);
                }
                garden[row][col].plant();
            }
        }
    }

    public void grow(int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    garden[j][k].grow();
                }
            }
        }
    }

    public void grow(int num, int row, int col) {
        if (row >= rows || row < 0 || col >= cols || col < 0) {
            System.out.println("\nCan't grow there.");
        } else {
            for (int i = 0; i < num; i++) {
                garden[row][col].grow();
            }
        }
    }

    public void grow(int num, String name) {
        for (int i = 0; i < num; i++) {
            if (plantType.containsKey(name)) {
                typeCommand(name, "grow");
            }
            else {
                for (int j = 0; j < rows; j++) {
                    for (int k = 0; k < cols; k++) {
                        if (name.equals(garden[j][k].getName())) {
                            garden[j][k].grow();
                        }
                    }
                }
            }
        }
    }

    public void harvest(String name, String command) {
        if (command.equals("all")) {
            typeCommand(name, "harvest");
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (name.equals(garden[i][j].getName())) {
                        garden[i][j].harvest();
                    }
                }
            }
        }
    }

    public void harvest(String name, int row, int col) {
        if (row >= rows || row < 0 || col >= cols || col < 0) {
            warning(name);
        } else {
            if (name.equals("flower") && garden[row][col] instanceof Flower) {
                garden[row][col].harvest();
            }
            else if (name.equals("tree") && garden[row][col] instanceof Tree) {
                garden[row][col].harvest();
            }
            else if (name.equals("vegetable") && garden[row][col] instanceof Vegetable) {
                garden[row][col].harvest();
            }
            else {
                warning(name);
            }
        }
    }

    public void warning(String name) {
        if (name.equals("flower")) {
            System.out.println("\nCan't pick there.");
        } else if (name.equals("tree")) {
            System.out.println("\nCan't cut there.");
        } else {
            System.out.println("\nCan't harvest there.");
        }
    }

    public void typeCommand(String name, String command) {
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                if (name.equals("flower") && garden[j][k] instanceof Flower) {
                    if (command.equals("grow")) {
                        garden[j][k].grow();
                    } else {
                        garden[j][k].harvest();
                    }
                } else if (name.equals("tree")
                        && garden[j][k] instanceof Tree) {
                    if (command.equals("grow")) {
                        garden[j][k].grow();
                    } else {
                        garden[j][k].harvest();
                    }
                } else if (name.equals("vegetable")
                        && garden[j][k] instanceof Vegetable) {
                    if (command.equals("grow")) {
                        garden[j][k].grow();
                    } else {
                        garden[j][k].harvest();
                    }
                }
            }
        }
    }

}
