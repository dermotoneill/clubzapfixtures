package com.stfinians;

public class ClubZapTeam {

	final private String teamName;
	final private String[] codes;

	final private String clubName;
	final private String[] tags;

	public ClubZapTeam(final String teamName, final String[] codes, final String clubName, final String[] tags) {
		this.teamName = teamName;
		this.codes = codes;
		this.clubName = clubName;
		this.tags = tags;
	}

	public String getTeamName() {
		return teamName;
	}

	public String[] getCodes() {
		return codes;
	}

	public String getClubName() {
		return clubName;
	}

	public String[] getTags() {
		return tags;
	}
}
