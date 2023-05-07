import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PlayerCanDo {

    public static String next;
    private static int counter = -1;
    private static int backpackCounter = -1;
    public static ArrayList <Integer> numBefore = new ArrayList <Integer>();

    public static Scanner answ = new Scanner(System.in);

    public enum PlayerEvent {
        hunger,
        feed,
        damage,
        heal,
        money,
        debth,
    }

    public static String readNext() {
        next = answ.next();
        return next;
    }

    public static void playerEvent(PlayerEvent event, float intesity) {
        switch(event) {
            case hunger -> Player.food -= intesity;
            case feed -> Player.food += intesity;
            case damage -> Player.hp -= intesity;
            case heal -> Player.hp += intesity;
            case money -> Player.money += intesity;
            case debth -> Player.money -= intesity;
        }
    }

    public static void inventoryAdd(String thingadd) {
        counter++;
        if(counter >= 5) {
            System.out.println("Az inventory teli");
            addToBackpack(thingadd);

        } else {
            try {
                Player.invetory[arrayIndex(" ", Player.invetory)] = thingadd;
            }catch(Exception e){
                System.out.println("Nincs üre hely az inventoridban");
            }
        }

    }

    public static boolean itemChecker(String thatContains) {
        boolean contains = Arrays.asList(Player.invetory).contains(thatContains);
        if(!contains){
            contains = Arrays.asList((Player.haveLargeBackpack ? Player.largeBackpack : Player.backpack)).contains(thatContains);
        }
        return contains;
    }

    public static int arrayIndex(String itemNeedsIndex, String[] arr) {
        if(itemChecker(itemNeedsIndex)) {
            try {
                int i;
                for(i = 0; i < arr.length; i++) {
                    if(arr[i].equals(itemNeedsIndex)) {
                        break;
                    }
                }
                return i;
            }catch(Exception e) {
                System.out.println(e);
            }

        }
        return arr.length +1;
    }

    public static void addToBackpack(String thingadd){
        if(backpackCounter < (Player.haveLargeBackpack ? Player.largeBackpack.length : Player.backpack.length) -1){
            backpackCounter++;
            if(!Player.haveLargeBackpack){
            Player.backpack[backpackCounter] = thingadd;
            return;
            }
            Player.largeBackpack[backpackCounter] = thingadd;
            return;
        }else System.out.println("A backpack teli");
    }

    public static void removeItem(String thingremove){
        if(!itemChecker(thingremove)){
            try {
                arrayIndex(thingremove, (Player.haveLargeBackpack ? Player.largeBackpack : Player.backpack));
            }catch(Exception e){
                System.out.println("Nincs ilyen itemed");
            }
        }
        try {
            Player.invetory[arrayIndex(thingremove, Player.invetory)] = " ";
        }catch(Exception e) { }
    }


    public static <numBefore> void storeNumBefor(int eventNum){
        numBefore.add(eventNum);
    }

    public static int randomNumber(int howMany, boolean isFight){
        int random = (int) Math.floor((Math.random()*howMany+(isFight ? +30: + 0)));
        return random;
    }

    public static void merchantTrade(int answer){
        switch(answer){
            case 1:
                if(itemChecker("Kés")){
                    removeItem("Kés");
                    inventoryAdd("Váza");
                    return;
                }
                System.out.println("Nincs késed.");
                System.out.println("A merchant elmegy.");
                break;
            case 2:
                if(itemChecker("Kés")){
                    Player.money += 10;
                }
                break;
            case 3:
                if(itemChecker("Kés")){
                    playerEvent(PlayerEvent.feed, 3);
                }
                break;
            case 4:
                if(Player.money >= 10){
                    playerEvent(PlayerEvent.debth, 10);
                    playerEvent(PlayerEvent.feed, 5);
                }
                break;
            case 5:
                if(!itemChecker(" ")){
                    return;
                }
                if(Player.money < 4){
                    return;
                }
                playerEvent(PlayerEvent.debth, 4);
                inventoryAdd("Deszka");
                break;
        }
    }

    public static void merchantCommands(String cmd){
        if(cmd.equals("D")){
            Player.money += 100;
            return;
        }
        System.out.println("A merchant elmegy.");
    }

    public static int strToInt(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch(Exception e) {
            merchantCommands(str);
        }
        return i;
    }

    public static void craft(int answer){
        switch(answer){
            case 1:
                if(!itemChecker("Deszka")){
                    return;
                }
                if(!itemChecker("Kés")){
                    System.out.println("hiba 2");
                    return;
                }
                removeItem("Deszka");
                inventoryAdd("Gyengelándzsa");
                break;
            case 2:
                if(!itemChecker("Kés")){
                    return;
                }
                if(!itemChecker("Deszka")){
                    return;
                }
                if(!itemChecker("Kötél")) {
                    return;
                }
                removeItem("Kés");
                removeItem("Deszka");
                removeItem("Kötél");
                inventoryAdd("Erőslándzsa");
                break;
        }
    }

    public static void dayhunger(){
        if(Player.days %3 == 0){
            Player.food --;
            System.out.println("-1 food");
        }else return;
    }

    public static void dead(){
        Player.hp = 0;
        Player.food = 0;
        Player.isAlive = false;
    }


    public static void stat(){
        System.out.println("Days: " + Player.days);
        System.out.println("Hp: " + Player.hp);
        dayhunger();
        System.out.println("Food: " + Player.food);
        System.out.println("Pénz: " + Player.money);
        System.out.println("Inventory: " + Player.convertToString(Player.invetory));
        System.out.println((Player.haveLargeBackpack ? "Large Backpack" + Player.convertToString(Player.largeBackpack) : "Small Backpack" + Player.convertToString(Player.backpack) ));
    }
}
