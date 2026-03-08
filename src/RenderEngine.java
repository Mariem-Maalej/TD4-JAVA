import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {

    private ArrayList<Displayable> renderList; // l'ajout de l’attribut privé renderList
    // initialisation de la liste
    public RenderEngine() {
        super();
        renderList = new ArrayList<>();
    }
    // création d'un setter
    public void setRenderList(ArrayList<Displayable> renderList) {
        this.renderList = renderList;
    }
    // l'ajout d'un élément dans la liste

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g); // important : nettoie/redessine le fond correctement
        for (Displayable d : renderList) {
            d.draw(g); // polymorphisme : appelle le bon draw()
        }
    }
    @Override
    public void update() {
        repaint();
    }





}
