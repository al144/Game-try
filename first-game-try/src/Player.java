import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Player {

    public static int hp = 100;
    public static int food = 20;
    public static boolean isAlive = true;
    public static int days = 0;
    public static int money = 0;
    public static boolean haveLargeBackpack = false;

    static String[] invetory = {" ", " ", " ", " ", " "};
    static  String[] backpack = {" ", " ", " "};
    static String[] largeBackpack = {" ", " ", " ", " ", " "};

    public static void writing() {

        File file = new File("player.txt");

        try {
            FileWriter writer = new FileWriter("player.txt");


            writer.write(hp + "\n");
            writer.write(food + "\n");
            writer.write(Arrays.toString(invetory) + "\n");
            writer.write(days + "\n");
            writer.close();
        }catch(Exception e){
            System.out.println(e);
            return;
        }


    }

    public static String convertToString(String[] arr){
        String str = "";
        for (String e : arr){
            str += "|" + e + "|";
        }
        return str;
    }


}
