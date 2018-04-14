package gdou.laiminghai.ime.model.entity;

public class UserFollowClass {
    private Long userClassId;

    private Integer classId;

    private Long userId;

    public Long getUserClassId() {
        return userClassId;
    }

    public void setUserClassId(Long userClassId) {
        this.userClassId = userClassId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}