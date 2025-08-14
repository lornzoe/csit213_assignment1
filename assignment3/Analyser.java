import java.util.HashMap;
import java.util.List;

public interface Analyser {
    HashMap<String, Double> analyse(List<CarparkUsage> data);
}
