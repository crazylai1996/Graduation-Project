package gdou.laiminghai.ime.common.statics;

public interface BaseEnum <E extends Enum<?>, T>{
	T getCode();
	String getName();
	E of(String code);
}
