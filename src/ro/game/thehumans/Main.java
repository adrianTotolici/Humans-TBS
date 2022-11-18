package ro.game.thehumans;

import engineRTS.utils.Utils;
import engineRTS.utils.Window;

public class Main {

    public static void main(String[] args) {

        try {
            HumansConstants.getInstance();
            Utils.logWhitTime("Initialise constants.", Main.class.getName());
            Window.getWindowInstance().createWindow();
            Utils.logWhitTime("Create window.", Main.class.getName());
            MainGame.getInstance();
        }catch (Exception e){
            Utils.sendMail(e);

        }
    }
}
