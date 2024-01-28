package en.com.choca.havoc.launcher;

public enum Entity {
    Empty('_'),
    Snow('s'),
    Stone('c');
    

    private final char code;

    Entity(char c) {
        this.code = c;
    }

    public char getCode() { return this.code; }

    public static Entity fromCode(char c) {
        for (Entity entity : values()) {
            if (entity.code == c)
                return entity;
        }
        throw new MapException("Invalid character " + c);
    }

    @Override
    public String toString() {
        return Character.toString(code);
    }

}
