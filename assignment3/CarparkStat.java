import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CarparkStat {
    private ArrayList<CarparkUsage> usageData;
    private ArrayList<String> error;

    public CarparkStat() {
        usageData = new ArrayList<>();
        error = new ArrayList<>();
    }

    public ArrayList<String> load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    error.add("Invalid record (wrong number of fields): " + line);
                    continue;
                }
                try {
                    CarparkUsage usage = new CarparkUsage(
                        parts[1].trim(), // carparkName
                        parts[2].trim(), // zone
                        parts[0].trim(), // date
                        Double.parseDouble(parts[3].trim()) // occupiedRate
                    );
                    // valid record so far
                    if (!usageData.contains(usage)) { // contain method uses equal, we're hoping to catch dupe with this
                        usageData.add(usage);
                    }
                    else {
                        error.add("Invalid record (duplicate entry): " + usage.toString());
                    }
                } catch (CarparkDataException | NumberFormatException e) {
                    error.add("Invalid record: " + line + " (" + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            error.add("File read error: " + e.getMessage());
        }
        return error;
    }

    public int size() {
        return usageData.size();
    }

    public HashMap<String, Double> process(Analyser analyser) {
        return analyser.analyse(usageData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CarparkUsage usage : usageData) {
            sb.append(usage.toString()).append("\n");
        }
        return sb.toString();
    }
}
