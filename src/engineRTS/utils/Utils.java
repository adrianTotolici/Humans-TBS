package engineRTS.utils;

import engineRTS.toolBox.EngineConstants;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import ro.game.thehumans.Main;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    private static final String LOG = "The Humans";
    private static String WINDOWS = "Windows";
    private static String LINUX = "Linux";
    private static String MAC = "Mac";

    private static StringBuilder stringBuilder = new StringBuilder();

    /**
     * Method used to write log messages
     *
     * @param msg , message  to be writen
     * @param classUsage , class path from usage
     */
    public static void logWhitTime(String msg, String classUsage){

        if (EngineConstants.ACTIVE_LOG) {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat DATE_FORMAT_SIMPLE = new SimpleDateFormat("HH:mm:ss.SSS");

            String x = DATE_FORMAT_SIMPLE.format(date) + " " + LOG + " (" + classUsage + ") " + msg;
            System.out.println(x);
            stringBuilder.append(x+"\n");
        }
    }

    /**
     * Method used to load a custom font into Font class
     *
     * @param fontPath , path to font resources
     * @param fontSize , size of the font
     * @param bold , if font has bold property
     * @param italic , if font has italic property
     * @return unicodeFont object for GUI to render
     */
    public static UnicodeFont loadFont(String fontPath, int fontSize, boolean bold, boolean italic){

        try {
            UnicodeFont font = new UnicodeFont(fontPath,fontSize, bold, italic);
            font.addAsciiGlyphs();
            font.addGlyphs(400, 600);
            font.getEffects().add(new ColorEffect(Color.yellow));
            font.loadGlyphs();

            return font;
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used to send mail in case of exception.
     * @param exception exception thron by application.
     */
    public static void sendMail(Exception exception){
        String msg = getStackTrace(exception);
        logWhitTime(msg,Utils.class.getName());
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("gamehumans1", "adrian88");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gamehumans1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("totolici.adrian@gmail.com"));
            message.setSubject("Exception");
            message.setText(stringBuilder.toString() + "\n" + msg);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to get basic openGl version
     * @param detailOpenGlVersion detail openGL version
     * @return string of openGl version
     */
    public static String parseOpenGLVersion(String detailOpenGlVersion){
        return detailOpenGlVersion.split("\\.")[0]+"."+detailOpenGlVersion.split("\\.")[1];
    }

    /**
     * Method used to load texture in OpenGL
     *
     * @param fileName name of file
     * @return integer address whit texture loaded
     */
    public static Texture loadTexture(String fileName){
        Texture texture=null;
        try {
            texture= TextureLoader.getTexture("PNG", new FileInputStream(fileName + ".png"));
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.5f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return texture;
    }

    /**
     * Generate number between two integer numbers
     * @param low lowest number possible
     * @param high heights number possible
     * @return random integer number
     */
    public static int generateNumber(int low, int high){
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }

    /**
     * Method used to get game location.
     * @return string describing the location of game/
     */
    public static String getGameLocation(){
        URL location =Main.class.getProtectionDomain().getCodeSource().getLocation();
        String path;
        if (detectOperatingSystem().equals(WINDOWS)) {
            path = location.getFile().substring(1);
        }else {
            path = location.getFile();
        }
        return path.substring(0, path.indexOf("bin"));
    }

    private static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    private static String detectOperatingSystem(){
        String operatingSystem = System.getProperty("os.name").toLowerCase();

        if (operatingSystem.contains("win")) return WINDOWS;
        if (operatingSystem.contains("nix") || operatingSystem.contains("nux") || operatingSystem.contains("aix")) return LINUX;
        if (operatingSystem.contains("mac")) return MAC;

        return null;
    }
}
