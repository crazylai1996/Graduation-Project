package gdou.laiminghai.ime.model.dto;

import java.util.List;

public class PageResult<T> {
	// 总页数
	private int pages;
	// 结果集
	protected List<T> list;
	//每页的数量
    private int pageSize;

	public PageResult(List<T> list) {
		this.list = list;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageResult [pages=" + pages + ", list=" + list + ", pageSize=" + pageSize + "]";
	}

}
