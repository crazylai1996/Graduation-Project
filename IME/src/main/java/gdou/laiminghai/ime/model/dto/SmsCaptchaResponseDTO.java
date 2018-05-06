package gdou.laiminghai.ime.model.dto;

/**
 * 秒嘀短信验证码接口返回结果
 * @ClassName: SmsCaptchaResponseDTO
 * @author: laiminghai
 * @datetime: 2018年5月6日 下午8:20:27
 */
public class SmsCaptchaResponseDTO {
	/**
	 * 请求状态码:00000为成功
	 */
	private String respCode;
	private String respDesc;
	private String failCount;
	private String failList;
	private String smsId;
	
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getFailCount() {
		return failCount;
	}
	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}
	public String getFailList() {
		return failList;
	}
	public void setFailList(String failList) {
		this.failList = failList;
	}
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
}
