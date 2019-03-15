/*
 * Plateau.java - v0.1 (03/08/2019)
 */

package xteam.marsrover;

public class Plateau {
    public Plateau(int width, int height) {
        if (width < 1) {
            throw new IllegalArgumentException("<width> cannot be less than or equal to 0");
        }
        if (height < 1) {
            throw new IllegalArgumentException("<height> cannot be less than or equal to 0");
        }

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean checkOutOfBounds(int x, int y) {
        return checkOutOfVerticalBounds(x) && checkOutOfHorizontalBounds(y);
    }

    public boolean checkOutOfHorizontalBounds(int y) {
        return y >= 0 && y <= height;
    }

    public boolean checkOutOfVerticalBounds(int x) {
        return x >= 0 && x <= width;
    }

    private final int width;
    private final int height;
}