package gdou.laiminghai.ime.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;

/**
 * 文件工具
 * 
 * @ClassName: FileUtil
 * @author: laiminghai
 * @datetime: 2018年5月15日 上午1:53:29
 */
public class FileUtil {

	// 日志记录
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 图片保存至磁盘
	 * @param file
	 * @param savedPath
	 * @param fileName
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 上午2:01:25
	 */
	public static boolean save(MultipartFile file, String savedPath, String fileName) {
		boolean success = true;
		File targetFile = new File(savedPath, fileName);
		// 父目录是否存在，不存在则创建
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		FileOutputStream out = null;
		InputStream in = null;
		try {
			out = new FileOutputStream(targetFile);
			in = file.getInputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			while (-1 != (len = in.read(buff))) {
				out.write(buff, 0, len);
			}
		} catch (Exception e) {
			logger.error("保存图片文件异常：", e);
			success = false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				logger.error("关闭文件流异常：", e);
				success = false;
			}
		}
		return success;
	}

	/**
	 * 图片从磁盘删除
	 * @param savedPath
	 * @param oldPortraitName
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 上午2:01:57
	 */
	public static void delete(String savedPath, String oldPortraitName) {
		// 从磁盘删除旧头像
		File oldFile = new File(savedPath, oldPortraitName);
		if (oldFile.exists()) {
			oldFile.delete();
		}
	}
}
