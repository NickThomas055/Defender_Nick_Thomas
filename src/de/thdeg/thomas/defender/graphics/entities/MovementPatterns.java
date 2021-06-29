package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;


import java.util.*;

/**
 * class for managing movement patterns of objects
 */
class MovementPatterns {
    Random random;

    private final HashMap<String, ArrayList<Position>> movePattern;

    MovementPatterns() {
        random = new Random();
        Comparator<Position> yAxisComparator = Comparator.comparingDouble(o -> o.y);
        movePattern = new HashMap<>();
        List<Position> squarePositions = List.of(new Position(30, 30), new Position(930, 30), new Position(930, 510), new Position(30, 510));
        List<Position> zigzagPositions = List.of(new Position(300, 200), new Position(400, 340), new Position(500, 200), new Position(600, 340), new Position(700, 200), new Position(800, 340));
        ArrayList<Position> square = new ArrayList<>(squarePositions);
        ArrayList<Position> zigZag = new ArrayList<>(zigzagPositions);
        ArrayList<Position> zero = new ArrayList<>(squarePositions);
        zero.addAll(zigzagPositions);
        Collections.sort(zero);
        ArrayList<Position> xFirst = new ArrayList<>(zero);
        xFirst.sort(new XAxisComparator());
        ArrayList<Position> yFirst = new ArrayList<>(zero);
        ArrayList<Position> centered = new ArrayList<>(zero);
        centered.sort(Comparator.comparingDouble(a -> a.distance(new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d))));
        yFirst.sort(yAxisComparator);
        List<Position> randomPattern1 = List.of(new Position(random.nextInt(GameView.WIDTH), random.nextInt(GameView.HEIGHT)), new Position(random.nextInt(GameView.WIDTH), random.nextInt(GameView.HEIGHT)), new Position(random.nextInt(GameView.WIDTH), random.nextInt(GameView.HEIGHT)), new Position(random.nextInt(GameView.WIDTH), random.nextInt(GameView.HEIGHT)));
        ArrayList<Position> randomPattern = new ArrayList<>(randomPattern1);
        movePattern.put("random", randomPattern);
        movePattern.put("square", square);
        movePattern.put("zigzag", zigZag);
        movePattern.put("zero", zero);
        movePattern.put("xfirst", xFirst);
        movePattern.put("yfirst", yFirst);
        movePattern.put("centered", centered);


    }

    /**
     * @param pattern name of pattern
     * @return a selected set of positions
     */
    public ArrayList<Position> getPattern(String pattern) {
        return movePattern.get(pattern);

    }

    /**
     * @return a random set of Positions to move to
     */
    public ArrayList<Position> getRandomPattern() {
        return movePattern.get("random");

    }
}
