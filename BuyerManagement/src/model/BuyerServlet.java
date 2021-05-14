package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuyerServlet {

	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/buyertable?useTimezone=true&serverTimezone=UTC", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();} 
		 	return con; 
		} 
	
	public String insertBuyer(String buyerName, String projectName, String email, String contactNo, String researcherName) 
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for inserting."; 
			 } 
		 	 	 // create a prepared statement
			 	 String query = "INSERT INTO buyer_table_gui(`buyerID`,`buyerName`,`projectName`,`email`,`contactNo`,`researcherName`)  VALUES (?, ?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
				 
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, buyerName);
				 //System.out.println(buyerName);
				 preparedStmt.setString(3, projectName);
				 preparedStmt.setString(4, email);
				 preparedStmt.setString(5, contactNo);
				 preparedStmt.setString(6, researcherName);
				 				 
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newBuyer = readBuyer(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newBuyer + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Buyer.\"}";
			 System.out.println(e.getMessage());
				System.out.println(e);
				e.printStackTrace();
		 } 
	 	return output; 
	 } 
	
	 public String readBuyer() 
	 { 
		 String output = ""; 

		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>buyerID</th>"
			 + "<th>buyerName</th>" +
			 "<th>projectName</th>" + 
			 "<th>email</th>" + 
			 "<th>contactNo</th>" +
			 "<th>researcherName</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
		 
			 
			 String query = "SELECT * FROM buyer_table_gui"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String buyerID = Integer.toString(rs.getInt("buyerID")); 
				 String buyerName = rs.getString("buyerName"); 
				 String projectName = rs.getString("projectName"); 
				 String email = rs.getString("email"); 
				 String contactNo = rs.getString("contactNo"); 
				 String researcherName = rs.getString("researcherName");
				 
				 
				 
				 // Add into the html table
				 
				 output += "<tr><td>" + buyerID + "</td>";
				 output += "<td>" + buyerName + "</td>"; 
				 output += "<td>" + projectName + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + contactNo + "</td>"; 
				 output += "<td>" + researcherName + "</td>";  					 
				 
				 
				 // buttons
				/* output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" 
				 + buyerID + "'>" + "</td></tr>"; */
				 
				
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ buyerID +"' >Remove</button></td></tr>";


			 } 
			 	 con.close(); 
			 	 // Complete the html table
			 	 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the buyer"; 
			 System.out.println(e.getMessage());
				System.out.println(e);
				e.printStackTrace();
		 } 
	 	 return output; 
	 }
	 
	 public String updateBuyer(String buyerID, String buyerName, String projectName, String email, String contactNo , String researcherName)
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 
				 // create a prepared statement
				 String query = "UPDATE buyer_table_gui SET buyerName=? , projectName=? , email=? , contactNo=? , researcherName=?   WHERE buyerID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setString(1, buyerName); 
				 preparedStmt.setString(2, projectName); 
				 preparedStmt.setString(3, email);  
				 preparedStmt.setString(4, contactNo); 
				 preparedStmt.setString(5, researcherName); 
				 preparedStmt.setInt(6, Integer.parseInt(buyerID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newBuyer = readBuyer(); output = "{\"status\":\"success\", \"data\": \"" + newBuyer + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the buyer.\"}"; 
				 System.out.println(e.getMessage());
					System.out.println(e);
					e.printStackTrace(); 
			 } 
			 	return output; 
			 } 
	 
	//Delete Buyer
	 public String deleteBuyer(String buyerID) 
	 { 
		 String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for deleting."; 
	 } 
	 
	 	 // create a prepared statement
		 String query = "DELETE FROM buyer_table_gui WHERE buyerID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(buyerID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newBuyer = readBuyer(); output = "{\"status\":\"success\", \"data\": \"" + newBuyer + "\"}";
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the buyer.\"}"; 
		 System.out.println(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
	 } 
	 return output; 
} 

}
