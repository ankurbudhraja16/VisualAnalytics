package com.visualanalytics;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.model.Data;

/**
 * Servlet implementation class GeoServlet
 */
@WebServlet("/GeoServlet")
public class GeoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map<String, Data> countMap = new HashMap<String, Data>();
		// Data data = new Data();

		try {

			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost/visual_analytics";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");

			String query = "SELECT * FROM sample";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next()) {
				String countryName = rs.getString("country_name");
				String countryCode = rs.getString("country_code");
				String weaponType = rs.getString("major_weapon");

				if (countMap.containsKey(countryCode)) {
					Data existingData = countMap.get(countryCode);
					existingData.setCount(existingData.getCount() + 1);

					System.out.println("arm type is " + weaponType);

					if (weaponType.equals("firearms")) {
						existingData.setWeaponFirearms(existingData.getWeaponFirearms() + 1);
					} else if (weaponType.equals("explosives")) {
						existingData.setWeaponExplosives(existingData.getWeaponExplosives() + 1);
					} else if (weaponType.equals("chemical")) {
						existingData.setWeaponChemicals(existingData.getWeaponChemicals() + 1);
					} else if (weaponType.equals("incendiary")) {
						existingData.setWeaponIncendiary(existingData.getWeaponIncendiary() + 1);
					} else if (weaponType.equals("biological")) {
						existingData.setWeaponBiological(existingData.getWeaponBiological() + 1);
					} else {
						existingData.setOthers(existingData.getOthers() + 1);
					}
					countMap.put(countryCode, existingData);
				} else {
					Data newData = new Data();
					newData.setCount(1);

					if (weaponType.equals("firearms")) {
						newData.setWeaponFirearms(1);
					} else if (weaponType.equals("explosives")) {
						newData.setWeaponExplosives(1);
					} else if (weaponType.equals("chemical")) {
						newData.setWeaponChemicals(1);
					} else if (weaponType.equals("incendiary")) {
						newData.setWeaponIncendiary(1);
					} else if (weaponType.equals("biological")) {
						newData.setWeaponBiological(1);
					} else {
						newData.setOthers(1);
					}

					countMap.put(countryCode, newData);
				}

				System.out.println("Map is: " + countMap.toString());

				JSONArray array = new JSONArray();
				

				for (Map.Entry<String, Data> mapData : countMap.entrySet()) {

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", mapData.getKey());
					jsonObject.put("name", "India");
					jsonObject.put("value", mapData.getValue().getCount());
					jsonObject.put("color", "#eea638");

					array.put(jsonObject);

				}
				message = array.toString();
				System.out.println("Array is:" + message);
			}
			st.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			System.err.println("Got an exception! ");
		}

//		String theURL = "geo.html?"+ "values="+message;
//		theURL = response.encodeRedirectURL(theURL);
//		response.sendRedirect(theURL);
//		//response.sendRedirect("geo.html");
		
		
//		message = message.replace('"', '\'');
//		request.setAttribute("message", message);
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/geomap.jsp");
//		rd.forward(request, response);
		
		response.sendRedirect("geo.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
