package com.stfinians;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

public class DCBFixture {

	private final Row row;
	private static final Map<String, Integer> columnIndex = new HashMap<String, Integer>();
	private static final int columnIndexStart = 0;
	static {

		columnIndex.put("code", columnIndexStart + 1);
		columnIndex.put("competition", columnIndexStart + 2);
		columnIndex.put("date", columnIndexStart + 3);
		columnIndex.put("time", columnIndexStart + 4);
		columnIndex.put("day", columnIndexStart + 5);
		columnIndex.put("hometeam", columnIndexStart + 6);
		columnIndex.put("awayteam", columnIndexStart + 7);
		columnIndex.put("venue", columnIndexStart + 8);
	}

	public DCBFixture(Row row) {
		this.row = row;
	}

	public String getClubZapDate() throws ParseException {
		final Date date = getDate();
		return new SimpleDateFormat("dd.MM.yyyy").format(date);
	}

	public String getClubZapTime() {
		Date date = row.getCell(columnIndex.get("time")).getDateCellValue();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}

	public String getClubZapVenue() {
		return row.getCell(columnIndex.get("venue")).getStringCellValue();
	}

	public String getClubZapReferee() {
		return "";
	}

	
	public String getClubZapTeamName() {
		return getClubZapTeam().getTeamName();
	}
	public ClubZapTeam getClubZapTeam() {
		String fixtureCode = getCode();
		String clubZapCompetitionName = getClubZapCompetitionName();
		for(ClubZapTeam team:ClubZapTeams) {
			for(String teamCode:team.getCodes()) {
				if(teamCode.equalsIgnoreCase(fixtureCode)) {
					for(String tag:team.getTags()) {
						if(clubZapCompetitionName.indexOf(tag)!=-1) {
							return team;
						}
					}
				}
			}
			
		}
		return null;
	}

	public String getCode() {
		return row.getCell(columnIndex.get("code")).getStringCellValue();
	}

	public String getClubZapCompetitionName() {
		return row.getCell(columnIndex.get("competition")).getStringCellValue() ;
	}

	public String getClubZapYourClubName() {
		return getClubZapTeam().getClubName();
	}

	public String getClubZapOpponent() {
	     String awayTeam= row.getCell(columnIndex.get("awayteam")).getStringCellValue();
	     String homeTeam= row.getCell(columnIndex.get("hometeam")).getStringCellValue();
	     if(awayTeam.trim().toLowerCase().startsWith("st finians") ||awayTeam.trim().toLowerCase().startsWith("thomas ashe") ||awayTeam.trim().toLowerCase().startsWith("fingal gaels") ) {
	    	 return homeTeam;
	     } else {
	    	 return awayTeam;
	     }
	}

	public String getClubZapEventType() {
		String clubZapCompetitionName = getClubZapCompetitionName();
		if(clubZapCompetitionName.toLowerCase().indexOf("league") != -1) {
			return "League";
		} else if(clubZapCompetitionName.toLowerCase().indexOf("championship") != -1) {
			return "Championship";
		} else if(clubZapCompetitionName.toLowerCase().indexOf("friendly") != -1) {
			return "Friendly";
		} else if(clubZapCompetitionName.toLowerCase().indexOf("champion") != -1) {
			return "Championship";
		} else {
			return "League";
		}
	}

	public Date getDate() throws ParseException {
		String dateValue = row.getCell(columnIndex.get("date")).getStringCellValue();
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		Date date = format.parse(dateValue);
		return date;
	}

