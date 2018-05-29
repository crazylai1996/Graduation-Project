package gdou.laiminghai.ime.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListUtil {
	/**
	 * 从List随机获取若干个元素
	 * @param paramList
	 * @param count
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月29日 下午8:19:54
	 */
	public static List getRandomList(List paramList, int count) {
		if (paramList.size() < count) {
			return paramList;
		}
		Random random = new Random();
		List<Integer> tempList = new ArrayList<Integer>();
		List<Object> newList = new ArrayList<Object>();
		int temp = 0;
		for (int i = 0; i < count; i++) {
			temp = random.nextInt(paramList.size());// 将产生的随机数作为被抽list的索引
			if (!tempList.contains(temp)) {
				tempList.add(temp);
				newList.add(paramList.get(temp));
			} else {
				i--;
			}
		}
		return newList;
	}
}
