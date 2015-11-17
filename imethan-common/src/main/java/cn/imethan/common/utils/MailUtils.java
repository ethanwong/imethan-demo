package cn.imethan.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * MailUtils.java
 * 
 * @author Ethan
 * @since JDK 1.7
 * @see
 */
public class MailUtils {

	public static String defaultSMTPHost;// 邮件服务器地址
	public static String defaultMailFromAccount;// 发送邮箱帐号
	public static String defaultMailFromPassword;// 发送邮箱密码
	public static String defaultMailFromUserName;// 邮箱发送人名称

	// static{
	// PropertiesUtils.ReadProp("main/init.properties");
	// defaultSMTPHost = PropertiesUtils.getValue("email.host").trim();
	// defaultMailFromUserName =
	// PropertiesUtils.getValue("email.username").trim();
	// defaultMailFromAccount =
	// PropertiesUtils.getValue("email.account").trim();
	// defaultMailFromPassword =
	// PropertiesUtils.getValue("email.password").trim();
	//
	// char[] chars = defaultMailFromUserName.toCharArray();
	// defaultMailFromUserName = "";
	// int length = chars.length;
	// for(int i = 0;i < length;i++){
	// if(i < length-1){
	// defaultMailFromUserName += chars[i];
	// }
	// }
	// }

	/**
	 * 发送邮件
	 * 
	 * @param mailTos
	 *            接收者
	 * @param mailTitle
	 *            邮件标题
	 * @param mailContent
	 *            邮件内容
	 * @return
	 */
	private static int send(String mailTos, String mailTitle, String mailContent) {

		// 发送内容检查
		if (mailTos == null || mailTitle == null || mailContent == null) {
			return 0;
		}

		Properties props = new Properties();
		String encoding = "UTF-8";// 设置编码
		try {
			// 标题不需要编码,编码后为乱码
			// 正文需要编码
			mailContent = new String(mailContent.getBytes(), encoding);

			props.put("mail.smtp.host", defaultSMTPHost);// 邮件服务器地址
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");// 不打印构建发送者信息

		} catch (UnsupportedEncodingException e) {
			System.out.println("[mail] - send:fail to encoding mail text to " + encoding);
		}

		// 构建发送者
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(defaultMailFromAccount, defaultMailFromPassword);
			}
		};

		Session session = Session.getInstance(props, auth);
		session.setDebug(false);// 不打印发送信息

		Message msg = new MimeMessage(session);
		int sendNum = 0;// 发送的数量

		for (String mailTo : mailTos.split(",")) {
			if (mailTo != null && !mailTo.trim().equals("")) {
				try {
					InternetAddress[] addresses = { new InternetAddress(mailTo) };
					msg.setFrom(new InternetAddress(defaultMailFromAccount, defaultMailFromUserName.toString(), "UTF-8"));
					msg.setRecipients(Message.RecipientType.TO, addresses);// 设置邮件接收地址集
					msg.setSentDate(new java.util.Date());// 设置邮件发送日期
					msg.setSubject(mailTitle);// 设置邮件的标题
					// msg.setText(mailText);// 设置邮件的内容(文本)
					msg.setContent(mailContent, "text/html;charset=UTF-8");// 设置邮件的内容
					Transport.send(msg);// 发送邮件
					sendNum++;// 发送记数
				} catch (MessagingException e) {
					System.out.println("[mail] - send:fail to send email ");
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		return sendNum;
	}

	/**
	 * 发送邮件 方法的作用:这里描述这个方法的作用–必填.<br/>
	 * 方法适用条件:这里描述这个方法适用条件–可选.<br/>
	 * 执行流程:这里描述这个方法的执行流程–可选.<br/>
	 * 使用方法:这里描述这个方法的使用方法 –可选.<br/>
	 * 注意事项:这里描述这个方法的注意事项 –可选.<br/>
	 * 
	 * @param emails
	 *            邮箱地址，多个以英文逗号隔开
	 * @param title
	 *            短信标题
	 * @param content
	 *            短信内容
	 * @return
	 * 
	 * @author huangyf
	 * @create-time 2013-8-20 下午2:00:33
	 */
	public static int sendMail(String emails, String title, String content) {
		content = "<table align=center width=100%><tr><td align=left>" + content + "</td></tr></table>";
		int count = send(emails, title, content);
		return count;
	}

	public static void main(String args[]) {
		MailUtils.sendMail("huangyf@zmyou.com", "中文标题test title", "中文标题test content" + DateUtils.getDatetimeStr(DateUtils.DATE_PATTERN_01));
	}
}
