package gdou.laiminghai.ime.common.task;

import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.service.RankService;

public class RankTask {
	
	@Autowired
	private RankService rankService;
	
	public void updateBrowserCountRank() {
		rankService.updateBrowserCountRank();
	}
	
	public void syncBrowserRecord() {
		rankService.syncBrowserRecord();
	}
}
