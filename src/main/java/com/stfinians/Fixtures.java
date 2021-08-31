package com.stfinians;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

public class Fixtures {
	List<Fixture> fixtures =new ArrayList<Fixture>();

	public void addRow(Row row) throws ParseException {
		fixtures.add(new Fixture(row));
	}
	
	public String getClubZapFixturesCSVTemplate() {
		StringBuilder fixturesCSV = new StringBuilder();
		fixturesCSV.append("Date,Time,Venue,Referee,Team,Competition Name,Your Club Name,Opponent,Event Type\n");
		for(Fixture fixture:fixtures) {
			fixturesCSV.append(fixture.getClubZapCSVTemplateRow());
			fixturesCSV.append("\n");
		}
		return fixturesCSV.toString();
	}

	public void addFixture(Fixture fixture) {
	  fixtures.add(fixture);
	}

}
