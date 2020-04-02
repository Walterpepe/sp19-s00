package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
//import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private int numStatesExplored;
    private double timeSpent;

    private Vertex goal;
    private Map<Vertex, Vertex> edgeTo;  // edgeTo[v] is the best known predecessor of v.
    private Map<Vertex, Double> distTo;  // distTo[v] is the best known total distance from source to v.
    private ExtrinsicMinPQ<Vertex> fringe; //

    /**
     * Constructor which finds the solution,
     * computing everything necessary for all other methods to return their results in constant time.
     *
     * @param input   ""
     * @param start   ""
     * @param end     ""
     * @param timeout timeout passed in is in seconds.
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();

        goal = end;
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();

        fringe = new ArrayHeapMinPQ<>();
        fringe.add(start, input.estimatedDistanceToGoal(start, goal));
        distTo.put(start, 0.0);
        while (true) {
            if (fringe.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
                break;
            }

            if (sw.elapsedTime() >= timeout) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            }

            if (fringe.getSmallest() == goal) {
                outcome = SolverOutcome.SOLVED;
                break;
            }

//            System.out.printf("remove %s\n", fringe.getSmallest());
            List<WeightedEdge<Vertex>> neighbors = input.neighbors(fringe.removeSmallest());
            for (WeightedEdge<Vertex> e : neighbors) {
                relax(e, input.estimatedDistanceToGoal(e.to(), goal));
            }

            numStatesExplored++;
        }

        timeSpent = sw.elapsedTime();
    }

    /**
     * @param e ""
     */
    private void relax(WeightedEdge<Vertex> e, double heuristic) {
        Vertex p = e.from();
        Vertex q = e.to();
        Double w = e.weight();

        if (distTo.get(q) == null || distTo.get(p) + w < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);
            if (fringe.contains(q)) {
//                System.out.printf("change (%s,%.2f)\n", q, distTo.get(q) + heuristic);
                fringe.changePriority(q, distTo.get(q) + heuristic);
            } else {
//                System.out.printf("add (%s,%.2f)\n", q, distTo.get(q) + heuristic);
                fringe.add(q, distTo.get(q) + heuristic);
            }
            edgeTo.put(q, p);
        }
    }

    /**
     * You should check to see if you have run out of time every time you dequeue.
     *
     * @return one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty.
     * TIMEOUT if the solver ran out of time.
     */
    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    /**
     * @return A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE.
     */
    @Override
    public List<Vertex> solution() {
        LinkedList<Vertex> sol = new LinkedList<>();

        if (outcome == SolverOutcome.TIMEOUT || outcome == SolverOutcome.UNSOLVABLE) {
            return sol;
        }

        sol.addFirst(goal);
        Vertex tmp = goal;
        while (edgeTo.get(tmp) != null) {
            tmp = edgeTo.get(tmp);
            sol.addFirst(tmp);
        }
        return sol;
    }

    /**
     * @return The total weight of the given solution. taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     */
    @Override
    public double solutionWeight() {
        if (outcome == SolverOutcome.TIMEOUT || outcome == SolverOutcome.UNSOLVABLE) {
            return 0;
        }
        return distTo.get(goal);
    }

    /**
     * @return The total number of priority queue dequeue operations
     */
    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    /**
     * @return The total time spent in seconds by the constructor.
     */
    public double explorationTime() {
        return timeSpent;
    }

}