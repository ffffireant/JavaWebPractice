package edu.wtbu.service;

import java.util.HashMap;

import edu.wtbu.dao.ScheduleDao;
import edu.wtbu.pojo.Result;

public class ScheduleService {
	public static Result updateSchedule(int scheduleId,String status) {
		Result result = new Result("fail", null, null);
		HashMap<String, Object> scheduleinfo = ScheduleDao.findByScheduleId(scheduleId);
		if(scheduleinfo==null) {
			result.setFlag("航班计划不存在");
			return result;
		}
		int updateResult = ScheduleDao.updateScheduleDao(scheduleId, status);
		if(updateResult>0) {
			result.setFlag("success");
		}
		return result;
	}
}
