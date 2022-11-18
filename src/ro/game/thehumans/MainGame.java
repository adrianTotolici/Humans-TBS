package ro.game.thehumans;

import engineRTS.CoordinateSystems.CoordinateSystemMap;
import engineRTS.CoordinateSystems.CoordinateSystemUi;
import engineRTS.renderEngine.Camera;
import engineRTS.renderEngine.DisplayManager;
import engineRTS.renderEngine.entity.EntityGenerate;
import engineRTS.renderEngine.entity.EntityUnit;
import engineRTS.renderEngine.terrain.TerrainGenerate;
import engineRTS.renderEngine.ui.UiGenerate;
import engineRTS.toolBox.EngineConstants;
import engineRTS.utils.Utils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

import static engineRTS.toolBox.EnumSystemSettings.OpenGlVersion.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Class that create the main game loop.
 */
public class MainGame {

    private static MainGame instance;
    private TerrainGenerate terrainGenerate;
    private EntityGenerate entityGenerate;
    private UiGenerate uiGenerate;
    private static Camera cameraInstance;

    private Vector2f[] selectionPoints = new Vector2f[2];
    private boolean endGame = false;

    private boolean placeEntity = false;
    private Texture entityTextureToPlace;

    /**
     * Method that create the main game instance. This method lunches every time a new game is started.
     * @return the main game class instance.
     */
    public static synchronized MainGame getInstance(){
        if (instance == null){
            instance = new MainGame();
        }
        return instance;
    }

    /**
     * Method that destroy the main game instance. This method is used when user exist playable game.
     */
    public static synchronized void destroyMainGameInstance(){
        instance = null;
    }

    private MainGame(){
        initialise();
        render();
    }

    private void initialise(){
        setOpenGlVersion();
        iniOpenGL();

        cameraInstance = Camera.getInstance();
        terrainGenerate = new TerrainGenerate();
        entityGenerate = new EntityGenerate();
        uiGenerate = new UiGenerate();
        terrainGenerate.createTerrain();
        entityGenerate.createEntity();
        uiGenerate.createUi();
    }

    private void setOpenGlVersion(){
        ContextAttribs contextAttributes = new ContextAttribs(3, 2);
        contextAttributes.withForwardCompatible(true);
        contextAttributes.withProfileCore(true);

        String glVersionInfo = glGetString(GL_VERSION);
        String glVersion= glVersionInfo.split(" ")[0];
        glVersion = Utils.parseOpenGLVersion(glVersion);

        String gslsVer;

        if (glVersion.equals(Gl20.getVersion())) {
            gslsVer = Gl20.getGlslVersion();
        } else if (glVersion.equals(Gl21.getVersion())) {
            gslsVer = Gl21.getGlslVersion();

        } else if (glVersion.equals(Gl30.getVersion())) {
            gslsVer = Gl30.getGlslVersion();

        } else if (glVersion.equals(Gl31.getVersion())) {
            gslsVer = Gl31.getGlslVersion();

        } else if (glVersion.equals(Gl32.getVersion())) {
            gslsVer = Gl32.getGlslVersion();

        } else if (glVersion.equals(Gl33.getVersion())) {
            gslsVer = Gl33.getGlslVersion();
            EngineConstants.ACTIVE_WATER = true;

        } else if (glVersion.equals(Gl40.getVersion())) {
            gslsVer = Gl40.getGlslVersion();
            EngineConstants.ACTIVE_WATER = true;

        } else if (glVersion.equals(Gl41.getVersion())) {
            gslsVer = Gl41.getGlslVersion();
            EngineConstants.ACTIVE_WATER = true;

        } else if (glVersion.equals(Gl42.getVersion())) {
            gslsVer = Gl42.getGlslVersion();
            EngineConstants.ACTIVE_WATER = true;

        } else if (glVersion.equals(Gl43.getVersion())) {
            gslsVer = Gl43.getGlslVersion();
            EngineConstants.ACTIVE_WATER = true;

        } else if (glVersion.equals(Gl44.getVersion())) {
            gslsVer = Gl44.getGlslVersion();
            EngineConstants.ACTIVE_WATER = true;

        } else if (glVersion.equals(Gl45.getVersion())) {
            gslsVer = Gl45.getGlslVersion();
            EngineConstants.ACTIVE_WATER = true;

        } else {
            gslsVer = glVersion;
        }
        Utils.logWhitTime("GLSL version selected is : " + gslsVer, Main.class.getName());
//        EngineConstants.GAME_PATH = EngineConstants.GAME_PATH+gslsVer+"/";
    }

