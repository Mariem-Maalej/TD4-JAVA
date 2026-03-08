import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private boolean isWalking= true;
    private double speed= 5;
    private final int spriteSheetNumberOfColumn= 10;
    private int timeBetweenFrame= 200;
    private Direction direction = Direction.SOUTH;

    public DynamicSprite(Image image, double x, double y,  double width, double height) {
        super(image, x, y, width, height);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        int index = (int) ((System.currentTimeMillis() / timeBetweenFrame) % spriteSheetNumberOfColumn);
        int attitude = direction.getFrameLineNumber();

        g.drawImage(
                image,
                (int) x,
                (int) y,
                (int) x + (int) width,
                (int) y + (int) height,
                index * (int) width,
                attitude * (int) height,
                (index + 1) * (int) width,
                (attitude + 1) * (int) height,
                null
        );
    }
    private void move() {
        switch (direction) {
            case NORTH:
                this.y -= speed;
                break;
            case SOUTH:
                this.y += speed;
                break;
            case EAST:
                this.x += speed;
                break;
            case WEST:
                this.x -= speed;
                break;
        }
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {

        Rectangle2D.Double hitBox;

        switch (direction) {
            case NORTH:
                hitBox = new Rectangle2D.Double(x, y - speed, width, height);
                break;
            case SOUTH:
                hitBox = new Rectangle2D.Double(x, y + speed, width, height);
                break;
            case EAST:
                hitBox = new Rectangle2D.Double(x + speed, y, width, height);
                break;
            case WEST:
                hitBox = new Rectangle2D.Double(x - speed, y, width, height);
                break;
            default:
                return false;
        }

        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite && sprite != this) {
                Rectangle2D.Double otherHitBox =
                        new Rectangle2D.Double(sprite.x, sprite.y, sprite.width, sprite.height);

                if (hitBox.intersects(otherHitBox)) {
                    return false;
                }
            }
        }

        return true;
    }
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }


}


