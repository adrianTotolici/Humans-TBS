/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 */
package ro.game.thehumans;

import engineRTS.toolBox.EngineConstants;
import engineRTS.utils.Utils;

public class HumansConstants {

    private static HumansConstants instance;

    private HumansConstants(){
        EngineConstants.TERRAIN_SIZE = 100;
        EngineConstants.TERRAIN_UNIT_SIZE = 50;
        EngineConstants.CAMERA_MOVEMENT_SPEED = 3f;
        EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT = 110f;
        if (EngineConstants.GAME_MODE) {
            EngineConstants.GAME_PATH = Utils.getGameLocation();
        }
    }

    public static synchronized HumansConstants getInstance(){
        if (instance == null){
            instance = new HumansConstants();
        }
        return instance;
    }


}
