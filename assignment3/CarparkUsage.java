import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CarparkUsage {
    private String carparkName;
    private String zone;
    private LocalDate usageDate;
    private double occupiedRate;

    public CarparkUsage(String carparkName, String zone, String usageDate, double occupiedRate) throws CarparkDataException {
        try {
            this.usageDate = LocalDate.parse(usageDate);
        }
        catch (DateTimeParseException e)
        {
            throw new CarparkDataException("Invalid usageDate: " + usageDate, e);
        }
        if (occupiedRate < 0 || occupiedRate > 100)
            throw new CarparkDataException("Invalid occupiedRate: " + occupiedRate);
        this.carparkName = carparkName;
        this.zone = zone;
        this.occupiedRate = occupiedRate;
    }

    public String getName() {
        return carparkName;
    }

    public String getZone() {
        return zone;
    }

    public int getYear() {
        return usageDate.getYear();
    }

    public int getMonth() {
        return usageDate.getMonthValue();
    }

    public double getOccupiedRate() {
        return occupiedRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == obj)
            return true;
        if (!(o instanceof CarparkUsage))
            return false;
        CarparkUsage obj = (CarparkUsage) o
        return (this.carkparkName.equals(obj.carkparkName) && this.usageDate.equals(obj.usageDate));
    }

    @Override
    public String toString() {
        return usageDate + ", " + carparkName + ", " + zone + ", " + occupiedRate;
    }
}