    private void iniOpenGL(){
        Utils.logWhitTime("2D openGl initialise.", TerrainGenerate.class.getName());

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        GL11.glMatrixMode(GL_MODELVIEW);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
    }

    private void iniKeyListener(){

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            cameraInstance.moveLeft();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            cameraInstance.moveRight();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
            cameraInstance.moveUp();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            cameraInstance.moveDown();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            endGame = true;
        }
    }

    private void iniMouse(){

        int height = Display.getHeight();
        int width = Display.getWidth();

        int dx = Mouse.getX();
        int dy = Mouse.getY();

        if ((dx>0) && (dx<EngineConstants.MAP_MOVING_BORDER)){
            cameraInstance.moveLeft();
        }

        if (dx>(width-EngineConstants.MAP_MOVING_BORDER)){
            cameraInstance.moveRight();
        }

        if ((dy>EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT) &&
                (dy<(EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT+EngineConstants.MAP_MOVING_BORDER))){
            cameraInstance.moveDown();
        }

        if (dy>(height-EngineConstants.MAP_MOVING_BORDER)){
            cameraInstance.moveUp();
        }

        while (Mouse.isCreated() && Mouse.next()){
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0 && Mouse.isButtonDown(0) && dy>EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT) {
                    placeEntity =false;
                    Vector2f vector2f = new Vector2f(Mouse.getX(),Display.getHeight()-Mouse.getY());
                    int[] ints = CoordinateSystemMap.getInstance().searchCoordinate(vector2f);
                    if (ints != null) {
                        if (!(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))) {
                            entityGenerate.unselectedEntity();
                            uiGenerate.resetVisibleUiObject();
                        }
                        selectionPoints[0] = new Vector2f(Mouse.getX(), Display.getHeight() - Mouse.getY());
                        entityGenerate.selectEntityAt(ints[0], ints[1], uiGenerate);
                    } else {
                        Utils.logWhitTime("Mouse clicked outside of matrix.", MainGame.class.getName());
                    }
                }

                if (Mouse.getEventButton() == 1) {
                    if (Mouse.getY()>EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT) {
                        Vector2f position = new Vector2f(Mouse.getX(), Display.getHeight() - Mouse.getY());
                        if (placeEntity) {
                            int[] pos = CoordinateSystemMap.getInstance().searchCoordinate(position);
                            entityGenerate.addEntity(pos[0],pos[1], entityTextureToPlace);
                            placeEntity = false;
                        } else {
                            EntityUnit selectedUnit = entityGenerate.getSelectedUnit();
                            if (selectedUnit != null) {
                                entityGenerate.updatePosition(selectedUnit, position);
                            } else {
                                Utils.logWhitTime("No entity selected.", MainGame.class.getName());
                            }
                        }
                    }
                }

            }else {
                if (Mouse.getEventButton() == 0) {
                    if (Mouse.getY()>EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT) {
                        placeEntity = false;
                        selectionPoints[1] = new Vector2f(Mouse.getX(), Display.getHeight() - Mouse.getY());
                        List<int[]> entityBetween = entityGenerate.getEntityBetween(selectionPoints[0], selectionPoints[1]);
                        if (entityBetween !=null) {
                            entityGenerate.selectMultipleEntity(entityBetween, uiGenerate);
                            selectionPoints = new Vector2f[2];
                        }
                    }else{
                        Integer slot = CoordinateSystemUi.getInstance().searchCoordinate(new Vector2f(Mouse.getX(),
                                Display.getHeight() - Mouse.getY()));
                        if (entityGenerate != null && slot !=0) {
                            int entityType = entityGenerate.getSelectedUnit().getEntityType();
                                entityTextureToPlace = uiGenerate.getUiObject(entityType, slot);
                                placeEntity = true;
                        }
                    }
                }
            }
        }
    }

    private void render(){

        while (!Display.isCloseRequested() && !endGame){
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            iniMouse();
            iniKeyListener();

            terrainGenerate.drawTerrain();
            entityGenerate.drawEntity();
            uiGenerate.drawUi();

            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
