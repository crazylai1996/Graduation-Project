package gdou.laiminghai.ime.model.entity;

public class AdminMenu {
    private Integer menuId;

    private Integer permId;

    private String menuName;

    private Integer isRootNavi;

    private Integer parentMenuId;

    private String url;

    private Integer menuOrder;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getIsRootNavi() {
        return isRootNavi;
    }

    public void setIsRootNavi(Integer isRootNavi) {
        this.isRootNavi = isRootNavi;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }
}