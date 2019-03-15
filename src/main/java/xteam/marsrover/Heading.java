/*
 * Heading.java - v0.1 (03/08/2019)
 */

package xteam.marsrover;

public enum Heading {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    Heading(String shortenName) {
        this.shortenName = shortenName;
    }

    public String getShortenName() {
        return shortenName;
    }

    @Override
    public String toString() {
        return shortenName;
    }

    public static Heading get(String shortenName) {
        for (Heading v : values()) {
            if (v.getShortenName().equalsIgnoreCase(shortenName)) {
              return v;
            }
        }
        throw new IllegalArgumentException("<shortenName> is not defined");
    }

    private final String shortenName;
}