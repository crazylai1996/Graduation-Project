package gdou.laiminghai.ime.common.util;

import java.util.Date;
import java.util.UUID;

public class RedisKeyUtil {
	/**
	 * 用户偏好记录：HASH
	 */
	private static final String USER_PREF_PRE_KEY = "IME:USER:PREF";
	/**
	 * 用户新增偏好记录：SET
	 */
	private static final String USER_NEW_PREFS_PRE_KEY = "IME:USER:PREFS_NEW";
	/**
	 * 用户偏好集合：SET
	 */
	private static final String USER_PREFS_CLASS_PRE_KEY = "IME:USER:PREFS";
	/**
	 * 待同步商品浏览记录：HASH
	 */
	private static final String PRODUCT_BROWSER_RECORD_TO_SYNC_PRE_KEY = "IME:PRODUCT:BROWSER_RECORD";
	/**
	 * 待同步商品浏览记录集：SET
	 */
	private static final String PRODUCT_BROWSE_RECORDS_TO_SYNC_KEY = "IME:PRODUCT:BROWSER_RECORDS";
	/**
	 * 商品浏览量排行：ZSET
	 */
	private static final String RANK_PRODUCT_BROWSER_KEY = "IME:RANK:PRODUCT_BROWSER";
	
	/**
	 * 用户偏好记录：HASH
	 */
	public static String getUserPrefKey(long userId) {
		String timestamp = String.valueOf(new Date().getTime());
		return USER_PREF_PRE_KEY+":"+userId+":"+timestamp;
	}
	/**
	 * 用户新增偏好记录：SET
	 */
	public static final String getUserNewPrefsKey(long userId) {
		return USER_NEW_PREFS_PRE_KEY + ":" +userId;
	}
	/**
	 * 用户偏好集合：SET
	 */
	public static String getUserClassPrefsKey(String skinTexture,int age) {
		return USER_PREFS_CLASS_PRE_KEY+":"+skinTexture+":"+age;
	}
	/**
	 * 待同步商品浏览记录：HASH
	 */
	public static String getToSyncProductBrowserRecordKey() {
		String key = UUID.randomUUID().toString().replaceAll("-", "");
		return PRODUCT_BROWSER_RECORD_TO_SYNC_PRE_KEY+":"+key;
	}
	/**
	 * 待同步商品浏览记录集：SET
	 */
	public static String getToSyncProductBrowserRecordsKey() {
		return PRODUCT_BROWSE_RECORDS_TO_SYNC_KEY;
	}
	/**
	 * 商品浏览量排行：ZSET
	 */
	public static String getProductBrowserRankKey() {
		return RANK_PRODUCT_BROWSER_KEY;
	}
}