	List<ClubZapTeam> ClubZapTeams = Arrays.asList(
			new ClubZapTeam("2005 Boys-U16 Football-Thomas Ashe", new String[] { "Football" }, "Thomas Ashe",new String[] { "U16" }),
			new ClubZapTeam("2005/2006 Boys-U16 Hurling", new String[] { "Hurling" }, "St Finians", new String[] { "U16" }),
			new ClubZapTeam("2005/2006 Girls-U16", new String[] { "LGFA","Camogie" }, "St Finians", new String[] { "U16" }),
			new ClubZapTeam("2006 Boys-U15 Football-Thomas Ashe", new String[] { "Football" }, "Thomas Ashe",new String[] { "U15" }),
			new ClubZapTeam("2006 Boys-U15 Hurling", new String[] { "Hurling" }, "St Finians", new String[] { "U15" }),
			new ClubZapTeam("2007 Boys-U14 Hurling", new String[] { "Hurling" }, "St Finians", new String[] { "U14" }),
			new ClubZapTeam("2007 Girls-U14", new String[] { "LGFA","Camogie" }, "St Finians", new String[] { "U14" }),
			new ClubZapTeam("2008 Boys-U13 Football-Thomas Ashe", new String[] { "Football" }, "Thomas Ashe",new String[] { "U13" }),
			new ClubZapTeam("2008 Boys-U13 Hurling", new String[] { "Hurling" }, "St Finians", new String[] { "U13" }),
			new ClubZapTeam("2008 Girls-U13", new String[] { "LGFA","Camogie" }, "St Finians", new String[] { "U13" }),
			new ClubZapTeam("2009 Boys-U12",new String[] { "Football","Hurling" }, "St Finians", new String[] { "U12" }),
			new ClubZapTeam("2009 Girls-U12", new String[] { "LGFA","Camogie" }, "St Finians", new String[] { "U12" }),
			new ClubZapTeam("2010 Boys-U11", new String[] { "Football","Hurling" }, "St Finians", new String[] { "U11" }),
			new ClubZapTeam("2010 Girls-U11",new String[] {"LGFA","Camogie"  }, "St Finians", new String[] { "U11" }),
			new ClubZapTeam("2011 Boys-U10", new String[] { "Football","Hurling"  }, "St Finians", new String[] { "U10" }),
			new ClubZapTeam("2011 Girls-U10", new String[] { "LGFA","Camogie"  }, "St Finians", new String[] { "U10" }),
			new ClubZapTeam("2012 Boys-U9",new String[] { "Football","Hurling"  }, "St Finians", new String[] { "U9" }),
			new ClubZapTeam("2012 Girls-U9", new String[] { "LGFA","Camogie" }, "St Finians", new String[] { "U9" }),
			new ClubZapTeam("2013 Boys-U8", new String[] { "Football","Hurling"  }, "St Finians", new String[] { "U8" }),
			new ClubZapTeam("2013 Girls-U8",new String[] {"LGFA","Camogie"  }, "St Finians", new String[] { "U8" }),
			new ClubZapTeam("2014 Boys-U7",new String[] { "Football","Hurling"  }, "St Finians", new String[] { "U7" }),
			new ClubZapTeam("2014 Girls-U7", new String[] {"LGFA","Camogie"  }, "St Finians", new String[] { "U7" }),
			new ClubZapTeam("Adult Camogie",new String[] {"Camogie" }, "St Finians", new String[] { "Junior","Intermediate","Senior","Adult" }),
			new ClubZapTeam("Adult Football 1 (AFL 5)", new String[] { "Football" }, "St Finians", new String[] { "Junior","Intermediate","Senior","Adult"}),
			new ClubZapTeam("Adult Football 2 (AFL 8)", new String[] { "Football" }, "St Finians", new String[] { "Junior","Intermediate","Senior","Adult"}),
			new ClubZapTeam("Adult GFM&O", new String[] { "GFM&O)" }, "St Finians", new String[] { "" }),
			new ClubZapTeam("Adult Hurling 1 (AHL 5)",new String[] { "Hurling" }, "St Finians", new String[] { "Junior","Intermediate","Senior","Adult" }),
			new ClubZapTeam("Adult Hurling 2 (AHL 9)", new String[] { "Hurling" }, "St Finians", new String[] { "Junior","Intermediate","Senior","Adult" }),
			new ClubZapTeam("Adult LGFA",new String[] { "LGFA" }, "St Finians", new String[] { "Junior","Intermediate","Senior","Adult" }),
			new ClubZapTeam("Mixed Adult Rounders", new String[] { "Rounders" }, "St Finians", new String[] { "Junior","Intermediate","Senior","Adult" }),
			new ClubZapTeam("U18 Minor Football-Thomas Ashe",new String[] { "Football" }, "Thomas Ashe", new String[] { "U18","Minor" }),
			new ClubZapTeam("U18 Minor Hurling-Fingal Gaels",new String[] { "Hurling" }, "Fingal Gaels", new String[] { "U18","Minor" }));
}
