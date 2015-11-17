package cn.imethan.common.utils.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * BarCodeUtils.java
 * 
 * @author Ethan
 * @since JDK 1.7
 * @see
 */
public class BarCodeUtils {

	private static final String FORMAT = "PNG";
	private static final Integer WIDTH = 100;
	private static final Integer HEIGHT = 50;

	/**
	 * 生成一维码
	 * 
	 * @param number
	 * @param imgPath
	 *
	 * @author Ethan
	 * @create-time 2015年6月5日 下午1:54:15
	 */
	public static void encode(String number, String imgPath) {
		try {

			number = new String(number.getBytes("UTF-8"), "ISO-8859-1");
			BitMatrix matrix = new MultiFormatWriter().encode(number, BarcodeFormat.CODE_128, WIDTH, HEIGHT);
			MatrixToImageWriter.writeToStream(matrix, FORMAT, new FileOutputStream(new File(imgPath)));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
