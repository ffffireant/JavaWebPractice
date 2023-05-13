package edu.wtbu.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import edu.wtbu.pojo.Result;
import edu.wtbu.service.UsersService;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset = UTF-8");
		String email = request.getParameter("email");
		String password = "";
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String phone = request.getParameter("phone");
		String photo = request.getParameter("photo");
		String address = request.getParameter("address");
		int roleId = 0;
		try {
			roleId = Integer.parseInt(request.getParameter("roleId"));
		}catch (Exception e) {
			// TODO: handle exception
			roleId = 0;
		}
		try {
			password = email.split("@")[0];
			password = password.length()>6?password.substring(0,6):password;
		}catch (Exception e) {
			password = "123456";
		}
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		map.put("firstName", firstName);
		map.put("lastName", lastName);
		map.put("gender", gender);
		map.put("dateOfBirth", dateOfBirth);
		map.put("phone", phone);
		map.put("photo", photo);
		map.put("address", address);
		map.put("roleId", roleId);
		Result result = UsersService.addUser(map);
		String msg = JSON.toJSONString(result);
		response.getWriter().append(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
