package en.com.choca.havoc.engine;

public enum Direction {
    UP {
        @Override
        public Position nextPosition(Position position, int delta) {
            return new Position(position.getX(), position.getY() - delta);
        }
    },
    RIGHT {
        @Override
        public Position nextPosition(Position position, int delta) {
            return new Position(position.getX() + size, position.getY()); // No delta ??
        }
    },
    DOWN {
        @Override
        public Position nextPosition(Position position, int delta) {
            return new Position(position.getX(), ((int)Math.floor((position.getX() + delta + size)/64)*64));
        }
    },
    LEFT {
        @Override
        public Position nextPosition(Position position, int delta) {
            return new Position(position.getX() - delta, position.getY());
        }
    };

    private static final int size = 64;

    public abstract Position nextPosition(Position position, int delta);

    public Position nextPosition(Position position) {
        return nextPosition(position, 1);
    }

}
