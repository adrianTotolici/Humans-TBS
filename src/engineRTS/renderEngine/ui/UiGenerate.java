package engineRTS.renderEngine.ui;

import engineRTS.CoordinateSystems.CoordinateSystemUi;
import engineRTS.toolBox.EngineConstants;
import engineRTS.toolBox.Textures;
import engineRTS.utils.Utils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import ro.game.thehumans.GameConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Class used to generate user interface.
 */
public class UiGenerate {

    private HashMap<Integer, UiUnit> uiUnits = new HashMap<>();

    private float SLOT_SIZE = 100f;
    private float SLOT_STEP = 10f;

    private int numberOfSlots;
    private Hashtable<Integer,UiUnit[]> uiMenuTable = new Hashtable<>();

    /**
     * Method used to create all UI for the game.
     */
    public void createUi(){

        Utils.logWhitTime("User interface created.",UiGenerate.class.getName());

        Texture lowerUi = Utils.loadTexture(Textures.UiPath.LOWER_UI.getUiTexturePath());
        Texture slotUi = Utils.loadTexture(Textures.UiPath.LOWER_UI_SLOT.getUiTexturePath());

        UiUnit uiLower = new UiUnit(0,lowerUi);
        uiLower.setListOfVertexPoints(uiLowerCords());
        uiLower.setVisible(true);
        uiUnits.put(uiLower.getId(),uiLower);

        numberOfSlots = (int) (Display.getWidth() / (SLOT_STEP + SLOT_SIZE));
        CoordinateSystemUi.getFirstInstance(numberOfSlots);

        for (int i=1; i<=numberOfSlots; i++){
            UiUnit uiLowerSlots = new UiUnit(i,slotUi);
            uiLowerSlots.setListOfVertexPoints(uiLowerSlot(i-1));
            uiLowerSlots.setVisible(true);
            uiUnits.put(uiLowerSlots.getId(),uiLowerSlots);

            CoordinateSystemUi.getInstance().addUiLowerComponent(uiLowerSlots.getListOfVertexPoints(),i-1);
        }

        createHumanSelectUi();
        createHouseSelectUi();
    }

    /**
     * Method used to draw all UI unit.
     */
    public void drawUi(){
        for (Integer key : uiUnits.keySet()) {
            UiUnit uiUnit = uiUnits.get(key);
            List<Vector2f> listOfVertexPoints = uiUnit.getListOfVertexPoints();
            Texture texture = uiUnit.getUiTexture();
            texture.bind();

            GL11.glBegin(GL11.GL_QUADS);

            Vector2f vector2f = listOfVertexPoints.get(0);
            GL11.glVertex2d(vector2f.x, vector2f.y);
            GL11.glTexCoord2d(texture.getWidth(), 0);

            vector2f = listOfVertexPoints.get(1);
            GL11.glVertex2d(vector2f.x, vector2f.y);
            GL11.glTexCoord2d(texture.getWidth(), texture.getHeight());

            vector2f = listOfVertexPoints.get(2);
            GL11.glVertex2d(vector2f.x, vector2f.y);
            GL11.glTexCoord2d(0, texture.getHeight());

            vector2f = listOfVertexPoints.get(3);
            GL11.glVertex2f(vector2f.x, vector2f.y);
            GL11.glTexCoord2f(0, 0);

            GL11.glEnd();
        }
    }

    /**
     * Method used to set ui option for selected entity
     * @param entityType integer type of entity
     */
    public void setVisibleUiObject(int entityType){
        UiUnit[] uiUnits = uiMenuTable.get(entityType);
        if (uiUnits!=null) {
            for (UiUnit uiUnit : uiUnits) {
                this.uiUnits.put(numberOfSlots + uiUnit.getId() + 1, uiUnit);
            }
        }
    }

    /**
     * Method used to reset all ui object to invisible.
     */
    public void resetVisibleUiObject(){
        List<Integer> keyUiUnitsToBeRemoved = new ArrayList<>();

        for (Integer key : uiUnits.keySet()) {
            if (key>numberOfSlots){
                keyUiUnitsToBeRemoved.add(key);
            }
        }
        for (Integer key : keyUiUnitsToBeRemoved) {
            uiUnits.remove(key);
        }
    }

