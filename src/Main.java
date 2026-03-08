import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception {

        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        renderEngine = new RenderEngine();
        // l'ajout du panneau RenderEngine à la fenêtre
        displayZoneFrame.getContentPane().add(renderEngine);
        Sprite test = new Sprite(ImageIO.read(new File("./img/tree.png")),200,300,64,64);
        renderEngine.addToRenderList(test);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),200,300,
                48,50);
        renderEngine.addToRenderList(hero);

        gameEngine = new GameEngine(hero);
        displayZoneFrame.addKeyListener(gameEngine);

        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        renderTimer.start();

        displayZoneFrame.setVisible(true);
    }



    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }

}

