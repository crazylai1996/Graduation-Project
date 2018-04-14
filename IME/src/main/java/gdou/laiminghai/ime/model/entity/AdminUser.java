package gdou.laiminghai.ime.model.entity;

public class AdminUser {
    private String adminUser;

    private String adminPassword;

    private String passwordSalt;

    private Integer isSa;

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser == null ? null : adminUser.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    public Integer getIsSa() {
        return isSa;
    }

    public void setIsSa(Integer isSa) {
        this.isSa = isSa;
    }
}