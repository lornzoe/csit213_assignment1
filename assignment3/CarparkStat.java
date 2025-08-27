import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CarparkStat {
    private ArrayList<CarparkUsage> usageData;
    private ArrayList<String> error;

    public CarparkStat() {
        usageData = new ArrayList<>();
        error = new ArrayList<>();
    }

    public ArrayList<String> load(String filePath) {
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
                    if (!usageData.contains(usage)) {
                        usageData.add(usage);
                    }
                } catch (CarparkDataException e) {
                    error.add("Invalid record: " + line + " (" + e.getMessage() + ")");
                } catch (NumberFormatException e) {
                    error.add("Invalid record: " + line + " (" + e.getMessage() + ")", e);
                }
            }
        } catch (IOException e) {
            error.add("File read error: " + e.getMessage());
        }
        return error;
    }

    public int getSize() {
        return usageData.count();
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
