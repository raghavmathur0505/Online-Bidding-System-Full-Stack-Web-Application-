package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DBOperation {
	private static final String mysqlurl = "jdbc:mysql://localhost:3306/user_data";
	private static final String mysqluser = "root";
	private static final String mysqlpassword = "Raghav.0505";
	
	private static final String sqlcmd0="USE user_data;";	// use the database statement
	
	
	/**
	 * process the user login
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean userLogin(String username, String password, String location) {
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "SELECT * FROM user_data.users WHERE Username='" + username + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			if (result1.next()) {	// the user exists
				if (result1.getString("Pass").equals(password)) {	// valid login
					result = true;
					// update Last_login
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String Last_login = dateFormat.format(new Date());
					System.out.println(location);
					String sqlcmd2 = "UPDATE users SET Last_login='" + Last_login + "',Last_login_location='" + location + "' WHERE Username ='" + username + "';";
					stmt1.executeUpdate(sqlcmd2);
					System.out.println("The sql statement is " + sqlcmd2);
					System.out.println("This is a valid login.");
				} else {	// failed login: wrong password
					result = false;
					int failedLoginNum = Integer.parseInt(result1.getString("No_failed_login"));
					failedLoginNum++;
					String sqlcmd3 = "UPDATE users SET No_failed_login='" + failedLoginNum + "' WHERE Username ='" + username + "';";
					stmt1.executeUpdate(sqlcmd3);
					System.out.println("The sql statement is " + sqlcmd3);
					System.out.println("Login failed. Wrong password.");
				}
			} else {	// the user doesn't exist
				result = false;
				System.out.println("The user doesn't exist.");
			}
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getProfile(String username) {
		JSONObject resultJSON = new JSONObject();
		
		Connection conn = null;
		// query user information statement
		String sqlcmd1 = "SELECT * FROM user_data.users WHERE Username='" + username + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			
			System.out.println("getting user information...");
			System.out.println("The sql statement is " + sqlcmd1);
			
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {// for all rows
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) { // for 1 row
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getString(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);// result has all rows
			
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return resultJSON;
	}
	
	
	/**
	 * 
	 * @param actPrice2 
	 * @param actQuality2 
	 * @param actDesc2 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param address
	 * @param phone
	 * @return
	 */public static boolean AddBidToCart(String bidID, String itemName, String postUserEmail, String bidUserEmail, String itemID, String bidderId, String postUserID, String expDesc, String expQuality, String expPrice, String actDesc, String actQuality, String actPrice) {
		 boolean result = false;
			Connection conn = null;
			
			// query statement
			String sqlcmd1 = "INSERT INTO shoppingcart VALUES (" + null + ",'" + bidID + "','" + itemID + "','" + itemName + "','" + bidderId + "','" + postUserID + "','" + expDesc + "','" + expQuality + "','" + expPrice + "','" + actDesc + "','" + actQuality + "','" + actPrice + "','" + 1 + "','" + bidUserEmail + "','" + postUserEmail + "');";
			
			System.out.println("sql cmd: "+ sqlcmd1);
			try {
				// connect to database
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
				Statement stmt1 = conn.createStatement();
				
				stmt1.executeQuery(sqlcmd0);	// use the database
				stmt1.executeUpdate(sqlcmd1);	
				result = true;
				System.out.println("The sql statement is " + sqlcmd1);
				System.out.println("The new bid is inserted successfully.");
				
				conn.close();
			} catch (Exception e) {
				System.out.println("Error occurred during communicating with database.");
				e.printStackTrace();
			}
			
			return result;
		}
		public static boolean prodBid(String itemName, String postUserEmail, String bidUserEmail, String itemID, String bidderId, String postUserID, String expDesc,String expQuality,String expPrice, String actDesc, String actQuality,  String actPrice){
		
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "INSERT INTO bid VALUES (" + null + ",'" + itemID + "','" + bidderId + "','" + postUserID + "','" + expDesc + "','" + expQuality + "','" + expPrice + "','" + actDesc + "','" + actQuality + "','" + actPrice + "','" + itemName + "','" + postUserEmail + "','" + bidUserEmail + "');";
		
		System.out.println("sql cmd: "+ sqlcmd1);
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			stmt1.executeUpdate(sqlcmd1);	
			result = true;
			System.out.println("The sql statement is " + sqlcmd1);
			System.out.println("The new bid is inserted successfully.");
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updateUser(String username, String password, String firstName, String lastName, String email, String address1,String address2,String city, String state, String country, String dateofbirth, String phone, String gender) {
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "SELECT * FROM user_data.users WHERE Username='" + username + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1 + password + firstName + lastName + email + address1 + address2 + city + state + country + dateofbirth +phone + gender);
			if (result1.next()) {	// the user already exists
				result = true;
				//int noFailedLogin = 0;
				String sqlcmd2 = "UPDATE users SET U_First_Name='" + firstName + "',Address_Line1='" + address1 + "',Address_Line1='" + address2 + "',U_Last_Name='" + lastName + "',Pass='" + password + "',Email_Id='" + email + "',Birth_Date='" + dateofbirth + "',Gender='" + gender + "',City='" + city + "',State='" + state + "',Country='" + country + "',Ph_No='" + phone + "' WHERE Username ='" + username + "';";
				//String sqlcmd2 = "INSERT INTO users VALUES (" + null + ",'" + username + "','" + firstName + "','" + lastName + "','" + password + "','" + email + "','" + dateofbirth + "','" + gender + "','" + city + "','" + state + "','" + country + "','" + phone + "','" + address1 + "','" + address2 + "'," + null + "," + null + ",'" + noFailedLogin + "');";
				stmt1.executeUpdate(sqlcmd2);
				System.out.println("The sql statement is " + sqlcmd2);
				System.out.println("The user updated information successfully.");
				
			} else {	// the user doesn't exist
				result = true;
				System.out.println("The username does not exists.");
			}
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean prodPost(String username, String itemName, String itemPrice,String itemDesc,String itemCategory,String itemQuality,String add1, String add2,String country, String state,String city){
		
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "INSERT INTO product VALUES (" + null + ",'" + username + "','" + itemName + "','" + itemPrice + "','" + itemDesc + "','" + itemCategory + "','" + itemQuality + "','" + add1 + "','" + add2 + "','" + country + "','" + state + "','" + city + "');";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			stmt1.executeUpdate(sqlcmd1);	
			result = true;
			System.out.println("The sql statement is " + sqlcmd1);
			System.out.println("The new product is inserted successfully.");
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean userSignUp(String username, String password, String firstName, String lastName, String email, String address1,String address2,String city, String state, String country, String dateofbirth, String phone, String gender) {
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "SELECT * FROM user_data.users WHERE Username='" + username + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1 + password + firstName + lastName + email + address1 + address2 + city + state + country + dateofbirth +phone + gender);
			if (result1.next()) {	// the user already exists
				result = false;
				System.out.println("Sign up failed. The username already exists.");
			} else {	// the user doesn't exist
				result = true;
				int noFailedLogin = 0;
				String sqlcmd2 = "INSERT INTO users VALUES ('" + username + "','" + firstName + "','" + lastName + "','" + password + "','" + email + "','" + dateofbirth + "','" + gender + "','" + city + "','" + state + "','" + country + "','" + phone + "','" + address1 + "','" + address2 + "',"  + null + ",'" + noFailedLogin + "'," + null + ");";
				stmt1.executeUpdate(sqlcmd2);
				System.out.println("The sql statement is " + sqlcmd2);
				System.out.println("The user signed up successfully.");
			}
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<String>> searchBid(String title) {
		//JSONObject resultJSON = new JSONObject();
		ArrayList<ArrayList<String>> searchResult = null;
		
		Connection conn = null;
		// query book information statement
		//String sqlcmd1 = "SELECT * FROM product WHERE Post_User_Id=B.Isbn, B.Title, B.Price, B.No_of_copies, GROUP_CONCAT(DISTINCT A.Author_name SEPARATOR ', ') AS Author_name ";
		//sqlcmd1 += "FROM BOOK AS B, AUTHORS AS A ";
		//sqlcmd1 += "WHERE B.Isbn=A.Isbn AND B.Title LIKE '" + title + "' AND B.No_of_copies>=0 GROUP BY A.Isbn ORDER BY B.Price;";
		String sqlcmd2 = "SELECT Post_User_Id, Prod_Id, Prod_Name, P_Price, P_Description, P_Category, P_Quality, P_Address_Line1, P_Address_Line2, P_City, P_State, P_Country FROM user_data.product,users;";
		String sqlcmd1 = "SELECT Email_Id,Post_User_Id, Prod_Id, Prod_Name, P_Price, P_Description, P_Category, P_Quality, P_Address_Line1, P_Address_Line2, P_City, P_State, P_Country FROM user_data.product,user_data.users WHERE Post_User_Id=Username;";
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			
			/*
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) {
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getObject(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);
			*/
			searchResult = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currProduct = new ArrayList<String>();
				
				currProduct.add(result1.getString("Post_User_Id"));
				currProduct.add(result1.getString("Prod_Id"));
				currProduct.add(result1.getString("Prod_Name"));
				currProduct.add(result1.getString("P_Price"));
				currProduct.add(result1.getString("P_Description"));
				currProduct.add(result1.getString("P_Category"));
				currProduct.add(result1.getString("P_Quality"));
				currProduct.add(result1.getString("P_Address_Line1"));
				currProduct.add(result1.getString("P_Address_Line2"));
				currProduct.add(result1.getString("P_City"));
				currProduct.add(result1.getString("P_State"));
				currProduct.add(result1.getString("P_Country"));
				currProduct.add(result1.getString("Email_Id"));
				//currProduct.add(result1.getString("P_Image"));
				searchResult.add(currProduct);
				
				
				//System.out.println("currProduct: "  +  currProduct);
				
			}
			System.out.println("The search product result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return searchResult;
	}
	public static ArrayList<ArrayList<String>> searchPostBidsByTitle(String title) {
		//JSONObject resultJSON = new JSONObject();
		ArrayList<ArrayList<String>> searchResult = null;
		
		Connection conn = null;
		// query book information statement
		//String sqlcmd1 = "SELECT * FROM product WHERE Post_User_Id=B.Isbn, B.Title, B.Price, B.No_of_copies, GROUP_CONCAT(DISTINCT A.Author_name SEPARATOR ', ') AS Author_name ";
		//sqlcmd1 += "FROM BOOK AS B, AUTHORS AS A ";
		//sqlcmd1 += "WHERE B.Isbn=A.Isbn AND B.Title LIKE '" + title + "' AND B.No_of_copies>=0 GROUP BY A.Isbn ORDER BY B.Price;";
		String sqlcmd1 = "SELECT * FROM user_data.bid WHERE Prod_Id ='" + title + "';";
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			
			/*
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) {
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getObject(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);
			*/
			searchResult = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currProduct = new ArrayList<String>();
				currProduct.add(result1.getString("Bid_Id"));
				currProduct.add(result1.getString("Prod_Id"));
				currProduct.add(result1.getString("Bidder_Id"));
				currProduct.add(result1.getString("Post_User_Id"));
				currProduct.add(result1.getString("Exp_Description"));
				currProduct.add(result1.getString("Exp_Quality"));
				currProduct.add(result1.getString("Exp_Price"));
				currProduct.add(result1.getString("Act_Description"));
				currProduct.add(result1.getString("Act_Quality"));
				currProduct.add(result1.getString("Act_Price"));
				currProduct.add(result1.getString("Prod_Name"));
				currProduct.add(result1.getString("Post_Email"));
				currProduct.add(result1.getString("Bidder_Email"));
				//currProduct.add(result1.getString("P_Country"));
				//currProduct.add(result1.getString("P_Image"));
				searchResult.add(currProduct);
				
				
				//System.out.println("currProduct: "  +  currProduct);
				
			}
			System.out.println("The search product result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return searchResult;
	}
	
	/**
	 * get the search book result
	 * @param title
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<String>> viewProductByTitle(String title) {
		//JSONObject resultJSON = new JSONObject();
		ArrayList<ArrayList<String>> viewResult = null;
		
		Connection conn = null;
		// query book information statement
		//String sqlcmd1 = "SELECT * FROM product WHERE Post_User_Id=B.Isbn, B.Title, B.Price, B.No_of_copies, GROUP_CONCAT(DISTINCT A.Author_name SEPARATOR ', ') AS Author_name ";
		//sqlcmd1 += "FROM BOOK AS B, AUTHORS AS A ";
		//sqlcmd1 += "WHERE B.Isbn=A.Isbn AND B.Title LIKE '" + title + "' AND B.No_of_copies>=0 GROUP BY A.Isbn ORDER BY B.Price;";
		String sqlcmd1 = "SELECT Prod_Id, Prod_Name, P_Price, P_Description, P_Category, P_Quality, P_Address_Line1, P_Address_Line2, P_City, P_State, P_Country,Email_Id  FROM user_data.product,users WHERE Post_User_Id ='" + title + "' AND Post_User_Id= Username;" ;
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			
			/*
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) {
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getObject(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);
			*/
			viewResult = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currProduct = new ArrayList<String>();
				currProduct.add(result1.getString("Prod_Id"));
				currProduct.add(result1.getString("Prod_Name"));
				currProduct.add(result1.getString("P_Price"));
				currProduct.add(result1.getString("P_Description"));
				currProduct.add(result1.getString("P_Category"));
				currProduct.add(result1.getString("P_Quality"));
				currProduct.add(result1.getString("P_Address_Line1"));
				currProduct.add(result1.getString("P_Address_Line2"));
				currProduct.add(result1.getString("P_City"));
				currProduct.add(result1.getString("P_State"));
				currProduct.add(result1.getString("P_Country"));
				currProduct.add(result1.getString("Email_Id"));
				
				//currProduct.add(result1.getString("P_Image"));
				viewResult.add(currProduct);
				
				
				//System.out.println("currProduct: "  +  currProduct);
				
			}
			System.out.println("The search product result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return viewResult;
	}
	
	public static ArrayList<ArrayList<String>> searchBiddersByTitle(String title) {
		//JSONObject resultJSON = new JSONObject();
		ArrayList<ArrayList<String>> searchResult = null;
		
		Connection conn = null;
		// query book information statement
		String sqlcmd1 = "SELECT * FROM user_data.bid WHERE Bidder_Id LIKE'" + title + "';";
			
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			
			/*
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) {
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getObject(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);
			*/
			searchResult = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currProduct = new ArrayList<String>();
				currProduct.add(result1.getString("Bid_Id"));
				currProduct.add(result1.getString("Prod_Id"));
				currProduct.add(result1.getString("Bidder_Id"));
				currProduct.add(result1.getString("Post_User_Id"));
				currProduct.add(result1.getString("Exp_Description"));
				currProduct.add(result1.getString("Exp_Quality"));
				currProduct.add(result1.getString("Exp_Price"));
				currProduct.add(result1.getString("Act_Description"));
				currProduct.add(result1.getString("Act_Quality"));
				currProduct.add(result1.getString("Act_Price"));
				searchResult.add(currProduct);
			}
			System.out.println("The search result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return searchResult;
	}
	public static ArrayList<ArrayList<String>> deleteItemInCart(String title, String username) {
		//JSONObject resultJSON = new JSONObject();
		ArrayList<ArrayList<String>> searchResult = null;
		
		Connection conn = null;
		// query book information statement
		String sqlcmd1 = "DELETE FROM user_data.shoppingcart WHERE Prod_Name='" + title + "' AND Post_User_Id='" + username + "';";
		String sqlcmd2 = "SELECT * FROM user_data.shoppingcart WHERE Post_User_Id='" + username + "';";
			
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);
			stmt1.executeUpdate(sqlcmd1);// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd2);	// get the result
			System.out.println("The sql statement 1 is " + sqlcmd1);
			System.out.println("The sql statement 2 is " + sqlcmd2);
			
			/*
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) {
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getObject(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);
			*/
			searchResult = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currProduct = new ArrayList<String>();
				currProduct.add(result1.getString("Prod_Name"));
				currProduct.add(result1.getString("Act_Price"));
				currProduct.add(result1.getString("Bidder_Email"));
				currProduct.add(result1.getString("Post_Email"));
				currProduct.add(result1.getString("Item_Count"));
				
				//currProduct.add(result1.getString("P_Image"));
				searchResult.add(currProduct);
			}
			System.out.println("The search result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return searchResult;
	}

	
	public static ArrayList<ArrayList<String>> viewCart(String title) {
		//JSONObject resultJSON = new JSONObject();
				ArrayList<ArrayList<String>> searchResult = null;
				
				Connection conn = null;
				// query book information statement
				String sqlcmd1 = "SELECT * FROM user_data.shoppingcart WHERE Post_User_Id='" + title + "';";
					
				try {
					// connect to database
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
					Statement stmt1 = conn.createStatement();
					
					stmt1.executeQuery(sqlcmd0);	// use the database
					ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
					System.out.println("The sql statement is " + sqlcmd1);
					
					/*
					// convert the ResultSet to JSON
					JSONArray rows = new JSONArray(); 
					int colNum = result1.getMetaData().getColumnCount();
					while (result1.next()) {
						JSONObject currRow = new JSONObject();
						for (int i = 1; i <= colNum; i++) {
							currRow.put(result1.getMetaData().getColumnLabel(i), result1.getObject(i));
			            }
						rows.add(currRow);
					}
					resultJSON.put("result", rows);
					*/
					searchResult = new ArrayList<ArrayList<String>>();
					while (result1.next()) {
						ArrayList<String> currProduct = new ArrayList<String>();
						currProduct.add(result1.getString("Prod_Name"));
						currProduct.add(result1.getString("Act_Price"));
						currProduct.add(result1.getString("Bidder_Email"));
						currProduct.add(result1.getString("Post_Email"));
						currProduct.add(result1.getString("Item_Count"));
						currProduct.add(result1.getString("Prod_Id"));
						currProduct.add(result1.getString("Bidder_Id"));
						//currProduct.add(result1.getString("P_Image"));
						searchResult.add(currProduct);
					}
					System.out.println("The search result is got successfully.");
							
					result1.close();
					conn.close();
				} catch (Exception e) {
					System.out.println("Error occurred during communicating with database.");
					e.printStackTrace();
				}
				
				return searchResult;
			}

	public static ArrayList<ArrayList<String>> searchProductsByTitle(String title) {
		//JSONObject resultJSON = new JSONObject();
		ArrayList<ArrayList<String>> searchResult = null;
		
		Connection conn = null;
		// query book information statement
		String sqlcmd1 = "SELECT * FROM user_data.product,users WHERE Prod_Name LIKE '" + title + "' AND Post_User_Id = Username;";
			
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			
			/*
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) {
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getObject(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);
			*/
			searchResult = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currProduct = new ArrayList<String>();
				currProduct.add(result1.getString("Post_User_Id"));
				currProduct.add(result1.getString("Prod_Id"));
				currProduct.add(result1.getString("Prod_Name"));
				currProduct.add(result1.getString("P_Price"));
				currProduct.add(result1.getString("P_Description"));
				currProduct.add(result1.getString("P_Category"));
				currProduct.add(result1.getString("P_Quality"));
				currProduct.add(result1.getString("P_Address_Line1"));
				currProduct.add(result1.getString("P_Address_Line2"));
				currProduct.add(result1.getString("P_City"));
				currProduct.add(result1.getString("P_State"));
				currProduct.add(result1.getString("P_Country"));
				currProduct.add(result1.getString("Email_Id"));
				//currProduct.add(result1.getString("P_Image"));
				searchResult.add(currProduct);
			}
			System.out.println("The search result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return searchResult;
	}
	
	public static ArrayList<ArrayList<String>> updateItemCount(String item, String username, String itemCount){
		ArrayList<ArrayList<String>> searchResult = null;
		//boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "SELECT * FROM user_data.shoppingcart WHERE Post_User_Id='" + username + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1 + item + username + itemCount);
			if (result1.next()) {	// the user already exists
				//result = true;
				//int noFailedLogin = 0;
				String sqlcmd2 = "UPDATE shoppingcart SET Item_Count ='" +itemCount+ "' where Prod_Name ='" +item+ "' AND Post_User_Id='" + username + "';";
				String sqlcmd3 = "SELECT * FROM user_data.shoppingcart WHERE Post_User_Id='" + username + "';";
				
				//String sqlcmd2 = "INSERT INTO users VALUES (" + null + ",'" + username + "','" + firstName + "','" + lastName + "','" + password + "','" + email + "','" + dateofbirth + "','" + gender + "','" + city + "','" + state + "','" + country + "','" + phone + "','" + address1 + "','" + address2 + "'," + null + "," + null + ",'" + noFailedLogin + "');";
				stmt1.executeUpdate(sqlcmd2);
				System.out.println("The sql statement is " + sqlcmd2);
				System.out.println("The item count information successfully.");
				System.out.println("For Update ITEM COUNT \n The sql statement is " + sqlcmd1);
				ResultSet result2 = stmt1.executeQuery(sqlcmd3);
				searchResult = new ArrayList<ArrayList<String>>();
				while (result2.next()) {
					ArrayList<String> currProduct = new ArrayList<String>();
					currProduct.add(result2.getString("Prod_Name"));
					currProduct.add(result2.getString("Act_Price"));
					currProduct.add(result2.getString("Bidder_Email"));
					currProduct.add(result2.getString("Post_Email"));
					currProduct.add(result2.getString("Item_Count"));
					currProduct.add(result2.getString("Prod_Id"));
					currProduct.add(result2.getString("Bidder_Id"));
					
					//currProduct.add(result1.getString("P_Image"));
					searchResult.add(currProduct);
				}
				System.out.println("Item Count DB Updated");
			} else {	// the user doesn't exist
				//result = true;
				System.out.println("The product does not exists.");
			}
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return searchResult;
	}
	
	public static ArrayList<ArrayList<String>> emailCart(String itemId, String itemName, String itemPrice, String bidderId,
			String postUserId, String postUserEmail, String bidUserEmail, String itemCount) {
	
		ArrayList<ArrayList<String>> searchResult = null;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "INSERT INTO user_data.orders VALUES (" + null + ",'" + itemId + "','" + itemName + "','" + bidderId + "','" + postUserId + "','" + itemPrice + "','" + itemCount + "','" + bidUserEmail + "','" + postUserEmail + "');";
		String sqlcmd2 = "DELETE FROM user_data.shoppingcart WHERE Prod_Name='" + itemName + "' AND Post_User_Id='" + postUserId + "';";
		String sqlcmd3 = "SELECT * FROM user_data.shoppingcart WHERE Post_User_Id='" + postUserId + "';";
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);
			stmt1.executeUpdate(sqlcmd1);// use the database
			stmt1.executeUpdate(sqlcmd2);// use the database
			
			ResultSet result1 = stmt1.executeQuery(sqlcmd3);	// get the result
			System.out.println("The sql statement 1 is " + sqlcmd1);
			System.out.println("The sql statement 2 is " + sqlcmd2);
			System.out.println("The sql statement 3 is " + sqlcmd3);
			
			
			searchResult = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currProduct = new ArrayList<String>();
				currProduct.add(result1.getString("Prod_Name"));
				currProduct.add(result1.getString("Act_Price"));
				currProduct.add(result1.getString("Bidder_Email"));
				currProduct.add(result1.getString("Post_Email"));
				currProduct.add(result1.getString("Item_Count"));
				currProduct.add(result1.getString("Prod_Id"));
				currProduct.add(result1.getString("Bidder_Id"));
				
				//currProduct.add(result1.getString("P_Image"));
				searchResult.add(currProduct);
			}
			System.out.println("The search result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return searchResult;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getBookInfo(String Isbn) {
		JSONObject resultJSON = new JSONObject();
		
		Connection conn = null;
		// query book information statement
		String sqlcmd1 = "SELECT B.Isbn, B.Title, B.Price, B.No_of_copies, GROUP_CONCAT(DISTINCT A.Author_name SEPARATOR ', ') AS Author_name ";
		sqlcmd1 += "FROM BOOK AS B, AUTHORS AS A ";
		sqlcmd1 += "WHERE B.Isbn=A.Isbn AND B.Isbn='" + Isbn + "';";
		
		String sqlcmd2 = "SELECT OI.Review FROM ORDERED_ITEMS AS OI WHERE OI.Isbn='" + Isbn + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			
			// convert the ResultSet to JSON
			result1.next();
			resultJSON.put("Isbn", result1.getString("Isbn"));
			resultJSON.put("Title", result1.getString("Title"));
			resultJSON.put("Price", result1.getString("Price"));
			resultJSON.put("No_of_copies", result1.getString("No_of_copies"));
			resultJSON.put("Author_name", result1.getString("Author_name"));
			
			ResultSet result2 = stmt2.executeQuery(sqlcmd2);	// get the result
			System.out.println("The sql statement is " + sqlcmd2);
			
			JSONArray reviews = new JSONArray(); 
			while (result2.next()) {
				JSONObject currReview = new JSONObject();
				currReview.put("Review", result2.getString("Review"));
				reviews.add(currReview);
			}
			resultJSON.put("Reviews", reviews);
			System.out.println("The book information is got successfully.");
			
			result1.close();
			result2.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return resultJSON;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<String>> getOrderHistory(String username) {
		//JSONObject resultJSON = new JSONObject();
		ArrayList<ArrayList<String>> orderHistory = null;
		
		Connection conn = null;
		// query order history information statement
		String sqlcmd1 = "SELECT OH.Order_id, OH.Order_time, OH.Total_price, OI.Isbn, B.Title, OI.Quantity, OI.Unit_price, OI.Review ";
		sqlcmd1 += "FROM ORDER_HISTORY AS OH, ORDERED_ITEMS AS OI, BOOK AS B ";
		sqlcmd1 += "WHERE OH.Username='" + username + "' AND OH.Order_id=OI.Order_id AND OI.Isbn=B.Isbn ORDER BY OH.Order_id DESC;";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			
			/*
			// convert the ResultSet to JSON
			JSONArray rows = new JSONArray(); 
			int colNum = result1.getMetaData().getColumnCount();
			while (result1.next()) {
				JSONObject currRow = new JSONObject();
				for (int i = 1; i <= colNum; i++) {
					currRow.put(result1.getMetaData().getColumnLabel(i), result1.getString(i));
	            }
				rows.add(currRow);
			}
			resultJSON.put("result", rows);
			System.out.println("The order history is got successfully.");
			*/
			
			orderHistory = new ArrayList<ArrayList<String>>();
			while (result1.next()) {
				ArrayList<String> currBook = new ArrayList<String>();
				currBook.add(result1.getString("Order_id"));
				currBook.add(result1.getString("Order_time"));
				currBook.add(result1.getString("Total_price"));
				currBook.add(result1.getString("Isbn"));
				currBook.add(result1.getString("Title"));
				currBook.add(result1.getString("Quantity"));
				currBook.add(result1.getString("Unit_price"));
				currBook.add(result1.getString("Review"));
				orderHistory.add(currBook);
			}
			System.out.println("The order history is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return orderHistory;
	}
	
	public static boolean addReview(String orderId, String isbn, String review) {
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "UPDATE ORDERED_ITEMS SET Review='" + review + "' WHERE Order_id='" + orderId + "' AND Isbn='" + isbn + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			stmt1.executeUpdate(sqlcmd1);	// get the result
			result = true;
			System.out.println("The sql statement is " + sqlcmd1);
			System.out.println("The review is added successfully.");
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static boolean submitOrder(String username, String totalPrice, JSONArray items) {
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "SELECT * FROM BOOKSTORE_INFO;";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			System.out.println("The sql statement is " + sqlcmd1);
			result1.next();
			int orderId = result1.getInt("Max_order_no") + 1;
			
			// insert a row into order_history table
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String orderTime = dateFormat.format(new Date());
			String sqlcmd2 = "INSERT INTO ORDER_HISTORY VALUES ('" + orderId + "','" + username + "','" + orderTime + "','" + totalPrice + "');";
			stmt1.executeUpdate(sqlcmd2);
			System.out.println("The sql statement is " + sqlcmd2);
			
			// insert multiple rows into the ordered_items table
			for (int i = 0; i < items.size(); i++) {
				String isbn = ((JSONObject)items.get(i)).get("Isbn").toString();
				String quantity = ((JSONObject)items.get(i)).get("Quantity").toString();
				String unitPrice = ((JSONObject)items.get(i)).get("Unit_price").toString();
				String sqlcmd3 = "INSERT INTO ORDERED_ITEMS VALUES ('" + orderId + "','" + isbn + "','" + quantity + "','" + unitPrice + "'," + null + ");";
				stmt1.executeUpdate(sqlcmd3);
				System.out.println("The sql statement is " + sqlcmd3);
			}
			
			// update the BOOKSTORE_INFO table
			String sqlcmd4 = "UPDATE BOOKSTORE_INFO SET Max_order_no='" + orderId + "';";
			stmt1.executeUpdate(sqlcmd4);
			System.out.println("The sql statement is " + sqlcmd4);
			
			result = true;
			System.out.println("Submit order successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updateUserAddress(String username, String newAddress) {
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "UPDATE USERS SET Address='" + newAddress + "' WHERE Username='" + username + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			stmt1.executeUpdate(sqlcmd1);	
			result = true;
			System.out.println("The sql statement is " + sqlcmd1);
			System.out.println("The address is updated successfully.");
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updateUserPhone(String username, String newPhone) {
		boolean result = false;
		Connection conn = null;
		
		// query statement
		String sqlcmd1 = "UPDATE USERS SET Phone='" + newPhone + "' WHERE Username='" + username + "';";
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			stmt1.executeUpdate(sqlcmd1);	
			result = true;
			System.out.println("The sql statement is " + sqlcmd1);
			System.out.println("The phone is updated successfully.");
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return result;
	}

	
	
	
	
}
