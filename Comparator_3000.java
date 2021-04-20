import java.util.Comparator;
import java.util.Comparator;

class Comparator_3000 implements Comparator<HumanBeing> {
    public int compare(HumanBeing s1, HumanBeing s2) {
        if (s1.getId() > s2.getId())
            return 1;
        else if (s1.getId() < s2.getId())
            return -1;
        else return 0;
    }
}