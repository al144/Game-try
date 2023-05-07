import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int hp = Player.hp;
        int food = Player.food;
        String[] invetory = Player.invetory;


        while(Player.isAlive) {
            Player.days++;
            PlayerCanDo.stat();
            Player.writing();
            playerAlive();
            endLine();
        }
        System.out.println("Game over");
    }

    private static void playerAlive(){
        int random = randomDecider();
        PlayerCanDo.storeNumBefor(randomDecider());
        System.out.println(PlayerCanDo.numBefore);

        if(random == 0){
            System.out.println("Találtál egy dobozt megnézed? y/n");
            PlayerCanDo.readNext();
            if(!PlayerCanDo.next.equals("n")) {
                System.out.println("Találtál egy kést");
                PlayerCanDo.inventoryAdd("Kés");
                return;
            }
            System.out.println("Visszaraktad a dobozt.");
            return;
        }
        if(random == 1) {
            System.out.println("Reggel kopogásra kelsz fel. Egy merchant kopog. Kinyitod neki az ajtót? y/n");
            String next = PlayerCanDo.readNext();
            if(!next.equals("n")) {
                System.out.println("A merchant ajánlatai:");
                System.out.println("1: Kés -> 1 Váza");
                System.out.println("2: Kés -> 10 Pénz");
                System.out.println("3: Kés -> 3 Étel ");
                System.out.println("4: 10 Pénz -> 5 Étel");
                System.out.println("5: 4 Pénz -> 1 Deszka");

                String merchantCommands = PlayerCanDo.readNext();


                PlayerCanDo.merchantTrade(PlayerCanDo.strToInt(merchantCommands));
                return;
            }
            System.out.println("A merchant elmegy");
            return;
        }
        if(random == 2) {
            System.out.println("Lementél a pincébe és ott találtál egy kalapácsot és szögeket");
            System.out.println("Akarsz barkácsolni? y/n");
            String next = PlayerCanDo.readNext();
            if(!next.equals("n")){
                System.out.println("Alapanyag | Eszközök");
                System.out.println("1: Deszka | Kés -> Gyengelándzsa ");
                System.out.println("2: Kés, Deszka Kötél -> Erőslándzsa");

                String answer = PlayerCanDo.readNext();

                PlayerCanDo.craft(PlayerCanDo.strToInt(answer));
                return;
            }
            return;
        }
        if(random == 3) {
            System.out.println("Meghaltál");
            Player.isAlive = false;
            return;
        }
        if(random == 4) {
            System.out.println("");
            return;
        }
        if(random == 5) {
            System.out.println("");
            return;
        }
        if(random == 6) {
            System.out.println("");
            return;
        }
        if(random == 7) {
            System.out.println("");
            return;
        }
        if(random == 8) {
            System.out.println("");
            return;
        }
        if(random == 9) {
            System.out.println("");
            return;
        }


        // fight events

        if(random == 30){
            System.out.println("fight");
            PlayerCanDo.readNext();
            //PlayerCanDo.removeItem("Kés");
            if(!PlayerCanDo.next.equals("n")) {
                System.out.println("fight 1");
                return;
            }
            System.out.println("no");
            return;
        }
        if(random == 31) {
            System.out.println("fight 2");
            PlayerCanDo.readNext();
            //PlayerCanDo.removeItem("Kés");
            if(!PlayerCanDo.next.equals("n")) {
                System.out.println("fight 2");
                return;
            }
            System.out.println("no");
            return;
        }
        if(random == 32) {
            System.out.println("fight 3");
            PlayerCanDo.readNext();
            //PlayerCanDo.removeItem("Kés");
            if(!PlayerCanDo.next.equals("n")) {
                System.out.println("Fight 3");
                return;
            }
            System.out.println("no");
            return;
        }
    }

    public static int randomDecider(){
        if(/*PlayerCanDo.inventoryChecker("Kés") &&*/ Player.days%4 == 0) {
            int randrand = PlayerCanDo.randomNumber(3, false);
            if(randrand != 1) {
                //return PlayerCanDo.randomNumber(10, false);
                return 3;
            }
            return PlayerCanDo.randomNumber(3, true);
        }else {
            //return PlayerCanDo.randomNumber(10, false);
            return 3;
        }
    }

    public static void endLine(){
        for(int i = 0; i < 200; i++){
            System.out.print("_");
        }
        System.out.println();
    }

}