    /**
     * Method used to get texture for ui option selected
     * @param objectType type of object selected
     * @param slot slot of ui selected
     * @return texture of selected option on object
     */
    public Texture getUiObject(int objectType, int slot){
        return uiMenuTable.get(objectType)[slot].getUiTexture();
    }

    private void createHumanSelectUi(){
        UiUnit[] menuList = new UiUnit[2];

        Texture slotEntityUi = Utils.loadTexture(Textures.BuildingPath.HUT.getBuildingTexturePath());
        Texture slotHumanUi = Utils.loadTexture(Textures.EntityPath.VILLAGER_F.getEntityTexturePath());

        UiUnit uiHuman = new UiUnit(0,slotHumanUi);
        uiHuman.setVisible(false);
        uiHuman.setListOfVertexPoints(uiLowerObjectSlot(0));
        menuList[0]=uiHuman;

        UiUnit uiHouse = new UiUnit(1,slotEntityUi);
        uiHouse.setVisible(false);
        uiHouse.setListOfVertexPoints(uiLowerObjectSlot(1));
        menuList[1]=uiHouse;

        uiMenuTable.put(GameConstants.VILLAGER_TYPE,menuList);
    }

    private void createHouseSelectUi(){
        UiUnit[] menuList = new UiUnit[1];
        Texture slotEntityUi = Utils.loadTexture(Textures.BuildingPath.HUT.getBuildingTexturePath());

        UiUnit uiHouse = new UiUnit(0,slotEntityUi);
        uiHouse.setVisible(false);
        uiHouse.setListOfVertexPoints(uiLowerObjectSlot(0));
        menuList[0]=uiHouse;

        uiMenuTable.put(GameConstants.BUILDING_HOUSE_TYPE,menuList);
    }

    private List<Vector2f> uiLowerCords(){
        List<Vector2f> vector2fList = new ArrayList<>();

        float x1 = 0f;
        float y1 = Display.getHeight() - EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT;

        float x2 = Display.getWidth();
        float y2 = Display.getHeight() - EngineConstants.HEIGHT_SIZE_LOWER_UI_UNIT;

        float x3 = Display.getWidth();
        float y3 = Display.getHeight();

        float x4 = 0f;
        float y4 = Display.getHeight();

        vector2fList.add(new Vector2f(x1,y1));
        vector2fList.add(new Vector2f(x2,y2));
        vector2fList.add(new Vector2f(x3,y3));
        vector2fList.add(new Vector2f(x4,y4));

        return vector2fList;
    }

    private List<Vector2f> uiLowerSlot(int unit){

        List<Vector2f> vector2fList = new ArrayList<>();

        float x1 = (unit*(SLOT_STEP+SLOT_SIZE))+SLOT_STEP;
        float y1 = (Display.getHeight()-SLOT_SIZE);

        float x2 = x1+SLOT_SIZE;
        float y2 = (Display.getHeight()-SLOT_SIZE);

        float x4 = (unit*(SLOT_SIZE+SLOT_STEP))+SLOT_STEP;
        float y4 = (Display.getHeight()-SLOT_STEP);

        float x3 = x4+SLOT_SIZE;
        float y3 = (Display.getHeight()-SLOT_STEP);

        vector2fList.add(new Vector2f(x1,y1));
        vector2fList.add(new Vector2f(x2,y2));
        vector2fList.add(new Vector2f(x3,y3));
        vector2fList.add(new Vector2f(x4,y4));

        return vector2fList;
    }

    private List<Vector2f> uiLowerObjectSlot(int unit){

        List<Vector2f> vector2fList = new ArrayList<>();

        float x1 = (unit*(SLOT_STEP+SLOT_SIZE))+SLOT_STEP;
        float y1 = (Display.getHeight()-SLOT_SIZE)+5f;

        float x2 = x1+SLOT_SIZE;
        float y2 = (Display.getHeight()-SLOT_SIZE)+5f;

        float x4 = (unit*(SLOT_SIZE+SLOT_STEP))+SLOT_STEP;
        float y4 = (Display.getHeight()-SLOT_STEP)+5f;

        float x3 = x4+SLOT_SIZE;
        float y3 = (Display.getHeight()-SLOT_STEP)+5f;

        vector2fList.add(new Vector2f(x1,y1));
        vector2fList.add(new Vector2f(x2,y2));
        vector2fList.add(new Vector2f(x3,y3));
        vector2fList.add(new Vector2f(x4,y4));

        return vector2fList;
    }
}
