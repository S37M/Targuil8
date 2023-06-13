package sortingClean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.*;

@Aspect
public class TimingAspect {
    private Map<String, SortExecution> sortExecutionMap = new HashMap<>();
    private long startTime;

    @Pointcut("execution(* sortingClean.SortingAlgorithm.sort(..)) && target(sortingAlgorithm)")
    public void sortingAlgorithmMethods(SortingAlgorithm sortingAlgorithm) { }

    @Pointcut("execution(* sortingClean.AlgorithmRunner.runAlgorithms(..))")
    public void runAlgorithmsMethod() { }

    @Before("runAlgorithmsMethod()")
    public void beforeRunAlgorithmsMethod() {
        startTime = System.currentTimeMillis();
    }

    @Before("sortingAlgorithmMethods(sortingAlgorithm)")
    public void beforeSortingAlgorithmMethods(JoinPoint joinPoint, SortingAlgorithm sortingAlgorithm) {
        String className = sortingAlgorithm.getClass().getSimpleName();
        sortExecutionMap.computeIfAbsent(className, k -> new SortExecution()).setStartTime(System.currentTimeMillis());
    }

    @After("sortingAlgorithmMethods(sortingAlgorithm)")
    public void afterSortingAlgorithmMethods(JoinPoint joinPoint, SortingAlgorithm sortingAlgorithm) {
        String className = sortingAlgorithm.getClass().getSimpleName();
        sortExecutionMap.get(className).endExecution();
    }

    @After("runAlgorithmsMethod()")
    public void afterRunAlgorithmsMethod() {
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Total time of running all sort functions was " + totalTime + " ms");

        sortExecutionMap.forEach((k, v) -> {
            System.out.println("In detail:");
            System.out.println("Function sort in " + k + " ran " + v.getExecutionCount() + " times and took in total " + v.getExecutionTime() + " ms");
        });
    }
}
