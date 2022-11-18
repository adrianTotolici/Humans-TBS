package engineRTS.renderEngine.entity;

import engineRTS.CoordinateSystems.CoordinateSystemMap;
import engineRTS.renderEngine.ui.UiGenerate;
import engineRTS.toolBox.EngineConstants;
import engineRTS.toolBox.Textures;
import engineRTS.utils.Utils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import ro.game.thehumans.GameConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class used to render all dynamic entity in the game.
 */
public class EntityGenerate {

    private HashMap<Integer,EntityUnit> entityList = new HashMap<>();
    private int[][] entityPlacement = new int[EngineConstants.TERRAIN_SIZE][EngineConstants.TERRAIN_SIZE];

    /**
     * Method used to create dynamic entity on the map.
     */
    public void createEntity(){
        int villagerNumber = Utils.generateNumber(1, 300);

        Texture[] villagerTextures = new Texture[8];
        Texture[] villagerTexturesSelected = new Texture[8];

        Texture villagerTextureF = Utils.loadTexture(Textures.EntityPath.VILLAGER_F.getEntityTexturePath());
        Texture villagerTextureFR = Utils.loadTexture(Textures.EntityPath.VILLAGER_FR.getEntityTexturePath());
        Texture villagerTextureFL = Utils.loadTexture(Textures.EntityPath.VILLAGER_FL.getEntityTexturePath());
        Texture villagerTextureB = Utils.loadTexture(Textures.EntityPath.VILLAGER_B.getEntityTexturePath());
        Texture villagerTextureBR = Utils.loadTexture(Textures.EntityPath.VILLAGER_BR.getEntityTexturePath());
        Texture villagerTextureBL = Utils.loadTexture(Textures.EntityPath.VILLAGER_BL.getEntityTexturePath());
        Texture villagerTextureL = Utils.loadTexture(Textures.EntityPath.VILLAGER_L.getEntityTexturePath());
        Texture villagerTextureR = Utils.loadTexture(Textures.EntityPath.VILLAGER_R.getEntityTexturePath());
        villagerTextures[0] = villagerTextureF;
        villagerTextures[1] = villagerTextureFL;
        villagerTextures[2] = villagerTextureFR;
        villagerTextures[3] = villagerTextureB;
        villagerTextures[4] = villagerTextureBL;
        villagerTextures[5] = villagerTextureBR;
        villagerTextures[6] = villagerTextureL;
        villagerTextures[7] = villagerTextureR;

        Texture villagerTextureSelectedF = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_F.getEntityTexturePath());
        Texture villagerTextureSelectedFL = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_FL.getEntityTexturePath());
        Texture villagerTextureSelectedFR = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_FR.getEntityTexturePath());
        Texture villagerTextureSelectedB = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_B.getEntityTexturePath());
        Texture villagerTextureSelectedBL = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_BL.getEntityTexturePath());
        Texture villagerTextureSelectedBR = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_BR.getEntityTexturePath());
        Texture villagerTextureSelectedL = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_L.getEntityTexturePath());
        Texture villagerTextureSelectedR = Utils.loadTexture(Textures.EntityPath.VILLAGER_SELECTED_R.getEntityTexturePath());
        villagerTexturesSelected[0] = villagerTextureSelectedF;
        villagerTexturesSelected[1] = villagerTextureSelectedFL;
        villagerTexturesSelected[2] = villagerTextureSelectedFR;
        villagerTexturesSelected[3] = villagerTextureSelectedB;
        villagerTexturesSelected[4] = villagerTextureSelectedBL;
        villagerTexturesSelected[5] = villagerTextureSelectedBR;
        villagerTexturesSelected[6] = villagerTextureSelectedL;
        villagerTexturesSelected[7] = villagerTextureSelectedR;

        for (int i=1; i<=villagerNumber; i++) {
            EntityUnit entityUnit = new EntityUnit(villagerTextures,villagerTexturesSelected, i, GameConstants.VILLAGER_SPEED);
            entityUnit.setEntityType(GameConstants.VILLAGER_TYPE);
            int x = Utils.generateNumber(0, EngineConstants.TERRAIN_SIZE);
            int y = Utils.generateNumber(0, EngineConstants.TERRAIN_SIZE);
            entityUnit = (EntityUnit) CoordinateSystemMap.getInstance().setVertexPointToObject(entityUnit, x, y);
            entityPlacement[x][y]=i;
            entityList.put(i, entityUnit);
        }
    }

    /**
     * Method used to draw entity on map.
     */
    public void drawEntity(){

        for (Integer key : entityList.keySet()) {
            EntityUnit entityUnit = entityList.get(key);

            List<Vector2f> listOfVertexPoints = entityUnit.getListOfVertexPoints();
            Texture texture = entityUnit.getTexture();
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
     * Method used to add new entity on the map
     * @param x , position x of new entity on the map
     * @param y , position y of new entity on the map
     * @param texture , texture of the new entity.
     */
    public void addEntity(int x, int y, Texture texture){
        Texture[] textures = new Texture[]{texture};
        EntityUnit entityUnit = new EntityUnit(textures,textures,entityList.size()+1,0);
        entityUnit.setEntityType(GameConstants.BUILDING_HOUSE_TYPE);
        entityUnit = (EntityUnit) CoordinateSystemMap.getInstance().setVertexPointToObject(entityUnit, x, y);
        entityPlacement[x][y]=entityList.size()+1;
        entityList.put(entityList.size()+1, entityUnit);
    }

    /**
     * Method used to set entity to selected mode
     * @param x index on x axis
     * @param y index on y axis
     */
    public void selectEntityAt(int x, int y, UiGenerate uiGenerate){
        int i = entityPlacement[x][y];
        if (i!=0) {
            EntityUnit entityUnit = entityList.get(i);
            if (!entityUnit.isSelected()) {
                entityUnit.setSelected(true);
                uiGenerate.setVisibleUiObject(entityUnit.getEntityType());
            }
        }
    }

    /**
     * Method used to select multiple entity
     * @param list list of int[] containing x, y coordinates of common index system
     */
    public void selectMultipleEntity(List<int[]> list, UiGenerate uiGenerate){
        if (list!=null) {
            for (int[] entity : list) {
                selectEntityAt(entity[0], entity[1], uiGenerate);
            }
        }
    }

    /**
     * Method used to set unselected state to all entity objects.
     */
    public void unselectedEntity(){
        entityList.keySet().stream().filter(key -> entityList.get(key).isSelected()).forEach(key ->
                entityList.get(key).setSelected(false));
    }

    /**
     * Method used to get all entity between two points on map
     * @param positionStart first point selected
     * @param positionEnd second point selected
     * @return list of int[] whit all entity between two coordinates
     */
    public List<int[]> getEntityBetween(Vector2f positionStart, Vector2f positionEnd){
        int[] start = CoordinateSystemMap.getInstance().searchCoordinate(positionStart);
        int[] finish = CoordinateSystemMap.getInstance().searchCoordinate(positionEnd);

        if (start!=null && finish!=null) {
            if (start[0] < finish[0]) {
                if (start[1] < finish[1]) {
                    return getPosition(start[0], finish[0], start[1], finish[1]);
                } else {
                    return getPosition(start[0], finish[0], finish[1], start[1]);
                }
            } else {
                if (start[1] < finish[1]) {
                    return getPosition(finish[0], start[0], start[1], finish[1]);
                } else {
                    return getPosition(finish[0], start[0], finish[1], start[1]);
                }
            }
        }
        return null;
    }

    /**
     * Method use to update position of an entity, this represent basic movement of an entity
     * @param entityUnit entity object we wish to move
     * @param vector2f new position of entity
     */
    public void updatePosition(EntityUnit entityUnit, Vector2f vector2f){
        if (entityUnit.getSpeed()>0) {
            int[] newPos = CoordinateSystemMap.getInstance().searchCoordinate(vector2f);
            int[] curPos = getEntityUnitIndex(entityUnit);

            if (newPos != null && curPos != null) {
                while (entityPlacement[newPos[0]][newPos[1]] != 0) {
                    newPos[0] = newPos[0] + 1;
                }
                entityPlacement[curPos[0]][curPos[1]] = 0;
                entityPlacement[newPos[0]][newPos[1]] = entityUnit.getId();

                int[] directions = getDirections(curPos, newPos);
                CoordinateSystemMap.getInstance().updateVertexPointToObject(entityUnit, newPos[0], newPos[1], directions);
            }
        }
    }

    /**
     * Method used search for selected unit.
     * @return selected entity for entity list
     */
    public EntityUnit getSelectedUnit(){
        for (Integer integer : entityList.keySet()) {
            if (entityList.get(integer).isSelected()){
                return entityList.get(integer);
            }
        }
        return null;
    }

    private List<int[]> getPosition(int startX, int finishX, int startY, int finishY){
        List<int[]> positions =new ArrayList<>();
        for (int i=startX; i<=finishX; i++){
            for (int j=startY; j<=finishY; j++){
                int id = entityPlacement[i][j];
                if (id!=0) {
                    int[] position =new int[2];
                    position[0]=i;
                    position[1]=j;
                    positions.add(position);
                }
            }
        }
        return positions;
    }

    private int[] getEntityUnitIndex(EntityUnit entityUnit){
        int id = entityUnit.getId();
        for (int i = 0; i<entityPlacement.length; i++){
            for (int j = 0; j<entityPlacement.length; j++){
                if (entityPlacement[i][j] == id){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    private int[] getDirections(int[] oldP, int[] newP){
        int dx=0;
        int dy=0;

        if (oldP[0]<newP[0]) dx = 1;
        if (oldP[0]>newP[0]) dx = -1;
        if (oldP[1]>newP[1]) dy = 1;
        if (oldP[1]<newP[1]) dy = -1;

        return new int[]{dx,dy};
    }
}
