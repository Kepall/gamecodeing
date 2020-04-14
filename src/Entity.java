import java.awt.*;

public abstract class Entity {

    protected Game game;
    protected int width, height;

    protected float x, y;

    public Entity(Game game, float x, float y, int width, int height){
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
    public abstract void tick();

    public abstract void render(Graphics g);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
