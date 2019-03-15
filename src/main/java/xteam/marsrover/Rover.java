/*
 * Rover.java - v0.1 (03/08/2019)
 */

package xteam.marsrover;

public class Rover {
    public Rover(Plateau plateau, int positionX, int positionY, Heading heading) {
        if (plateau == null) {
            throw new IllegalArgumentException("<plateau> cannot be null");
        }
        if (!plateau.checkOutOfVerticalBounds(positionX)) {
            throw new IllegalArgumentException("<positionX> cannot be out of range");
        }
        if (!plateau.checkOutOfHorizontalBounds(positionY)) {
            throw new IllegalArgumentException("<positionY> cannot be out of range");
        }

        this.plateau = plateau;
        position = new Vector(positionX, positionY);
        velocity = new Vector(0, 1);
        switch (heading) {
            case SOUTH:
                velocity.setXY(0, -1);
                break;
            case EAST:
                velocity.setXY(1, 0);
                break;
            case WEST:
                velocity.setXY(-1, 0);
                break;
        }
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Heading getHeading() {
        if (velocity.getX() != 0) {
            return velocity.getX() > 0 ? Heading.EAST : Heading.WEST;
        } else {
            assert velocity.getY() != 0;
            return velocity.getY() > 0 ? Heading.NORTH : Heading.SOUTH;
        }
    }

    public void rotateLeft() {
        velocity.setXY(-velocity.getY(), velocity.getX());
    }

    public void rotateRight() {
        velocity.setXY(velocity.getY(), -velocity.getX());
    }

    public boolean move() {
        final int newX = position.getX() + velocity.getX();
        final int newY = position.getY() + velocity.getY();
        if (plateau.checkOutOfBounds(newX, newY)) {
            position.setXY(newX, newY);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getPositionX() + " " + getPositionY() + " " + getHeading();
    }

    private final Plateau plateau;

    private final Vector position;
    private final Vector velocity;
}