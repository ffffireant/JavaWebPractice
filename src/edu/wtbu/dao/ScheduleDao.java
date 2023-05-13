package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.helper.MySqlHelper;

public class ScheduleDao {
	public static int updateScheduleDao(int scheduleId,String status) {
		String sql = "update `Schedule` set Status = ? where ScheduleId = ?";
		return MySqlHelper.executeUpdate(sql,new Object[] {status,scheduleId});
	}
	public static HashMap<String, Object> findByScheduleId(int scheduleId) {
		String sql = "select * from `Schedule` "+"left join Route on `Schedule`.RouteId = Route.RouteId "+
					 "left join Aircraft on Aircraft.AircraftId = `Schedule`.AircraftId "+"where `Schedule`.ScheduleId = ?";
		List<HashMap<String, Object>> list = MySqlHelper.executeQueryReturnMap(sql, new Object[] {scheduleId});
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}
}	
