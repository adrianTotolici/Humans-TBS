package engineRTS.renderEngine.terrain;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store terrain unit info.
 */
public class TerrainUnit {

    private List<Vector2f> listVertexPoints = new ArrayList<>();
    private Texture texture;

    /**
     * Method used to add a vertex point to a terrain unit
     * @param vector2f vector containing vertex coordinates.
     */
    public void addVertexPoint(Vector2f vector2f){
        listVertexPoints.add(vector2f);
    }

    /**
     * Method used to get list of vertex points for a terrain units.
     * @return list of vectors containing coordinates of terrain unit.
     */
    public List<Vector2f> getListVertexPoints(){
        return listVertexPoints;
    }

    /**
     * Method used to get texture for terrain unit
     * @return integer whit texture
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Method used to set a texture to terrain unit
     * @param texture integer representing texture.
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
