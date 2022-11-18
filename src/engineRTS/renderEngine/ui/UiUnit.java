package engineRTS.renderEngine.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created for storing information about one user interface unit block.
 */
public class UiUnit {

    private Texture uiTexture;
    private int id;
    private boolean visible;
    private List<Vector2f> listOfVertexPoints = new ArrayList<>();

    /**
     * Constructor of entity unit.
     * @param id , id parameter for ui unit.
     * @param uiTexture a texture parameter used by ui entity.
     */
    public UiUnit(int id, Texture uiTexture) {
        this.id = id;
        this.uiTexture = uiTexture;
    }

    /**
     * Method used to set the vertex point of a UI texture.
     * @param listOfVertexPoints parameter used to stor a list of end of points for texture to unfold.
     */
    public void setListOfVertexPoints(List<Vector2f> listOfVertexPoints) {
        this.listOfVertexPoints = listOfVertexPoints;
    }

    /**
     * Method used to get the id of an UI unit.
     * @return integer representing the id of UI unit.
     */
    public int getId() {
        return id;
    }

    /**
     * Method used to get all UI unit vertex points
     * @return list of vector representing the vertex points of an UI unit
     */
    public List<Vector2f> getListOfVertexPoints() {
        return listOfVertexPoints;
    }

    /**
     * Method used to get texture for UI unit.
     * @return a texture of an UI unit.
     */
    public Texture getUiTexture() {
        return uiTexture;
    }

    /**
     * Method used to check if an ui object is visible.
     * @return visibility of an ui object
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Method used to make ui object visible/invisible
     * @param visible boolean value for visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
