package gdou.laiminghai.ime.model.entity;

import java.util.Date;

public class CommentReportRecord {
    private Long reportRecordId;

    private Long commentId;

    private Long userId;

    private String reportContent;

    private Date reportTime;

    public Long getReportRecordId() {
        return reportRecordId;
    }

    public void setReportRecordId(Long reportRecordId) {
        this.reportRecordId = reportRecordId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent == null ? null : reportContent.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}