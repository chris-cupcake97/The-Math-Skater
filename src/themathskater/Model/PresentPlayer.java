package themathskater.Model;

/**
 *
 * @author Christina.2114893 <christinadeborah458@gmail.com>
 */

public class PresentPlayer {

    private static PresentPlayer presentPlayer;
    private Players players;

    public void setPresentPlayer(Players player){
        this.players = player;
    }

    public Players getPresentPlayer(){
        return players;
    }



    public static PresentPlayer getPlayerInstance(){
        if(presentPlayer == null){
            presentPlayer = new PresentPlayer();
        }
        return presentPlayer;
    }

    private PresentPlayer(){
    }

}
