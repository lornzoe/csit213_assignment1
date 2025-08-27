import java.util.HashMap;
import java.util.List;

public class ZoneAnalyser implements Analyser {
    @Override
    public HashMap<String, Double> analyse(List<CarparkUsage> data) {
        HashMap<String, Double> totals = new HashMap<>();
        HashMap<String, Double> counts = new HashMap<>();
        for (CarparkUsage usage : data) {
            String zone = usage.getZone();
            totals.put(zone, totals.getOrDefault(zone, 0.0) + usage.getOccupiedRate());
            counts.put(zone, counts.getOrDefault(zone, 0.0) + 1.0);
        }

        HashMap<String, Double> averages = new HashMap<>();
        for (String zone : totals.keySet()) {
            averages.put(zone, totals.get(zone) / counts.get(zone));
        }
        return averages;
    }
}
