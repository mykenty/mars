/*
 * MarsRover.java - v0.1 (03/08/2019)
 */

package xteam.marsrover;

import java.lang.StringBuilder;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MarsRover {
    public String execute(String input) {
        try (final Scanner scanner = new Scanner(input)) {
            StringBuilder result = new StringBuilder();
            final Plateau plateau = createPlateau(scanner);
            
            if (plateau != null) {
                final List<Rover> rovers = createAndDriveRover(scanner, plateau);
                System.out.print(rovers);
                for (Rover rover : rovers) {
                    result.append(rover);
                    result.append(System.getProperty("line.separator"));
                }
            }
            return result.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    private Plateau createPlateau(Scanner scanner) {
        if (scanner.hasNextLine()) {
            final Matcher m = PLATEAU_PATTERN.matcher(scanner.nextLine());
            if (m.find()) {
                return new Plateau(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
            } else {
                throw new InputMismatchException("Input is invalid to create a Plateau");
            }
        }

        return null;
    }

    private List<Rover> createAndDriveRover(Scanner scanner, Plateau plateau) {
        final List<Rover> rovers = new ArrayList<>();        
        while (scanner.hasNextLine()) {
            final Matcher m = ROVER_PATTERN.matcher(scanner.nextLine());
            if (m.find()) {
                Rover rover = new Rover(plateau, Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Heading.get(m.group(3)));
                rovers.add(rover);
                driveRover(scanner, rover);
            } else {
                throw new InputMismatchException("Input is invalid to create a Rover");
            }
        }
        return rovers;
    }

    private void driveRover(Scanner scanner, Rover rover) {
        if (scanner.hasNextLine()) {
            final Matcher m = ROVER_INSTRUCTIONS_PATTERN.matcher(scanner.nextLine());
            if (m.find()) {
                for (char i : m.group(0).toCharArray()) {
                    switch (i) {
                        case 'L':
                        case 'l':
                            rover.rotateLeft();
                            break;
                        case 'R':
                        case 'r':
                            rover.rotateRight();
                            break;
                        case 'M':
                        case 'm':
                            if (!rover.move() && !IGNORE_WHEN_HITTING_THE_BOUNDS) {
                                return;
                            }
                            break;
                    }
                }
            } else {
                throw new InputMismatchException("Input is invalid to create Rover's instructions");
            }
        }
    }

    private static final Pattern PLATEAU_PATTERN = Pattern.compile("(\\d+)\\s+(\\d+)");
    private static final Pattern ROVER_PATTERN = Pattern.compile("(?i)(\\d+)\\s+(\\d+)\\s+([NSEW])");
    private static final Pattern ROVER_INSTRUCTIONS_PATTERN = Pattern.compile("(?i)[LRM]+");

    private static final boolean IGNORE_WHEN_HITTING_THE_BOUNDS = false;
}