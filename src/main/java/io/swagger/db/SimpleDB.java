package io.swagger.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import io.swagger.model.ArrayofOwnerContact;
import io.swagger.model.ArrayofPetLocations;
import io.swagger.model.PetLocation;
import io.swagger.model.PetOwner;

public class SimpleDB {

	 //create an object of SimpleDB Singleton object
	   private static SimpleDB instance = new SimpleDB();
	
	  
	   //Get the only object available
	   public static SimpleDB getInstance(){
	      return instance;
	   }
	   
	private String protocol = "jdbc:derby:";
	Connection conn = null;
	ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
	
	PreparedStatement psUpdate;
	Statement s;
	ResultSet rs = null;

	private SimpleDB() {
		try {
			Properties props = new Properties(); // connection properties
			// providing a user name and password is optional in the embedded
			// and derbyclient frameworks
			Random rand = new Random(); 
			int rndNum = rand.nextInt(99);
			
			props.put("user", "user1"+rndNum);
			props.put("password", "user1"+rndNum);
			
			//Generate new DB everytime initialzing 
			
			String dbName = "derbyDB"+ rndNum;
			
			
			conn = DriverManager.getConnection(protocol + dbName + ";create=true", props);

			System.out.println("Connected to and created database " + dbName);

			s = conn.createStatement();
			statements.add(s);
			
			/* s.execute("drop table petlocation");
			 s.execute("drop table petowner");
	         System.out.println("Dropped table location");
*/

			// We create a table...
			s.execute("create table petlocation( petId varchar(40), langitude varchar(40), lattitude varchar(40), timestamp varchar(40) )");
			System.out.println("Created table petlocation");
			
			s.execute("create table petowner( ownerId varchar(40), ownerName varchar(40), street varchar(40), city varchar(40), state varchar(40), zip varchar(40), phone varchar(40), petid varchar(40) )");
			System.out.println("Created table petowner");
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void addPetLocationRecord(PetLocation petlocation) {

		PreparedStatement psInsert;
		try {
			psInsert = conn.prepareStatement("insert into petlocation values (?, ?, ?, ?)");
			statements.add(psInsert);

			psInsert.setString(1, petlocation.getPetId());
			psInsert.setString(2, petlocation.getLangitude());
			psInsert.setString(3, petlocation.getLattitude());

			psInsert.setString(4, petlocation.getTimestamp().toString());
			psInsert.executeUpdate();
			
			//storing random owner details to help with search
			
			createPetOwner(petlocation.getPetId());
			System.out.println("Pet Location Inserted");
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("Inserted location " + petlocation.getPetId());
	}

	private void createPetOwner(String petId) {
		
		PreparedStatement psInsert1;
		try {
		psInsert1 = conn.prepareStatement("insert into petowner values (?, ?, ?, ?, ?, ?, ?, ?)");
		statements.add(psInsert1);
		
		Random rand = new Random(); 
		int rndNum = rand.nextInt(99);
		psInsert1.setString(1, petId +"-"+ rndNum);
		psInsert1.setString(2, petId +"-"+ rndNum);
		psInsert1.setString(3, "Test Street "+"-"+ rndNum);
		psInsert1.setString(4, "Test City "+"-"+ rndNum);
		psInsert1.setString(5, "Test State "+"-"+ rndNum);
		psInsert1.setString(6, "Test zip "+"-"+ rndNum);
		psInsert1.setString(7, "Test phone "+"-"+ rndNum);
		psInsert1.setString(8, petId);
		psInsert1.executeUpdate();
		System.out.println("Pet Owner Inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public ArrayofPetLocations getPetLocationHistory(String petId) {
	      
		ArrayofPetLocations arr = new ArrayofPetLocations();
		try {
	        	
	        	rs = s.executeQuery(
	                    "SELECT petId, langitude, lattitude,  timestamp FROM petlocation where petid = '"+ petId +"'");

				while (rs.next())
				{
				 
					PetLocation petlocation = new PetLocation();
					petlocation.setPetId(rs.getString(1));
					petlocation.setLangitude(rs.getString(2));
					petlocation.setLattitude(rs.getString(3));
					
					arr.add(petlocation);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return arr;

	   }
	
	public ArrayofOwnerContact getPetOwnerContact(String petId) {
	      
		ArrayofOwnerContact arr = new ArrayofOwnerContact();
		try {
	        	
	        	rs = s.executeQuery(
	                    "SELECT ownerId , ownerName , street , city , state , zip , phone , petid from petowner where petid like '%" + petId + "%'");

				while (rs.next())
				{
				 
					PetOwner petowner = new PetOwner();
					petowner.setOwnerId(rs.getString(1));
					petowner.setOwnerName(rs.getString(2));
					petowner.setStreet(rs.getString(3));
					petowner.setCity(rs.getString(4));
					petowner.setState(rs.getString(5));
					petowner.setZip(rs.getString(6));
					petowner.setPhone(rs.getString(7));
					petowner.setPetId(rs.getString(8));
					
					
					arr.add(petowner);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return arr;

	   }
	
	public void displayRecord() {
       try {
        	
        	rs = s.executeQuery(
                    "SELECT petId, langitude, lattitude,  timestamp FROM petlocation ORDER BY petId");

			while (rs.next())
			{
			    System.out.println("PetId " + rs.getString(1));
			    System.out.println("langitude " + rs.getString(2));
			    System.out.println("lattitude" + rs.getString(3));
			    System.out.println("timestamp " + rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   }
}
