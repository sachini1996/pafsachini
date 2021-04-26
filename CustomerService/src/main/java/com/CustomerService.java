package com;

import model.Customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Buyer")
public class CustomerService {
	Customer customerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBuyer() 
	{
		return customerObj.readBuyer();
	}
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomer(
	 @FormParam("FName") String FName, 
	 @FormParam("LName") String LName, 
	 @FormParam("Address") String Address, 
	 @FormParam("Country") String Country,
	 
	 @FormParam("City") String City,
	 @FormParam("PNo") String PNo,
	 @FormParam("PostalCode") String PostalCode,
	 @FormParam("Email") String Email,
	 @FormParam("Password") String Password,
	 @FormParam("CreditCardID") String CreditCardID,
	 @FormParam("BillAddress") String BillAddress,
	 @FormParam("ShipAddress") String ShipAddress) 
	{ 
	 String output = customerObj.insertCustomer(FName, LName, Address, Country, City, PNo, PostalCode, Email, Password,CreditCardID, BillAddress, ShipAddress); 
	return output; 
	}
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String customerData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String CID = customerObject.get("CID").getAsString(); 
	 String FName = customerObject.get("FName").getAsString(); 
	 String LName = customerObject.get("LName").getAsString(); 
	 String Address = customerObject.get("Address").getAsString(); 
	 String Country = customerObject.get("Country").getAsString();
	 String City = customerObject.get("City").getAsString();
	 String PNo = customerObject.get("PNo").getAsString();
	 String PostalCode = customerObject.get("PostalCode").getAsString();
	 String Email = customerObject.get("Email").getAsString();
	 String Password = customerObject.get("Password").getAsString();
	 String CreditCardID = customerObject.get("CreditCardID").getAsString();
	 String BillAddress = customerObject.get("BillAddress").getAsString();
	 String ShipAddress = customerObject.get("ShipAddress").getAsString();
	 String output = customerObj.updateCustomer(CID, FName, LName, Address, Country, City, PNo, PostalCode,  
			  Email, Password, CreditCardID, BillAddress, ShipAddress); 
	return output; 
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String customerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String CID = doc.select("CID").text(); 
	 String output = customerObj.deleteCustomer(CID); 
	return output; 
	}
}
