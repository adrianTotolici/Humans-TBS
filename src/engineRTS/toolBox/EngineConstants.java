package engineRTS.toolBox;

/**
 * Class used to hold engine constants, like window size or log path.
 */
public class EngineConstants {
    /** Flag used to activate and deactivate water in the world */
    public static boolean ACTIVE_WATER = false;

    /** Resource path when game is in development mode. */
    public static boolean GAME_MODE = false;

    /** Path to game location */
    public static String GAME_PATH = "";

    /** Window width when game is in window mode.*/
    public static int WINDOW_WIDTH = 640;

    /** Window height when game is in window mode.*/
    public static int WINDOW_HEIGHT = 480;

    /** Boolean value indicating if thw window is full screen, */
    public static boolean FULL_SCREEN = false;

    /** Boolean value indicating */
    public static boolean ACTIVE_LOG = true;

    /** Frames per seconds when updating window */
    public static int FRAMES_PER_SECONDS = 60;

    /** Terrain unit size. */
    public static int TERRAIN_UNIT_SIZE = 20;

    /** Map size. */
    public static int TERRAIN_SIZE = 5;

    /** Camera speed on movement. */
    public static float CAMERA_MOVEMENT_SPEED = 0.1f;

    /** Height of lower user interface. */
    public static float HEIGHT_SIZE_LOWER_UI_UNIT = 110f;

    /** Border used to mark if map has to be move. */
    public static float MAP_MOVING_BORDER = 10f;

}
