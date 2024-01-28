package en.com.choca.havoc.engine;

public record Position (int x, int y) {

    public Position(Position position) {
        this(position.x, position.y);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Direction Compaire(Position position, boolean alreadyTried){
        if (this.x > position.x){
            return Direction.LEFT;
        } else if (this.x < position.x){
            return Direction.RIGHT;
        } else {
            if (this.y > position.y){
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        }
        
    }
}

