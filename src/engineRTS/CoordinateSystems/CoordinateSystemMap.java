package engineRTS.CoordinateSystems;

import engineRTS.renderEngine.entity.EntityUnit;
import engineRTS.renderEngine.terrain.TerrainUnit;
import engineRTS.toolBox.EngineConstants;
import org.lwjgl.util.vector.Vector2f;

import java.util.List;

/**
 * Class used to set a common coordinate system in game
 */
public class CoordinateSystemMap {

    private static CoordinateSystemMap instance;
    private CoordinateUnitMap[][] coordinate;

    private CoordinateSystemMap(){
        buildEntityPosition(EngineConstants.TERRAIN_SIZE,EngineConstants.TERRAIN_SIZE);
    }

    /**
     * Method used to get a single instance of coordinate system entity.
     * @return instance of class CoordinateSystemMap
     */
    public static synchronized CoordinateSystemMap getInstance(){
        if (instance == null){
            instance = new CoordinateSystemMap();
        }
        return instance;
    }

    /**
     * Method used to build entity coordinate system used by entity for rendering
     * @param terrainWidth integer representing maximum height of coordinate system
     * @param terrainHeight integer representing minimum width of coordinate system
     */
    public void buildEntityPosition(int terrainWidth, int terrainHeight){
        coordinate = new CoordinateUnitMap[terrainWidth][terrainHeight];
        for (int i=0; i<terrainWidth; i++){
            for(int j=0; j<terrainHeight; j++){
                CoordinateUnitMap coordinateUnitMap = new CoordinateUnitMap();
                coordinateUnitMap.addVertexArray(new Vector2f(i * EngineConstants.TERRAIN_UNIT_SIZE, j * EngineConstants.TERRAIN_UNIT_SIZE));
                coordinateUnitMap.addVertexArray(new Vector2f((i * EngineConstants.TERRAIN_UNIT_SIZE) + EngineConstants.TERRAIN_UNIT_SIZE, j * EngineConstants.TERRAIN_UNIT_SIZE));
                coordinateUnitMap.addVertexArray(new Vector2f((i * EngineConstants.TERRAIN_UNIT_SIZE) + EngineConstants.TERRAIN_UNIT_SIZE, (j * EngineConstants.TERRAIN_UNIT_SIZE) + EngineConstants.TERRAIN_UNIT_SIZE));
                coordinateUnitMap.addVertexArray(new Vector2f(i * EngineConstants.TERRAIN_UNIT_SIZE, (j * EngineConstants.TERRAIN_UNIT_SIZE) + EngineConstants.TERRAIN_UNIT_SIZE));

                coordinate[i][j] = coordinateUnitMap;
            }
        }
    }

    /**
     * Method used to set coordinate vertex points to entity
     * @param object , entity to set new coordinate system
     * @param i , index of width
     * @param j, index of height
     * @return , entity object whit new coordinate system implemented
     */
    public Object setVertexPointToObject(Object object, int i, int j){
        List<Vector2f> vertexArray = coordinate[i][j].getVertexArray();

        if (object instanceof TerrainUnit){
            TerrainUnit terrainUnit = (TerrainUnit) object;
            vertexArray.forEach(terrainUnit::addVertexPoint);
            return terrainUnit;
        }

        if (object instanceof EntityUnit){
            EntityUnit entityUnit = (EntityUnit) object;
            vertexArray.forEach(entityUnit::addVertexPoint);
            return entityUnit;
        }

        return null;
    }

    /**
     * Method used to change coordinate of an entity.
     * @param object entity to change it position
     * @param i new coordinate value on X axis
     * @param j new coordinate value on Y axis
     * @param direction direction of entity to be moved
     * @return object whit new vertexes and new coordinate on map.
     */
    public Object updateVertexPointToObject(Object object, int i, int j, int[] direction){
        List<Vector2f> vertexArray = coordinate[i][j].getVertexArray();

        if (object instanceof EntityUnit){
            EntityUnit entityUnit = (EntityUnit) object;
            entityUnit.setListOfVertexPoints(vertexArray);
            entityUnit.setDirection(direction);
            return entityUnit;
        }

        return null;
    }

    /**
     * Method used to move coordinate system to left.
     * @param moveLeft float number representing moment iteration.
     */
    public void moveCoordinateSystemLeft(float moveLeft){
        for (CoordinateUnitMap[] coordinateUnitMaps : coordinate) {
            for (CoordinateUnitMap coordinateUnitMap : coordinateUnitMaps) {
                List<Vector2f> vertexArray = coordinateUnitMap.getVertexArray();
                for (Vector2f vector2f : vertexArray) {
                    vector2f.set(vector2f.x+moveLeft ,vector2f.y);
                }
            }
        }
    }

    /**
     * Method used to move coordinate system to left
     * @param moveRight float number representing movement to right
     */
    public void moveCoordinateSystemRight(float moveRight){
        for (CoordinateUnitMap[] coordinateUnitMaps : coordinate) {
            for (CoordinateUnitMap coordinateUnitMap : coordinateUnitMaps) {
                List<Vector2f> vertexArray = coordinateUnitMap.getVertexArray();
                for (Vector2f vector2f : vertexArray) {
                    vector2f.set(vector2f.x-moveRight ,vector2f.y);
                }
            }
        }
    }

    /**
     *
     * Method used to move coordinate system up
     * @param moveUp float number representing up movement
     */
    public void moveCoordinateSystemUp(float moveUp){
        for (CoordinateUnitMap[] coordinateUnitMaps : coordinate) {
            for (CoordinateUnitMap coordinateUnitMap : coordinateUnitMaps) {
                List<Vector2f> vertexArray = coordinateUnitMap.getVertexArray();
                for (Vector2f vector2f : vertexArray) {
                    vector2f.set(vector2f.x ,vector2f.y+moveUp);
                }
            }
        }
    }

    /**
     * Method used to move coordinate system down
     * @param moveDown float number representing down movement
     */
    public void moveCoordinateSystem(float moveDown){
        for (CoordinateUnitMap[] coordinateUnitMaps : coordinate) {
            for (CoordinateUnitMap coordinateUnitMap : coordinateUnitMaps) {
                List<Vector2f> vertexArray = coordinateUnitMap.getVertexArray();
                for (Vector2f vector2f : vertexArray) {
                    vector2f.set(vector2f.x ,vector2f.y-moveDown);
                }
            }
        }
    }

    /**
     * Method used to search index coordinate by position parameters.
     * @param position a float vector representing the position on screen
     * @return vector whit index of position in common coordinate system
     */
    public int[] searchCoordinate(Vector2f position){
        int[] index = new int[2];
        float x = position.getX();
        float y = position.getY();

        for (int i=0; i<coordinate.length; i++){
            for (int j=0; j<coordinate.length; j++){
                List<Vector2f> vertexArray = coordinate[i][j].getVertexArray();

                Vector2f vertexPointMin = vertexArray.get(0);
                Vector2f vertexPointMax = vertexArray.get(2);

                if (((x>vertexPointMin.getX())&&(x<vertexPointMax.getX()))&&
                ((y>vertexPointMin.getY())&&(y<vertexPointMax.getY()))){
                    index[0]=i;
                    index[1]=j;
                    return index;
                }
            }
        }
        return null;
    }
}
