package sortingClean;

import java.util.Random;

import jakarta.inject.Inject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
// TODO: Add dependency injection and annotations to this file

public class AlgorithmRunner {
    SortingAlgorithm<Integer> quadraticAlgorithm;
    SortingAlgorithm<Integer> nlognAlgorithm;
    SortingAlgorithm<Integer> randomAlgorithm1;
    SortingAlgorithm<Integer> randomAlgorithm2;
    int numberOfElements;

    @Inject
    public AlgorithmRunner(@Qualifiers.Quadratic SortingAlgorithm<Integer> quadraticAlgorithm,
                           @Qualifiers.NlogN SortingAlgorithm<Integer> nlognAlgorithm,
                           @Qualifiers.Random1 SortingAlgorithm<Integer> randomAlgorithm1,
                           @Qualifiers.Random2 SortingAlgorithm<Integer> randomAlgorithm2,
                           int numberOfElements) {
        this.quadraticAlgorithm = quadraticAlgorithm;
        this.nlognAlgorithm = nlognAlgorithm;
        this.randomAlgorithm1 = randomAlgorithm1;
        this.randomAlgorithm2 = randomAlgorithm2;
        this.numberOfElements = numberOfElements;
    }

    public void runAlgorithms() {
        Random rand = new Random();
        Integer[] ints = rand.ints(1, Integer.MAX_VALUE)
                .distinct()
                .limit(numberOfElements)
                .boxed()
                .toArray(Integer[]::new);
        Integer[] intsClone = ints.clone();
        quadraticAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        nlognAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm1.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm2.sort(intsClone);
    }


}
