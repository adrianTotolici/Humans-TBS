package engineRTS.CoordinateSystems;

import org.lwjgl.util.vector.Vector2f;

import java.util.List;

/**
 * Class used to set a common coordinate system in game for user interface.
 */
public class CoordinateSystemUi {

    private static CoordinateSystemUi instance;
    private CoordinateUnitUi[] lowerUi;

    private CoordinateSystemUi(int numberOfLowerUiComp) {
        lowerUi = new CoordinateUnitUi[numberOfLowerUiComp];
    }

    /**
     * Method used to get a single instance of coordinate system entity.
     * @return instance of class CoordinateSystemUI.
     */
    public static synchronized CoordinateSystemUi getFirstInstance(int numberOfLowerUiComp){
        if (instance == null){
            instance = new CoordinateSystemUi(numberOfLowerUiComp);
        }
        return instance;
    }

    /**
     * Method used to get a single instance of Coordinate system for user interface
     * @return instance of Coordinate system.
     */
    public static synchronized CoordinateSystemUi getInstance(){
        return instance;
    }

    /**
     * Method used to add user interface slots in lower bar
     * @param vector2List parameters of slot in a window
     * @param nr id number of slot
     */
    public void addUiLowerComponent(List<Vector2f> vector2List, int nr){
        CoordinateUnitUi coordinateUnitUi = new CoordinateUnitUi();
        for (Vector2f vector2f : vector2List) {
            coordinateUnitUi.addVertexArray(vector2f);
        }
        lowerUi[nr] = coordinateUnitUi;
    }

    /**
     * Method used to search index coordinate by position parameters.
     * @param position a float vector representing the position on screen
     * @return vector whit index of position in common coordinate system
     */
    public Integer searchCoordinate(Vector2f position){
        float x = position.getX();
        float y = position.getY();

        for (int i=0; i<lowerUi.length; i++){
                List<Vector2f> vertexArray = lowerUi[i].getVertexArray();

                Vector2f vertexPointMin = vertexArray.get(0);
                Vector2f vertexPointMax = vertexArray.get(2);

                if (((x>vertexPointMin.getX())&&(x<vertexPointMax.getX()))&&
                        ((y>vertexPointMin.getY())&&(y<vertexPointMax.getY()))){
                    return i;
                }
            }
        return null;
    }
}
