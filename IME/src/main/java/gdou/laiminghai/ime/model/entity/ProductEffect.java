package gdou.laiminghai.ime.model.entity;

public class ProductEffect {
    private Integer effectId;

    private String effectName;

    public Integer getEffectId() {
        return effectId;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName == null ? null : effectName.trim();
    }
}