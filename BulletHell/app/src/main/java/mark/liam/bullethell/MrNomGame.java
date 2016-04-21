package mark.liam.bullethell;

import badlogic.androidgames.framework.Screen;
import badlogic.androidgames.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}