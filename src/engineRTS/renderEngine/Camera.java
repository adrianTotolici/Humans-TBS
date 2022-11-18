package engineRTS.renderEngine;

import engineRTS.CoordinateSystems.CoordinateSystemMap;
import engineRTS.toolBox.EngineConstants;
import org.lwjgl.opengl.Display;

/**
 * Class used to render that are in players view.
 */
public class Camera {

    private static Camera instance;
    private float endRightMap = EngineConstants.TERRAIN_SIZE*EngineConstants.TERRAIN_UNIT_SIZE+10;
    private float endLeftMap = -10f;
    private float endTopMap =  -10f;
    private float endDownMap = EngineConstants.TERRAIN_SIZE*EngineConstants.TERRAIN_UNIT_SIZE+10;

    public static synchronized Camera getInstance (){
        if  (instance == null){
            instance = new Camera();
        }
        return instance;
    }

    /**
     * Method used to move camera to left
     */
    public void moveLeft(){
        if ((endLeftMap-EngineConstants.CAMERA_MOVEMENT_SPEED)>-15) {
            CoordinateSystemMap.getInstance().moveCoordinateSystemLeft(EngineConstants.CAMERA_MOVEMENT_SPEED);
            endLeftMap-=EngineConstants.CAMERA_MOVEMENT_SPEED;
            endRightMap+=EngineConstants.CAMERA_MOVEMENT_SPEED;
        }
    }

    /**
     * Method used to move camera to right
     */
    public void moveRight(){
        if ((endRightMap> Display.getWidth())) {
            CoordinateSystemMap.getInstance().moveCoordinateSystemRight(EngineConstants.CAMERA_MOVEMENT_SPEED);
            endRightMap -= EngineConstants.CAMERA_MOVEMENT_SPEED;
            endLeftMap += EngineConstants.CAMERA_MOVEMENT_SPEED;
        }
    }

    /**
     * Method used to move camera up
     */
    public void moveUp(){
        if ((endTopMap-EngineConstants.CAMERA_MOVEMENT_SPEED)>-15) {
            CoordinateSystemMap.getInstance().moveCoordinateSystemUp(EngineConstants.CAMERA_MOVEMENT_SPEED);
            endTopMap -= EngineConstants.CAMERA_MOVEMENT_SPEED;
            endDownMap += EngineConstants.CAMERA_MOVEMENT_SPEED;
        }
    }

    /**
     * Method used to move camera to down
     */
    public void moveDown(){
        if (endDownMap>Display.getHeight()) {
            CoordinateSystemMap.getInstance().moveCoordinateSystem(EngineConstants.CAMERA_MOVEMENT_SPEED);
            endDownMap -= EngineConstants.CAMERA_MOVEMENT_SPEED;
            endTopMap += EngineConstants.CAMERA_MOVEMENT_SPEED;
        }
    }

    /**
     * Method used to move camera at the midle of generated map.
     */
    public void moveCameraAtMiddle(){
        float x = (EngineConstants.TERRAIN_UNIT_SIZE * EngineConstants.TERRAIN_SIZE) /2;
        float y = (EngineConstants.TERRAIN_UNIT_SIZE * EngineConstants.TERRAIN_SIZE) /2 ;

        if ((endRightMap> Display.getWidth())) {
            CoordinateSystemMap.getInstance().moveCoordinateSystemRight(x);
            endRightMap -= x;
            endLeftMap += x;
        }

        if (endDownMap>Display.getHeight()) {
            CoordinateSystemMap.getInstance().moveCoordinateSystem(y);
            endDownMap -= y;
            endTopMap += y;
        }
    }

}
