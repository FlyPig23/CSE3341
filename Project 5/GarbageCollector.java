import java.util.ArrayList;
import java.util.List;

public class GarbageCollector {
    static List<Integer> garbageCollector;

    static void initialize() {
        garbageCollector = new ArrayList<Integer>();
    }

    static void allocateToGarbageCollector() {
        garbageCollector.add(1);
    }

    static void addToGarbageCollector(String id) {
        CoreVar currentReference = Executor.searchCorrespondingCoreVar(id);
        if (currentReference.value != null) {
            int currentTime = garbageCollector.get(currentReference.value);
            currentTime++;
            garbageCollector.set(Executor.searchCorrespondingCoreVar(id).value, currentTime);
        }
    }

    static void subFromGarbageCollector(String id) {
        CoreVar currentReference = Executor.searchCorrespondingCoreVar(id);
        if (currentReference.value != null) {
            int currentTime = garbageCollector.get(currentReference.value);
            currentTime--;
            garbageCollector.set(currentReference.value, currentTime);
        }
    }

    static void checkReachZero(String id) {
        CoreVar currentReference = Executor.searchCorrespondingCoreVar(id);
        if (currentReference.value != null) {
            int currentTime = garbageCollector.get(currentReference.value);
            if (currentTime == 0) {
                System.out.println("gc:" + countTotalReference());
            }
        }
    }

    static int countTotalReference() {
        int sum = 0;
        for (int time : garbageCollector) {
            if (time != 0) {
                sum++;
            }
        }
        return sum;
    }
}
