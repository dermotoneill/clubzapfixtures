package com.stfinians;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {
	public static void main(String[] args) {
		final Arguments arguments = new Arguments(args);
		final Fixtures fixtures = new Fixtures();
		try {
			final File dcbExcelFile = arguments.getFile();
			final FileInputStream file = new FileInputStream(dcbExcelFile);
			try (final Workbook workbook = new XSSFWorkbook(file)) {
				Sheet sheet = workbook.getSheetAt(arguments.getSheetIndex());
				final int rowEnd = sheet.getLastRowNum();
				for (int rowNum = 0; rowNum <= rowEnd; rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (rowNum >= arguments.getRowNum() - 1) {
						if(row!=null && !row.toString().trim().equals("")) {
						Fixture fixture = new Fixture(row);
						fixtures.addFixture(fixture);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fixtures.getClubZapFixturesCSVTemplate());
	}

	private static final String EXAMPLE_COMMAND = "java -jar clubzapfixtures \"Fixtures - Week 30.08 - 07.09.xlsx\" [sheetindex [startDate] ]";

	public static class Arguments {
		final String[] args;

		public Arguments(String[] args) {
			this.args = args;
			if (args.length < 1 || args.length > 3) {
				throw new IllegalArgumentException("Invalid arguments \n" + EXAMPLE_COMMAND);
			}
		}

		public File getFile() {
			return new File(args[0]);
		}

		public int getSheetIndex() {

			return args.length == 1 ? 0 : Integer.parseInt(args[1]);
		}

		public int getRowNum() {
			return args.length == 3 ? Integer.parseInt(args[2]) : 2;
		}

	}
}
