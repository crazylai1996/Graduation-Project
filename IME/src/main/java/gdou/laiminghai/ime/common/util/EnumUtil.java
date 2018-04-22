package gdou.laiminghai.ime.common.util;

import java.util.ArrayList;
import java.util.List;

import gdou.laiminghai.ime.common.statics.BaseEnum;
import gdou.laiminghai.ime.model.vo.SelectItemVO;

/**
 * 枚举工具类
 * @author laiminghai
 *
 */
public class EnumUtil {
	/**
	 * 将枚举转为List输出到页面
	 * @param clazz
	 * @return
	 */
	public static List<SelectItemVO> toList(Class<? extends BaseEnum<?, ?>> clazz){
		List<SelectItemVO> items = new ArrayList<SelectItemVO>();
		if (clazz.isEnum()) {
			Object[] enums = clazz.getEnumConstants();
			for (Object obj : enums) {
				BaseEnum<?, ?> e = (BaseEnum<?,?>)obj;
				String code = String.valueOf(e.getCode());
				String name = e.getName();
				items.add(new SelectItemVO(code, name));
			}
		}
 		return items;
	}
}
