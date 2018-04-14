package gdou.laiminghai.ime.model.entity;

import java.util.Date;

public class UserUpRecord {
    private Long upRecordId;

    private Long commentId;

    private Long userId;

    private Date upTime;

    public Long getUpRecordId() {
        return upRecordId;
    }

    public void setUpRecordId(Long upRecordId) {
        this.upRecordId = upRecordId;
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

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}