package com.onpassive.admin.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.onpassive.admin.domain.Book;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "NO", "Title", "Author", "Publisher", "publication Date", "Language", "Category",
			"Number of Pages", "format", "Isbn", "Shipping weight", "List Price", "Our Price" };
	static String SHEET = "Books";

	public static ByteArrayInputStream booksToExcel(List<Book> books) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			Font font = workbook.createFont();
			font.setColor(IndexedColors.YELLOW.getIndex());
			style.setFont(font);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(style);

				
			}

			int rowIdx = 1;
			
			for (Book book : books) {
				Row row = sheet.createRow(rowIdx++);
				/*
				 * CellStyle headerCellStyle = workbook.createCellStyle();
				 * headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
				 * headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				 */

				row.createCell(0).setCellValue(book.getId());
				row.createCell(1).setCellValue(book.getTitle());
				row.createCell(2).setCellValue(book.getAuthor());
				row.createCell(3).setCellValue(book.getPublisher());
				row.createCell(4).setCellValue(book.getPublicationDate());
				row.createCell(5).setCellValue(book.getLanguage());
				row.createCell(6).setCellValue(book.getCategory());
				row.createCell(7).setCellValue(book.getNumberOfPages());
				row.createCell(8).setCellValue(book.getFormat());
				row.createCell(9).setCellValue(book.getIsbn());
				row.createCell(10).setCellValue(book.getShippingWeight());
				row.createCell(11).setCellValue(book.getListPrice());
				row.createCell(12).setCellValue(book.getOurPrice());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}
