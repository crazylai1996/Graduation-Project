package gdou.laiminghai.ime.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.model.dto.SmsCaptchaResponseDTO;

public class CaptchaUtil {

	/**
	 * 随机对象
	 */
	private static Random random = new Random();

	/**
	 * 获取字符数字随机字符串
	 * 
	 * @param count
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月5日 下午6:43:52
	 */
	public static String getRandomCharCaptcha(int count) {
		String[] charArr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
				"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
				"Z" };
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < count; i++) {
			result.append(charArr[random.nextInt(charArr.length)]);
		}
		return result.toString();
	}

	/**
	 * 获取随机短信验证码
	 * 
	 * @param count
	 *            验证码长度
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月6日 上午10:24:41
	 */
	public static String getRandomNumberCaptcha(int count) {
		String numbers = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(numbers.charAt(random.nextInt(numbers.length())));
		}
		return sb.toString();
	}

	/**
	 * 获取随机颜色
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月5日 下午6:43:49
	 */
	private static Color getRandomColor() {
		Color[] colors = new Color[5];
		colors[0] = new Color(32, 158, 25);
		colors[1] = new Color(218, 42, 19);
		colors[2] = new Color(31, 75, 208);
		colors[3] = new Color(0, 102, 182);
		colors[4] = new Color(171, 0, 85);

		return colors[random.nextInt(5)];
	}

	/**
	 * 获取随机字体
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月5日 下午6:43:45
	 */
	private static Font getRandomFont() {
		Font fonts[] = new Font[5];
		fonts[0] = new Font("Aharoni", Font.PLAIN, 35);
		fonts[1] = new Font("Book Antiqua", Font.PLAIN, 40);
		fonts[2] = new Font("Calibri", Font.PLAIN, 37);
		fonts[3] = new Font("Lucida Console", Font.PLAIN, 39);
		fonts[4] = new Font("DilleniaUPC", Font.PLAIN, 34);
		return fonts[random.nextInt(5)];
	}

	/**
	 * 生成验证码图片
	 * 
	 * @param securityCode
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param out
	 * @throws IOException
	 * @author: laiminghai
	 * @datetime: 2018年5月5日 下午6:43:38
	 */
	public static void renderCaptchaImage(String securityCode, int width, int height, OutputStream out)
			throws IOException {
		BufferedImage captchaImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D g = (Graphics2D) captchaImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 画出随机字符串
		for (int i = 0; i < securityCode.length(); i++) {
			int y = 0;
			char c = securityCode.charAt(i);
			if (i == random.nextInt(securityCode.length())) {
				y = 30 - random.nextInt(6);
			} else {
				y = 30 + random.nextInt(6);
			}

			g.setColor(getRandomColor());
			g.setFont(getRandomFont());
			g.drawString(String.valueOf(c), 25 * i + 15, y);
		}
		// 随机增加100个点
		for (int i = 0; i < 100; i++) {
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawOval(random.nextInt(width), random.nextInt(height), 0, 0);
		}
		// 随机增加10条线
		for (int i = 0; i < 20; i++) {
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawLine(random.nextInt(width), random.nextInt(width), random.nextInt(height), random.nextInt(height));
		}
		g.dispose();
		ImageIO.write(captchaImage, "jpg", out);
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 *            手机号
	 * @param captcha
	 *            验证码
	 * @author: laiminghai
	 * @datetime: 2018年5月6日 下午8:11:56
	 */
	public static SmsCaptchaResponseDTO sendSmsCaptcha(String phone, String captcha) {
		// 当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		// 签名
		String sig = DigestUtils.md5Hex(AppSetting.MIAODI_ACCOUNT_SID + 
				AppSetting.MIAODI_AUTH_TOKEN + timestamp);
		//验证码失效时间，单位：分钟
		long timeout = AppSetting.CAPTCHA_SMS_TIMEOUT/(1000*60);
		//构造请求参数
		String params = "accountSid=" + AppSetting.MIAODI_ACCOUNT_SID + 
				"&to=" + phone + 
				"&templateid=" + AppSetting.MIAODI_SMS_TEMPLATE_ID + 
				"&param=" + captcha + "," + timeout +
				"&timestamp=" + timestamp + 
				"&sig=" + sig + 
				"&respDataType=JSON";
		//发送请求，获取请求结果 
		String result = HttpUtil.sendPost(AppSetting.MIAODI_SMS_RQUEST_URL, params);
		//Bean对象转换
		SmsCaptchaResponseDTO responseDTO = JSON.parseObject(result, SmsCaptchaResponseDTO.class);
		return responseDTO;
	}
}
