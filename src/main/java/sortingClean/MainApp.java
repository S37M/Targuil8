package sortingClean;

import jakarta.enterprise.inject.Produces;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Random;

// TODO: Add java classes (in separate files for annotations and aspects)
public class MainApp {
    public static WeldContainer container = new Weld().initialize();
    public static void main(String[] args) {


        AlgorithmRunner algorithmRunner = container.select(AlgorithmRunner.class).get();

        algorithmRunner.runAlgorithms();

        //.shutdown();
    }

    private static SortingAlgorithm<Integer> makeRandomSortingAlgorithm(){
        Random random = new Random(System.currentTimeMillis());
        SortingAlgorithm<Integer> sortAlg= null;
        switch (random.nextInt(4)){
            case 0: sortAlg =container.select(QuickSort.class).get();
                break;
            case 1: sortAlg = container.select(BubbleSort.class).get();
                break;
            case 2: sortAlg = container.select(MergeSort.class).get();
                break;
            case 3: sortAlg = container.select(InsertionSort.class).get();
        }
        return sortAlg;
    }
    @Produces
    @Qualifiers.Quadratic
    public SortingAlgorithm<Integer> produceQuadraticAlgorithm(){
        return container.select(QuickSort.class).get();

    }
    @Produces
    @Qualifiers.NlogN
    public SortingAlgorithm<Integer> producenlognAlgorithm(){
        return container.select(BubbleSort.class).get();

    }
    @Produces
    @Qualifiers.Random1
    public SortingAlgorithm<Integer> producerandomAlgorithm1(){
        return makeRandomSortingAlgorithm();

    }
    @Produces
    @Qualifiers.Random2
    public SortingAlgorithm<Integer> producerandomAlgorithm2(){
        return makeRandomSortingAlgorithm();

    }

    @Produces
    public Integer produceNumberOfElements() {
        return 1000;
    }

}
