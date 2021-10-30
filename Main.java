import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
 * program name: Main.java
 * author: Xin Li
 * email: xinli2@email.arizona.edu
 */

public class PA5Main {
    /*
     * This programe is a garden simulation. We will simulate reads and executes
     * commands such as PLANT, PRINT, GROW and HARVEST from a file. The garden
     * you are about to implement will consist of lots of rows and columns.
     * There can be a single plant in each plot. It's represented by a 5X5 cell.
     * Plants are divided into three different categories. Trees, flowers and
     * vegetables. Each of them have unique characteristics. For examples, tree
     * can grow, vegetables can grow and flowers can bloom as they grow.
     */

    public static List<String> readFile(String filename) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> file = new ArrayList<String>();
        String temp = scanner.nextLine();
        file.add(temp.substring(6));
        temp = scanner.nextLine();
        file.add(temp.substring(6));
        // skip empty line
        temp = scanner.nextLine();
        while (scanner.hasNext()) {
            temp = scanner.nextLine();
            if (temp.length() > 0) {
                file.add(temp.toLowerCase());
            }
        }
        return file;
    }

    public static void command(List<String> file, Garden garden) {
        for (int i = 2; i < file.size(); i++) {
            // plant command
            if (file.get(i).contains("plant")) {
                String[] command = file.get(i).split(" ");
                String[] position = command[1].replaceAll("^[(]+|[)]+$", "")
                        .split(",");
                garden.plant(Integer.parseInt(position[0]),
                        Integer.parseInt(position[1]), command[2]);
            }
            // print command
            else if (file.get(i).contains("print")) {
                System.out.println("> PRINT");
                garden.printGarden();
                System.out.println();
            }
            // grow command
            else if (file.get(i).contains("grow")) {
                growPlant(file.get(i), garden);
            }
            // harvest command
            else {
                harvestPlant(file.get(i), garden);
            }
        }
    }

    public static void growPlant(String rawCommand, Garden garden) {
        System.out.println("> GROW" + rawCommand.substring(4));
        String[] command = rawCommand.split(" ");
        // GROW 1
        if (command.length == 2) {
            garden.grow(Integer.parseInt(command[1]));
        }
        // GROW 1 (0,0)
        else if (command.length == 3 && command[2].charAt(0) == '(') {
            String[] position = command[2].replaceAll("^[(]+|[)]+$", "")
                    .split(",");
            garden.grow(Integer.parseInt(command[1]),
                    Integer.parseInt(position[0]),
                    Integer.parseInt(position[1]));
        }
        // GROW 1 rose or GROW 1 flower
        else {
            garden.grow(Integer.parseInt(command[1]), command[2]);
        }
        System.out.println();
    }

    public static void harvestPlant(String rawCommand, Garden garden) {
        String type = "";
        if (rawCommand.contains("harvest")) {
            System.out.println("> HARVEST" + rawCommand.substring(7));
            type = "vegetable";
        } else if (rawCommand.contains("pick")) {
            System.out.println("> PICK" + rawCommand.substring(4));
            type = "flower";
        } else {
            System.out.println("> CUT" + rawCommand.substring(3));
            type = "tree";
        }
        String[] command = rawCommand.split(" ");
        if (command.length == 1) {
            garden.harvest(type, "all");
        } else if (command.length == 2 && command[1].charAt(0) == '(') {
            String[] position = command[1]
                    .replaceAll("^[(]+|[)]+$", "")
                    .split(",");
            garden.harvest(type, Integer.parseInt(position[0]),
                    Integer.parseInt(position[1]));
        }
        else {
            garden.harvest(command[1], "");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> file = readFile(args[0]);
        Garden garden = new Garden(Integer.parseInt(file.get(0)),
                Integer.parseInt(file.get(1)));
        command(file, garden);
    }

}
