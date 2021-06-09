package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * class for managing movement patterns of objects
 */
class MovementPatterns {
    Random random;
    List squarepositions;
    List zigzagositions;
    List randompattern;
    ArrayList<Position> square;
    ArrayList<Position> zigZag;

    HashMap<String, ArrayList<Position>> movepattern;

    MovementPatterns() {
        random = new Random();
        movepattern = new HashMap<>();
        this.squarepositions = List.of(new Position(30, 30), new Position(930, 30), new Position(930, 510), new Position(30, 510));
        this.zigzagositions = List.of(new Position(300, 200), new Position(400, 340), new Position(500, 200), new Position(600, 340), new Position(700, 200), new Position(800, 340));
        square = new ArrayList<>(squarepositions);
        zigZag = new ArrayList<>(zigzagositions);
        randompattern = List.of(new Position(random.nextInt(GameView.WIDTH - 0) + 0, random.nextInt(GameView.HEIGHT - 0) + 0), new Position(random.nextInt(GameView.WIDTH - 0) + 0, random.nextInt(GameView.HEIGHT - 0) + 0), new Position(random.nextInt(GameView.WIDTH - 0) + 0, random.nextInt(GameView.HEIGHT - 0) + 0), new Position(random.nextInt(GameView.WIDTH - 0) + 0, random.nextInt(GameView.HEIGHT - 0) + 0));
        ArrayList<Position> randomPattern = new ArrayList<>(randompattern);
        movepattern.put("random", randomPattern);
        movepattern.put("square", square);
        movepattern.put("zigzag", zigZag);
    }

    /**
     *
     * @param pattern name of pattern
     * @return a selected set of positions
     */
    public ArrayList<Position> getPattern(String pattern) {
        return movepattern.get(pattern);

    }

    /**
     *
     * @return a random set of Positions to move to
     */
    public ArrayList<Position> getRandomPattern() {
        return movepattern.get("random");

    }
}
