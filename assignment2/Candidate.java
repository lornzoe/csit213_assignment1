public class Candidate extends Person
{
    private String partyName;
    private String constituency;
    private String constituencyType;
    private static int MAX_GRC_CANDIDATES = 3;
	
	public Candidate(String name, String nric, String gender, String dateOfBirth, String partyName, String constituency, String constituencyType)
	{
		super(name, nric, gender, dateOfBirth);
		this.partyName = partyName;
		this.constituency = constituency;
		this.constituencyType = constituencyType;
	}

	public String getPartyName()
	{
		return partyName;
	}

	public String getConstituency()
	{
		return constituency;
	}

	public String getConstituencyType()
	{
		return constituencyType;
	}

    public static int getMaxGRCCandidates()
	{
        return MAX_GRC_CANDIDATES;
    }

	public static void setMaxGRCCandidates(int n)
	{
		if (n > 0)
        	MAX_GRC_CANDIDATES = n;
    }

	@Override
	public String toString()
	{
		return getName() + " (" + this.partyName + ", " + this.constituency + " [" + constituencyType +"])";
	}
}