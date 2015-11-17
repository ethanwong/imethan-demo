package cn.imethan.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * HtmlUtils.java
 * 
 * @author Ethan
 * @since JDK 1.7
 * @see
 */
public class HtmlUtils {

	/**
	 * 从html中获取第一个img的src地址
	 * 
	 * @param html
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年7月21日 下午5:55:06
	 */
	public static String getImgsByHtml(String html) {
		String src = "";
		Document doc = Jsoup.parse(html);
		Elements imgs = doc.getElementsByTag("img");
		for (Element img : imgs) {
			src = img.attr("src");
			break;
		}
		return src;
	}

}
