package engineRTS.toolBox;

/**
 * Class used to enumerate texture file paths
 */
public class Textures {

    private static final String terrainPath = EngineConstants.GAME_PATH+"assets/terrain/";
    private static final String entityPath = EngineConstants.GAME_PATH+"assets/entity/";
    private static final String buildingPath = EngineConstants.GAME_PATH+"assets/entity/buildings/";
    private static final String uiPath = EngineConstants.GAME_PATH+"assets/ui/";

    public enum TerrainPath{
        GRASS(terrainPath+"grass_terrain");

        private String terrainTexturePath;

        TerrainPath(String terrainTexturePath) {
            this.terrainTexturePath = terrainTexturePath;
        }

        public String getTerrainTexturePath() {
            return terrainTexturePath;
        }
    }

    public enum EntityPath{
        VILLAGER_F(entityPath+"humans/civilians/unselected/villagerF"),
        VILLAGER_FR(entityPath+"humans/civilians/unselected/villagerFR"),
        VILLAGER_FL(entityPath+"humans/civilians/unselected/villagerFL"),
        VILLAGER_R(entityPath+"humans/civilians/unselected/villagerR"),
        VILLAGER_L(entityPath+"humans/civilians/unselected/villagerL"),
        VILLAGER_BL(entityPath+"humans/civilians/unselected/villagerBL"),
        VILLAGER_BR(entityPath+"humans/civilians/unselected/villagerBR"),
        VILLAGER_B(entityPath+"humans/civilians/unselected/villagerB"),
        VILLAGER_SELECTED_FL(entityPath+"humans/civilians/selected/villagerFLSelected"),
        VILLAGER_SELECTED_F(entityPath+"humans/civilians/selected/villagerFSelected"),
        VILLAGER_SELECTED_FR(entityPath+"humans/civilians/selected/villagerFRSelected"),
        VILLAGER_SELECTED_R(entityPath+"humans/civilians/selected/villagerRSelected"),
        VILLAGER_SELECTED_BR(entityPath+"humans/civilians/selected/villagerBRSelected"),
        VILLAGER_SELECTED_B(entityPath+"humans/civilians/selected/villagerBSelected"),
        VILLAGER_SELECTED_BL(entityPath+"humans/civilians/selected/villagerBLSelected"),
        VILLAGER_SELECTED_L(entityPath+"humans/civilians/selected/villagerLSelected");

        private String entityTexturePath;

        EntityPath(String entityTexturePath) {
            this.entityTexturePath = entityTexturePath;
        }

        public String getEntityTexturePath() {
            return entityTexturePath;
        }
    }

    public enum BuildingPath{
        HUT(buildingPath+"coliba");

        private String buildingTexturePath;

        BuildingPath(String buildingTexturePath) {
            this.buildingTexturePath = buildingTexturePath;
        }

        public String getBuildingTexturePath() {
            return buildingTexturePath;
        }
    }

    public enum UiPath{
        LOWER_UI(uiPath+"lower"),
        LOWER_UI_SLOT(uiPath+"slot");

        private String uiTexturePath;

        UiPath (String uiTexturePath){
            this.uiTexturePath = uiTexturePath;
        }

        public String getUiTexturePath(){ return uiTexturePath; }
    }
}
