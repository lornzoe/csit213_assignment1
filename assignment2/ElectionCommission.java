import java.util.ArrayList;
import java.util.HashMap;

public class ElectionCommission
{
	private ArrayList<Party> parties;

	public ElectionCommission()
	{
		parties = new ArrayList<>();
	}

	public void addParty(Party p) {
        boolean partyExists = false;
        for (Party existingParty : parties) {
            if (existingParty.getPartyName().equals(p.getPartyName()))
			{
                partyExists = true;
                break;
            }
        }
        if (!partyExists) {
            parties.add(p);
        }
    }
	public void addCandidateToParty(String partyName, Candidate c)
	{
		Party targetparty = null;
        for (Party p : parties)
		{
            if (p.getPartyName().equals(partyName))
			{
				targetparty = p;
			}
        }
		if (targetparty != null)
		{
			ArrayList<Candidate> candidates = targetparty.getCandidates().get(c.getConstituency());
			if (candidates != null)
			{
				int grcCount = 0;
				for (Candidate cand : candidates)
				{
					if (cand.getConstituencyType().equals("SMC"))
						return ; // the constitution is already claimed by SMC
					else if (cand.getConstituencyType().equals("GRC"))
						grcCount += 1;
				}
				if (grcCount < Candidate.getMaxGRCCandidates() && c.getConstituencyType().equals("GRC"))
				{
					targetparty.addCandidate(c);
				}
			}
			else
			{
				targetparty.addCandidate(c);
			}
		}
    }

	public ArrayList<Party> getParties()
	{
		return parties;
	}

	public ArrayList<String> getCandidatesNameByParty(String partyName)
	{
        ArrayList<String> candidateNames = new ArrayList<>();
        for (Party p : parties)
		{
            if (p.getPartyName().equals(partyName)) {
                for (Candidate c : p.getAllCandidates()) {
                    candidateNames.add(c.getName());
                }
                break;
            }
        }
        return candidateNames;
    }

    public ArrayList<String> getCandidatesNameByConstituency(String constituency)
	{
        ArrayList<String> candidateNames = new ArrayList<>();
        for (Party p : parties)
		{
            HashMap<String, ArrayList<Candidate>> partyCandidates = p.getCandidates();
            if (partyCandidates.containsKey(constituency)) {
                for (Candidate c : partyCandidates.get(constituency)) {
                    candidateNames.add(c.getName());
                }
            }
        }
        return candidateNames;
    }

	public ArrayList<String> getAllCandidatesName()
	{
        ArrayList<String> allCandidatesNames = new ArrayList<>();
        for (Party p : parties)
		{
            for (Candidate c : p.getAllCandidates()) {
                allCandidatesNames.add(c.getName());
            }
        }
        return allCandidatesNames;
    }
}