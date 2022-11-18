package engineRTS.renderEngine.entity;

import engineRTS.CoordinateSystems.CoordinateSystemMap;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent all dynamic entity.
 */
public class EntityUnit {

    private Texture[] selectedTexture = new Texture[8];
    private Texture[] unselectedTexture = new Texture[8];
    private int id;
    private boolean selected = false;
    private List<Vector2f> listOfVertexPoints = new ArrayList<>();
    private float speed;
    private int[] direction;
    private int entityType;

    /**
     * Constructor of entity class
     * @param unselectedTexture entity texture used on rendering.
     * @param selectedTexture entity selection texture used on rendering.
     * @param id entity id, used for identify different entity.
     * @param speed entity speed on movement.
     */
    public EntityUnit(Texture[] unselectedTexture,Texture[] selectedTexture, int id, float speed) {
        this.selectedTexture=selectedTexture;
        this.unselectedTexture=unselectedTexture;
        this.id = id;
        this.speed = speed;
        this.direction = new int[]{0,0};
    }

    /**
     * Method used to get the list of vertex points.
     * @return list of 2D vectors representing position of entity.
     */
    public synchronized List<Vector2f> getListOfVertexPoints() {
        return listOfVertexPoints;
    }

    /**
     * Method used to and vertex point.
     * @param vertexPoint 2D float vector representing vertexPoint
     */
    public void addVertexPoint(Vector2f vertexPoint){
        listOfVertexPoints.add(vertexPoint);
    }

    /**
     * Method used to update entity vertex;
     * @param listOfVertexPoints new vertex list.
     */
    public void setListOfVertexPoints(List<Vector2f> listOfVertexPoints) {
        this.listOfVertexPoints = listOfVertexPoints;
    }

    /**
     * Method used to get texture of an entity
     * @return texture object of entity
     */
    public Texture getTexture() {
        switch (direction[0]) {
            case 0:
                switch (direction[1]) {
                    case 1:
                        return getTypeTexture(3);
                    default:
                        return getTypeTexture(0);
                }
            case 1:
                switch (direction[1]){
                    case 1:
                        return getTypeTexture(5);
                    case -1:
                        return getTypeTexture(2);
                    default:
                        return getTypeTexture(7);
                }
             case -1:
                switch (direction[1]){
                    case 0:
                        return getTypeTexture(6);
                    case -1:
                        return getTypeTexture(1);
                    default:
                        return getTypeTexture(4);
                }

            default:
                return getTypeTexture(0);
        }
    }

    /**
     * Method used to get ID of an entity
     * @return integer representing id of an entity
     */
    public int getId() {
        return id;
    }

    /**
     * Method used to check if entity is selected.
     * @return boolean value representing if entity is selected
     */
    public synchronized boolean isSelected() {
        return selected;
    }

    /**
     * Method used to mark an entity as selected
     * @param selected boolean value representing selection state of entity
     */
    public synchronized void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Method used to update sprit position
     * @param directionX X direction of movement
     * @param directionY Y direction of movement
     * @return new position on coordinate system
     */
    public int[] updatePosition(int directionX, int directionY){
        float x0 = listOfVertexPoints.get(0).getX() + (speed * directionX);
        float y0 = listOfVertexPoints.get(0).getY() + (speed * directionY);
        listOfVertexPoints.set(0,new Vector2f(x0,y0));

        float x1 = listOfVertexPoints.get(1).getX() + (speed * directionX);
        float y1 = listOfVertexPoints.get(1).getY() + (speed * directionY);
        listOfVertexPoints.set(1,new Vector2f(x1,y1));

        float x2 = listOfVertexPoints.get(2).getX() + (speed * directionX);
        float y2 = listOfVertexPoints.get(2).getY() + (speed * directionY);
        listOfVertexPoints.set(2,new Vector2f(x2,y2));

        float x3 = listOfVertexPoints.get(3).getX() + (speed * directionX);
        float y3 = listOfVertexPoints.get(3).getY() + (speed * directionY);
        listOfVertexPoints.set(3, new Vector2f(x3, y3));

        return CoordinateSystemMap.getInstance().searchCoordinate(listOfVertexPoints.get(0));
    }

    /**
     * Method used to set direction of entity movement.
     * @param direction vector whit two values,1 for forward and -1 for backward,
     *                  int[0] direction on X , int[1] direction on Y.
     */
    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    private Texture getTypeTexture(int position){
        if (selected){
            return selectedTexture[position];
        }else {
            return unselectedTexture[position];
        }
    }

    /**
     * Method to get entity type.
     * @return string whit the type of entity.
     */
    public int getEntityType() {
        return entityType;
    }

    /**
     * Method used to set the name type of an entity.
     * @param entityType , int type name for this entity.
     */
    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    /**
     * Method used to get speed of entity
     * @return float representing the speed of an entity
     */
    public float getSpeed() {
        return speed;
    }
}
