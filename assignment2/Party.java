import java.util.ArrayList;
import java.util.HashMap;

public class Party
{
	private String partyName;
    private String partyLeader;
    private String partySymbol;
    private String campaignSlogan;
    private HashMap<String, ArrayList<Candidate>> candidates;

    public Party(String partyName, String partyLeader, String partySymbol, String campaignSlogan) {
        this.partyName = partyName;
        this.partyLeader = partyLeader;
        this.partySymbol = partySymbol;
        this.campaignSlogan = campaignSlogan;
		this.candidates = new HashMap<>();
    }

	public void addCandidate(Candidate c)
	{
		// NRIC check first
		ArrayList<Candidate> allCandidates = getAllCandidates();
		for (Candidate cand : allCandidates)
		{
			if (cand.getNric().equals(c.getNric()))
			{
				return ; // equal NRIC, same person could be running for multiple consts.
			}
		}

		// Constituency check
		String constituency = c.getConstituency();
		ArrayList<Candidate> sameConstCandidates = getCandidates().get(constituency);
		if (sameConstCandidates != null)
		{
			int grcCount = 0;
			for (Candidate cand : sameConstCandidates)
			{
				if (cand.getConstituencyType().equals("SMC"))
					return ; // the constitution is already claimed by SMC
				else if (cand.getConstituencyType().equals("GRC"))
					grcCount += 1;
			}
			if (grcCount < Candidate.getMaxGRCCandidates() && c.getConstituencyType().equals("GRC"))
			{
				// the arraylist already exists here
				candidates.get(constituency).add(c);
			}
		}
		else
		{
			if (!candidates.containsKey(constituency))
        		candidates.put(constituency, new ArrayList<>());
			candidates.get(constituency).add(c);
		}
    }

	public Candidate getCandidateByNric(String nric)
	{
        for (ArrayList<Candidate> candidateList : candidates.values()) {
            for (Candidate c : candidateList) {
                if (c.getNric().equals(nric)) {
                    return c;
                }
            }
        }
        return null;
    }

	public String listCandidates()
	{
        StringBuilder sb = new StringBuilder();
        for (String constituency : candidates.keySet()) {
            sb.append("Constituency: ").append(constituency).append("\n");
            for (Candidate c : candidates.get(constituency)) {
                sb.append("").append(c.toString()).append("\n");
            }
        }
        System.out.print(sb.toString());
        return sb.toString();
    }

	public HashMap<String, ArrayList<Candidate>> getCandidates()
	{
        return candidates;
    }

	public ArrayList<Candidate> getAllCandidates()
	{
        ArrayList<Candidate> allCandidates = new ArrayList<>();
        for (ArrayList<Candidate> candidateList : candidates.values()) {
            allCandidates.addAll(candidateList);
        }
        return allCandidates;
    }

	public String getPartyName()
	{
		return partyName;
	}

}