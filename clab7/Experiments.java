import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {

        int N = 5000;
        int L = 5;

        BST<String> mapBST = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        for (int x = 1; x <= N; x++) {
            mapBST.add(StringUtils.randomString(L));
            xValues.add(x);
            yValues.add(mapBST.averageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(x));
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("actual", xValues, yValues);
        chart.addSeries("optimal", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment(int num) {

        int N = 5000;
        int L = 5;
        int ACTION = 1000;

        BST<String> mapBST = new BST<>();

        for (int i = 0; i < N; i++) {
            mapBST.add(StringUtils.randomString(L));
        }

        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        double optimalAverageDepth = ExperimentHelper.optimalAverageDepth(N);

        for (int x = 1; x <= ACTION; x++) {
            if (num == 2) {
                ExperimentHelper.asymmetric(mapBST);
            } else if (num == 3) {
                ExperimentHelper.symmetric(mapBST);
            }

            xValues.add(x);
            yValues.add(mapBST.averageDepth());
            y2Values.add(optimalAverageDepth);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("actual", xValues, yValues);
        chart.addSeries("optimal", xValues, y2Values);

        new SwingWrapper(chart).displayChart();

    }

    public static void main(String[] args) {
//        experiment1();
//        experiment(2);
        experiment(3);
    }


}
