package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author LiYu
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    private static final double moveLoseEnergy = 0.03;

    private static final double stayLoseEnergy = 0.01;
    /**
     * fraction of energy to retain when replicating.
     */
    private double repEnergyRetained = 0.5;
    /**
     * fraction of energy to bestow upon offspring.
     */
    private double repEnergyGiven = 0.5;
    /**
     * probability of taking a move when ample space available.
     */
    private double moveProbability = 0.5;


    /**
     * creates clorus with energy equal to E.
     * The color() method for Cloruses should always return the color red = 34, green = 0, blue = 231
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     *
     */
    public Color color() {

        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature’s energy.
     * You do not need to worry about making sure the attacked creature dies—the simulator
     * does that for you.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Cloruses should lose 0.03 units of energy on a MOVE action.
     */
    public void move() {
        energy -= moveLoseEnergy;
        if (energy < 0) {
            energy = 0;
        }
    }


    /**
     * Cloruses should lose 0.01 units of energy on a STAY action.
     * Cloruses have no restrictions on their maximum energy.
     */
    public void stay() {
        energy -= stayLoseEnergy;
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {

        double babyEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;

        return new Clorus(babyEnergy);
    }

    /**
     * Cloruses should obey exactly the following behavioral rules:
     * 1. If there are no empty squares, the Clorus will STAY (even if there are Plips
     * nearby they could attack since plip squares do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.addLast(entry.getKey());
            }
            if (entry.getValue().name().equals("plip")){
                plipNeighbors.addLast(entry.getKey());
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (!plipNeighbors.isEmpty()){
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipNeighbors));
        }

        // Rule 3
        if (energy >= 1){
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));

    }
}
