/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 */
package engineRTS.utils;

import engineRTS.renderEngine.DisplayManager;
import engineRTS.toolBox.EngineConstants;
import engineRTS.toolBox.language.LangEn;
import org.lwjgl.opengl.Display;
import ro.game.thehumans.HumansConstants;

/**
 * Class used to crete a window.
 */
public class Window {

    private static Window windowInstance;

    private Window() {}

    /**
     * Method used to create a window instance.
     * @return instance of Window class
     */
    public synchronized static Window getWindowInstance(){
        if (windowInstance == null){
            windowInstance = new Window();
        }
        return windowInstance;
    }

    /**
     * Method used to create the window.
     */
    public void createWindow(){
        if (EngineConstants.FULL_SCREEN) {
            DisplayManager.createFullScreenDisplay(LangEn.gameTitle);
        }else {
            DisplayManager.createDisplay(EngineConstants.WINDOW_WIDTH, EngineConstants.WINDOW_HEIGHT, LangEn.gameTitle);
        }
    }

    /**
     * Method used to check if window require to be close.
     * @return boolean value if window require to be close
     */
    public boolean isWindowCloseRequired(){
        return Display.isCloseRequested();
    }

    /**
     * Method used to close window.
     */
    public void closeWindow(){
        DisplayManager.closeDisplay();
    }

    /**
     * Method used to update display (window content).
     */
    public void updateWindow(){
        DisplayManager.updateDisplay();
    }
}
