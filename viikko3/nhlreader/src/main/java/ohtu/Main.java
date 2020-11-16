package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> playerList = new ArrayList<>();
        
        System.out.println("Oliot:");
        for (Player player : players) {
            if (player.getNationality().equals("FIN")){
                playerList.add(player);
            }
        }   
        
        int finns = playerList.size();
        Player[] playersSorted = new Player[finns];
        int i = 0;
        while (i < finns){
            Player next = playerList.get(0);
            int max = next.getPoints();
            for (Player player : playerList){
                if (player.getPoints() > max){
                    next = player;
                }
            }
            playersSorted[i] = next;
            playerList.remove(next);
            i++;
        }
        
        for (Player player : playersSorted){
            System.out.println(player);
        }
    }
  
}
