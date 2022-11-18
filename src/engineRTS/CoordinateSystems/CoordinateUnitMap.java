package engineRTS.CoordinateSystems;

import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store COORDINATE position
 */
public class CoordinateUnitMap {

    private List<Vector2f> vertexArray = new ArrayList<>();

    /**
     * Method used to add a vertex point to new COORDINATE position
     * @param vertexPoint vector2f representing a point of vertex
     */
    public void addVertexArray(Vector2f vertexPoint){
        vertexArray.add(vertexPoint);
    }

    /**
     * Method used to get all vertexPoints of a position
     * @return list whit vertex points in a vector2f
     */
    public List<Vector2f> getVertexArray() {
        return vertexArray;
    }
}
