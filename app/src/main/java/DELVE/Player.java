package DELVE;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Player {

    SharedPreferences sharedPrefPlayer;

    // default name npc's may contextually address the player as
    // in future, may be customizable
    private String characterName = "Wayfarer";

    // Each location has an ID
    // private int currentLocationID = 0;

    private int numberOfSwipes = 0;

    // Support for buffs / debuffs to represent a player's status beyond vitality
    private Effect[] playerEffects = {Effect.HEALTHY};
    private int playerVitality = 7;

    // Abritrary limit of 10000 items
    private String[] items = new String[10000];

    private enum Effect {
        HEALTHY,
        SICK,
    }

    private void intializePlayer(Context context) {
        sharedPrefPlayer = context.getSharedPreferences("myPref", MODE_PRIVATE);
    }

    public int getCurrentLocationID() {
        return sharedPrefPlayer.getInt("currentLocationID", 0);
    }

    public void setCurrentLocationID(int currentLocationID) {
        sharedPrefPlayer.edit().putInt("currentLocationID", currentLocationID).commit();
    }

    public int getNumberOfSwipes() {
        return sharedPrefPlayer.getInt("numberOfSwipes", 0);
    }

    public void increaseNumberOfSwipes() {
        sharedPrefPlayer.edit().putInt("numberOfSwipes", getNumberOfSwipes() + 1).commit();
    }

    public String getPlayerName() {
        return characterName;
    }

}
