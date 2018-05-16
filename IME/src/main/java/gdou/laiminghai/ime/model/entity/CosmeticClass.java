package gdou.laiminghai.ime.model.entity;

import java.util.List;

public class CosmeticClass {
    private Integer classId;
    
    private Integer parentClassId;

    private String className;
    
    private List<CosmeticClass> childClasses;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getParentClassId() {
		return parentClassId;
	}

	public void setParentClassId(Integer parentClassId) {
		this.parentClassId = parentClassId;
	}

	public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

	public List<CosmeticClass> getChildClasses() {
		return childClasses;
	}

	public void setChildClasses(List<CosmeticClass> childClasses) {
		this.childClasses = childClasses;
	}
}