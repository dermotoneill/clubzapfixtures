package com.stfinians;



import java.text.ParseException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;

public class Fixture {

	final Date dcbDate;
	final String date;
	final String time;
	final String venue;
	final String referee;
	final String team;
	final String competitionName;
	final String yourClubName;
	final String opponent;
	final String eventType;
	final String code;
	
	public Fixture(Row row) throws ParseException {
		DCBFixture dcbFixture = new DCBFixture(row);
		this.dcbDate = dcbFixture.getDate();
		this.date = dcbFixture.getClubZapDate();
		this.time = dcbFixture.getClubZapTime();
		this.venue = dcbFixture.getClubZapVenue();
		this.referee = dcbFixture.getClubZapReferee();
		this.team = dcbFixture.getClubZapTeamName();
		this.competitionName = dcbFixture.getClubZapCompetitionName();
		this.yourClubName = dcbFixture.getClubZapYourClubName();
		this.opponent = dcbFixture.getClubZapOpponent();
		this.eventType = dcbFixture.getClubZapEventType();
		this.code = dcbFixture.getCode();
	}
	
	public String getClubZapCSVTemplateRow() {
		return date +","+time+","+venue+","+referee+","+team+","+competitionName+" - " +code+","+yourClubName+","+opponent+","+eventType;
	}

	public boolean isAfter(Date startDate) {
		return this.dcbDate.after(startDate);
	}
	
}
