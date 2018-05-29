package gdou.laiminghai.ime.model.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 点评统计分析
 * @ClassName: CommentAnalysisVO
 * @author: laiminghai
 * @datetime: 2018年5月29日 上午10:00:18
 */
public class CommentAnalysisVO {
	/**
	 * 综合评价
	 */
	private Float avgScore;
	/**
	 * 点评量
	 */
	private Long commentCount;
	/**
	 * 性价评分统计分析
	 */
	private Map<String,Float> scoreAnalysis = new HashMap<>();
	/**
	 * 用户肤质统计分析
	 */
	private Map<String,Float> skinTextureAnalysis = new HashMap<>();
	/**
	 * 用户年龄统计分析
	 */
	private Map<String,Float> ageAnalysis = new HashMap<>();
	
	public Float getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}
	public Long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	public Map<String, Float> getScoreAnalysis() {
		return scoreAnalysis;
	}
	public void setScoreAnalysis(Map<String, Float> scoreAnalysis) {
		this.scoreAnalysis = scoreAnalysis;
	}
	public Map<String, Float> getSkinTextureAnalysis() {
		return skinTextureAnalysis;
	}
	public void setSkinTextureAnalysis(Map<String, Float> skinTextureAnalysis) {
		this.skinTextureAnalysis = skinTextureAnalysis;
	}
	public Map<String, Float> getAgeAnalysis() {
		return ageAnalysis;
	}
	public void setAgeAnalysis(Map<String, Float> ageAnalysis) {
		this.ageAnalysis = ageAnalysis;
	}
}
