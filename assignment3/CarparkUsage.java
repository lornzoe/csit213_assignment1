import java.time.LocalDate;

public class CarparkUsage {
    private String carparkName;
    private String zone;
    private LocalDate usageDate;
    private double occupiedRate;

    public CarparkUsage(String carparkName, String zone, String usageDate, double occupiedRate) throws CarparkDataException {
    }

    public String getName() {
        return null;
    }

    public String getZone() {
        return null;
    }

    public int getYear() {
        return 0;
    }

    public int getMonth() {
        return 0;
    }

    public double getOccupiedRate() {
        return 0.0;
    }

    public LocalDate getUsageDate() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
