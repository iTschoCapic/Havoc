package en.com.choca.havoc.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.entities.structures.*;
import en.com.choca.havoc.launcher.Entity;

public class Grid {

    private int width;
    private int height;
    private int size = 64;
    private Entity[][] entities;
    private final Map<Position, Structure> elements = new HashMap<>();

    public Grid(int width, int height, Entity[][] entities) {
        this.width = width*size;
        this.height = height*size;
        this.entities = entities;
        for (int x = 0; x < this.width/size; x++){
            for (int y = 0; y < this.height/size; y++) {
                Entity entity = entities[x][y];
                findEntity(entity, new Position(x*size, y*size));
            }
        }
    }

    private void findEntity(Entity entity, Position position){
        switch (entity) {
            case Snow:
                elements.put(position, new Snow(position));
                break;
            case Stone:
                elements.put(position, new Stone(position));
                break;
            case Empty:
                break;
            default:
                throw new RuntimeException("EntityCode " + entity.name() + " not processed");
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Entity getEntity(int x, int y) {
        return this.entities[x][y];
    }

    public void setEntity(Entity entity, int x, int y) {
        this.entities[x][y] = entity;
        findEntity(entity, new Position(x, y));
    }

    public void remove(Position position) {
        elements.remove(position);
    }
    
    public Collection<Structure> values() {
        return elements.values();
    }

    public Structure getStructure(Position position){
        return elements.get(position);
    }

    public boolean inside(Position position){
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < this.width && position.getY() < this.height;
    }
}
