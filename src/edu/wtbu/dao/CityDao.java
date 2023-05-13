package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.helper.MySqlHelper;

public class CityDao {
	public static List<HashMap<String, Object>> getCityNames(){
		String sql = "select * from City";
		return MySqlHelper.executeQueryReturnMap(sql, null);
	}
	
			
}
