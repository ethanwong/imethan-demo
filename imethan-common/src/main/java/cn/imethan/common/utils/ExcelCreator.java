package cn.imethan.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Excel读写工具类，依赖Apache POI项目以及相关组件 http://poi.apache.org/
 * 主要封装两个方法:读方法：readExcel、写方法:writeExcel
 * 
 * @author Ethan
 * @since JDK 1.6
 * @see
 */
public class ExcelCreator {

	private String fileName;// 包含完整路径的文件名称，例如“F:\\workbook.xls”
	private String suffix;// 文件类型，文件后缀

	enum Type {
		XLS, XLSX, CSV;
	}

	public ExcelCreator() {

	}

	/**
	 * Creates a new instance of ExcelCreator.
	 * 
	 * @param fileName
	 *            包含完整路径的文件名称，例如“F:\\workbook.xls”
	 */
	public ExcelCreator(String fileName) {
		this.fileName = fileName;
		if (!StringUtils.isEmpty(fileName)) {
			this.suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		}

	}

	/**
	 * 通用Excel文件创建方法，可识别写入单元格值的类型
	 * 
	 * @param fileName
	 *            输出文件路程,含名称，例如“F:\\workbook.xls”
	 * @param sheetName
	 *            表格名称,默认为“sheet1”
	 * @param contentList
	 *            文档内容，其中LinkedList对象中含的第一个LinkedList对象为第一行标题，其他为内容
	 * @author Ethan
	 * @create-time 2014-3-14 下午5:13:56
	 */
	public boolean writeExcel(String sheetName, LinkedList<LinkedList<Object>> contentList) {

		if (!this.validateFile(fileName)) {
			return false;
		}
		if (this.suffix.equalsIgnoreCase(ExcelCreator.Type.CSV.name())) {
			// TODO 处理CSV文件
		} else {
			// 读取XLS和XLSX文件内容
			this.writeXlsAndXlsxExcel(sheetName, contentList);
		}

		return true;

	}

	/**
	 * 生成XLS和XLSX文件
	 * 
	 * @param sheetName
	 * @param contentList
	 * @return
	 * 
	 * @author Ethan
	 * @create-time 2014-3-21 上午11:03:42
	 */
	private boolean writeXlsAndXlsxExcel(String sheetName, LinkedList<LinkedList<Object>> contentList) {
		FileOutputStream fileOut = null;
		try {
			Workbook workbook = new SXSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();

			// 定义单元格时间格式
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(DateUtils.DATE_PATTERN_01));

			// 定义单元格文字格式
			Font font = workbook.createFont();
			font.setBoldweight((short) 16);
			CellStyle fontStyle = workbook.createCellStyle();
			fontStyle.setFont(font);

			// 创建Sheet
			Sheet sheet = workbook.createSheet(sheetName == null || sheetName.trim().equals("") ? "sheet1" : sheetName.trim());

			// 创建标题行
			Row row = sheet.createRow(0);
			int titleColumn = 0;
			for (Object title : contentList.get(0)) {
				Cell cell = row.createCell(titleColumn);
				cell.setCellValue(String.valueOf(title));
				cell.setCellStyle(fontStyle);
				titleColumn++;
			}

			// 创建内容行
			int rowCount = contentList.size();
			for (int rownum = 1; rownum < rowCount; rownum++) {
				Row contentRow = sheet.createRow(rownum);
				int contentColumn = 0;
				for (Object content : contentList.get(rownum)) {
					this.setCellValue(contentRow.createCell(contentColumn), cellStyle, content);
					contentColumn++;
				}
			}

			// 文件输出
			fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 按照不同的类型设置单元格的值
	 * 
	 * @param cell
	 * @param cellStyle
	 * @param value
	 * @author Ethan
	 * @create-time 2014-3-14 下午5:29:25
	 */
	private <T extends Object> void setCellValue(Cell cell, CellStyle cellStyle, T value) {
		if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Integer) {
			cell.setCellType((Integer) value);
		} else if (value instanceof Date) {
			cell.setCellValue(new Date());
			cell.setCellStyle(cellStyle);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		}
	}

	/**
	 * 验证是否是Excel文档
	 * 
	 * @param fileName
	 *            文档名称
	 * @return
	 * 
	 * @author Ethan
	 * @create-time 2014-3-14 下午5:32:03
	 */
	private boolean validateFile(String fileName) {
		if (!suffix.equalsIgnoreCase(ExcelCreator.Type.XLS.name()) && !suffix.equalsIgnoreCase(ExcelCreator.Type.XLSX.name()) && !suffix.equalsIgnoreCase(ExcelCreator.Type.CSV.name())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 读取Excel文档内容，其中返回的LinkedList对象中含的第一个LinkedList对象为第一行标题，其他为内容
	 * 
	 * @param fileName
	 * @return
	 * 
	 * @author Ethan
	 * @create-time 2014-3-17 下午1:49:54
	 */
	public LinkedList<LinkedList<Object>> readExcel() {
		LinkedList<LinkedList<Object>> result = new LinkedList<LinkedList<Object>>();
		// check type
		if (!this.validateFile(fileName)) {
			return null;
		}
		if (this.suffix.equalsIgnoreCase(ExcelCreator.Type.CSV.name())) {
			// TODO 处理CSV文件
		} else {
			// 读取XLS和XLSX文件内容
			this.readXlsAndXlsxExcel(result);
		}

		return result;
	}

	/**
	 * 读取XLS和XLSX文件内容
	 * 
	 * @param result
	 * 
	 * @author Ethan
	 * @create-time 2014-3-21 上午10:22:17
	 */
	private void readXlsAndXlsxExcel(LinkedList<LinkedList<Object>> result) {
		NPOIFSFileSystem fs = null;
		try {
			fs = new NPOIFSFileSystem(new File(fileName));
			HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);

			Sheet sheet = wb.getSheetAt(0);
			for (Row row : sheet) {
				LinkedList<Object> content = new LinkedList<Object>();
				for (Cell cell : row) {
					content.add(this.readCellValue(cell));
				}
				result.add(content);
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取单元格内容
	 * 
	 * @param cell
	 * @return
	 * 
	 * @author Ethan
	 * @create-time 2014-3-17 下午1:43:40
	 */
	private Object readCellValue(Cell cell) {
		Object object = new Object();
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			object = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				object = DateUtils.DateToString(cell.getDateCellValue(), DateUtils.DATE_PATTERN_01);
			} else {
				object = cell.getNumericCellValue();
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			object = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			object = cell.getCellFormula();
			break;
		default:
			object = cell.getStringCellValue();
		}
		return object;
	}

}
