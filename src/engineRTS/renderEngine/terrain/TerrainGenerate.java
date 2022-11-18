package engineRTS.renderEngine.terrain;

import engineRTS.CoordinateSystems.CoordinateSystemMap;
import engineRTS.toolBox.EngineConstants;
import engineRTS.toolBox.Textures;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.opengl.GL11;
import engineRTS.utils.Utils;

import java.util.List;

/**
 * Class used to create terrain in game.
 */
public class TerrainGenerate {

    private TerrainUnit[][] terrain = new TerrainUnit[EngineConstants.TERRAIN_SIZE][EngineConstants.TERRAIN_SIZE];

    /**
     * Method used to draw terrain map.
     */
    public void drawTerrain(){
        for (int i = 0; i<EngineConstants.TERRAIN_SIZE ; i++){
            for (int j = 0; j<EngineConstants.TERRAIN_SIZE; j++){
                List<Vector2f> listVertexPoints = terrain[i][j].getListVertexPoints();
                Texture texture = terrain[i][j].getTexture();

                texture.bind();
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());

                Vector2f vector2f = listVertexPoints.get(1);
                GL11.glVertex2d(vector2f.x, vector2f.y);
                GL11.glTexCoord2d(texture.getWidth(), 0);

                vector2f = listVertexPoints.get(2);
                GL11.glVertex2d(vector2f.x, vector2f.y);
                GL11.glTexCoord2d(texture.getWidth(), texture.getHeight());

                vector2f = listVertexPoints.get(3);
                GL11.glVertex2d(vector2f.x, vector2f.y);
                GL11.glTexCoord2d(0, texture.getHeight());

                vector2f = listVertexPoints.get(0);
                GL11.glVertex2d(vector2f.x,vector2f.y);
                GL11.glTexCoord2d(0, 0);

                GL11.glEnd();

            }
        }
    }

    /**
     * Method used to create all terrain.
     */
    public void createTerrain(){
        Utils.logWhitTime("Terrain created.", TerrainGenerate.class.getName());
        TerrainUnit terrainUnit;
        Texture texture = Utils.loadTexture(Textures.TerrainPath.GRASS.getTerrainTexturePath());
        for (int i = 0; i< EngineConstants.TERRAIN_SIZE; i++) {
            for (int j = 0; j < EngineConstants.TERRAIN_SIZE; j++) {
                terrainUnit = new TerrainUnit();
                terrainUnit = (TerrainUnit) CoordinateSystemMap.getInstance().setVertexPointToObject(terrainUnit, i, j);
                terrainUnit.setTexture(texture);
                terrain[i][j] =terrainUnit;
            }
        }
    }

    /**
     * Method used to get terrain units.
     * @return matrix of terrain units.
     */
    public TerrainUnit[][] getTerrain() {
        return terrain;
    }

    /**
     * Method used to set a matrix of terrain units.
     * @param terrain matrix of terrain units
     */
    public void setTerrain(TerrainUnit[][] terrain) {
        this.terrain = terrain;
    }
}
