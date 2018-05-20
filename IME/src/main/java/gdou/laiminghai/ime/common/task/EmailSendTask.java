package gdou.laiminghai.ime.common.task;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.sun.mail.util.MailSSLSocketFactory;

import gdou.laiminghai.ime.common.setting.AppSetting;

public class EmailSendTask implements Runnable {
	
	/**
	 * 日志记录
	 */
	private static final Logger logger = Logger.getLogger(EmailSendTask.class);

	private String email;
	private String subject;
	private String message;

	public EmailSendTask(String email ,
			String subject , String message) {
		this.email = email;
		this.subject = subject;
		this.message = message;
	}

	@Override
	public void run() {
		// 跟smtp服务器建立一个连接
		Properties p = new Properties();
		// 设置邮件服务器主机名
		p.setProperty("mail.host", "smtp.qq.com");// 指定邮件服务器，默认端口 25
		// 发送服务器需要身份验证
		p.setProperty("mail.smtp.auth", "true");// 要采用指定用户名密码的方式去认证
		// 发送邮件协议名称
		p.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			logger.error("开启SSL加密异常：",e);
		}
		sf.setTrustAllHosts(true);
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.ssl.socketFactory", sf);
		// 创建session
		Session session = Session.getDefaultInstance(p, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名可以用QQ账号也可以用邮箱的别名
				PasswordAuthentication pa = new PasswordAuthentication(
						AppSetting.MAIL_ACCOUNT, AppSetting.MAIL_AUTH_CODE);
				// 后面的字符是授权码，用qq密码不行！！
				return pa;
			}
		});
		session.setDebug(true);// 设置打开调试状态
		// 发送邮件
		try {
			Transport.send(getMessage(session));
		} catch (MessagingException e) {
			logger.error("发送错误：",e);
		}
		
	}
	
	private MimeMessage getMessage(Session session){
		// 声明一个Message对象(代表一封邮件),从session中创建
		MimeMessage msg = new MimeMessage(session);
		try {
			// 邮件信息封装
			// 1发件人
			msg.setFrom(new InternetAddress(AppSetting.MAIL_ACCOUNT));
			// 2收件人
			msg.setRecipient(RecipientType.TO, new InternetAddress(this.email));
			// 3邮件内容:主题、内容
			msg.setSubject(this.subject);

			msg.setContent(this.message, "text/html;charset=utf-8");// 发html格式的文本
			return msg;
		} catch (AddressException e) {
			logger.error("封装Message对象异常：",e);
		} catch (MessagingException e) {
			logger.error("封装Message对象异常：",e);
		}
		return msg;
	}
}
