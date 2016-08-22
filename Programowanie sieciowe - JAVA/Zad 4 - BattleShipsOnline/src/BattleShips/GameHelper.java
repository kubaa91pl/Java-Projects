package BattleShips;

/**
 * Created by Admin on 2016-06-24.
 */
final class GameHelper{
    public static int score1 = 0;
    public static int score2 = 0;
    public final static int maxScore = 20;
    public static boolean serversTurn;
    public final static String shootCode = "345#$%$#454";
    public final static String chatCode = "345$$#$%453##4";
    public final static String scored = "#$%^&^%$GDD";
    public final static String missed = "ddfgdf%^%$#";
    public static boolean ChatOn;
    public static boolean myTurn;
    public static boolean enemyisRead = false;
    private static final String READY = "76543456789876";



    public static void showScore(){
        System.out.println("Player1: " + String.valueOf(score1) + "       " + "Player2: " + String.valueOf(score2));
    }

    public static void showCommands(){
        System.out.println("Dostepne komendy:");
        //System.out.println("p - dla ustawiania statku");
        System.out.println("s x y- dla strzalu, gdzie x, y to wspolrzedne");
        System.out.println("c - dla czatu");
        System.out.println("show - dla pokazania plansz");
        System.out.println("help - dla wyswietlenia komend");
    }

    public static boolean endGame(){
        if(score1<maxScore && score2 <maxScore){
            return false;
        }
        else {
            if(score1>score2) {
                System.out.println("Gratulacje , wygrales");
            }
            else System.out.println("Pokonany.");
            return true;
        }
    }
}
