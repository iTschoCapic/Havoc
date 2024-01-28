package en.com.choca.havoc.engine;

public class Hitbox {
    private Position position;
    private int width;
    private int height;
    private final int size = 64;

    public Hitbox(int x, int y, int width, int height) {
        this.position = new Position(x, y);
        this.width = width;
        this.height = height;
    }

    public void offset(int dx, int dy){
        this.position = new Position(getPosition().getX() + dx, getPosition().getY() + dy);
    }

    public boolean intersects(Hitbox h) {
        return this.getPosition().getX() + size >= h.getPosition().getX() && h.getPosition().getX() + size >= this.getPosition().getX() && this.getPosition().getY() + size >= h.getPosition().getY() && h.getPosition().getY() + size >= this.getPosition().getY();
    }

    public void update(int x, int y, int width, int height) {
        this.position = new Position(x, y);
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
