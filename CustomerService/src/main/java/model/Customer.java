package model;

import java.sql.*;

public class Customer { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, Username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertCustomer(String fname, String lname, String address, String country, String city,
			String pno, String postalcode, String email, String password, String creditcardid, String billaddress,
			String shipaddress) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into buyer ('CID','FName','LName','Address','Country','City','PNo','PostalCode','Email','Password','CreditCardID','BillAddress','ShipAddress')"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, fname); 
	 preparedStmt.setString(3, lname); 
	 preparedStmt.setString(4, address); 
	 preparedStmt.setString(5, country);
	 preparedStmt.setString(6, city); 
	 preparedStmt.setInt(7, Integer.parseInt(pno)); 
	 preparedStmt.setInt(8, Integer.parseInt(postalcode)); 
	 preparedStmt.setString(9, email); 
	 preparedStmt.setString(10, password); 
	 preparedStmt.setInt(11, Integer.parseInt(creditcardid)); 
	 preparedStmt.setString(12, billaddress); 
	 preparedStmt.setString(13, shipaddress); 
	 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the customer."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

	public String readBuyer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>First Name</th><th>Last Name</th>" + "<th>Address</th>"
					+ "<th>Country</th>" + "<th>City</th>" + "<th>Phone NO</th>" + "<th>Postal Code</th>"
					+ "<th>Email</th>" + "<th>Password</th>" + "<th>Credit Card ID</th>" + "<th>Bill Address</th>"
					+ "<th>Ship Address</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from buyer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String CID = Integer.toString(rs.getInt("CID"));
				String FName = rs.getString("FName");
				String LName = rs.getString("LName");
				String Address = rs.getString("Address");
				String Country = rs.getString("Country");
				String City = rs.getString("City");
				String PNo = Integer.toString(rs.getInt("PNo"));
				String PostalCode = Integer.toString(rs.getInt("PostalCode"));
				String Email = rs.getString("Email");
				String Password = rs.getString("Password");
				String CreditCardID = Integer.toString(rs.getInt("CreditCardID"));
				String BillAddress = rs.getString("BillAddress");
				String ShipAddress = rs.getString("ShipAddress");
				// Add into the html table
				output += "<tr><td>" + FName + "</td>";
				output += "<td>" + LName + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Country + "</td>";
				output += "<td>" + City + "</td>";
				output += "<td>" + PNo + "</td>";
				output += "<td>" + PostalCode + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Password + "</td>";
				output += "<td>" + CreditCardID + "</td>";
				output += "<td>" + BillAddress + "</td>";
				output += "<td>" + ShipAddress + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='Customer.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='CID' type='hidden' value='" + CID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the buyer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String ID, String fname, String lname, String address, String country, String city,
			String pno, String postalcode, String email, String password, String creditcardid, String billaddress,
			String shipaddress) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE buyer SET FName=?,LName=?,Address=?,Country=?,City=?,PNo=?,PostalCode=?,Email=?,Password=?,CreditCardID=?,BillAddress=?,ShipAddress=? WHERE CID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, country);
			preparedStmt.setString(5, city);
			preparedStmt.setInt(6, Integer.parseInt(pno));
			preparedStmt.setInt(7, Integer.parseInt(postalcode));
			preparedStmt.setString(8, email);
			preparedStmt.setString(9, password);
			preparedStmt.setInt(10, Integer.parseInt(creditcardid));
			preparedStmt.setString(11, billaddress);
			preparedStmt.setString(12, shipaddress);
			preparedStmt.setInt(13, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCustomer(String CID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from buyer where CID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(CID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
