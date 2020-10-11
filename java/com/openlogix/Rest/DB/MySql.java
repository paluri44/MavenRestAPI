package com.openlogix.Rest.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import implementation.User;

public class MySql {
	private String hostName;
	private Integer port;
	private String userName;
	private String password;
	private String dbName;
	private String url;
	private Connection connector=null;
	
	
	public MySql(String hostName,Integer port, String userName,String password,String dbName) {
		super();
		this.hostName=hostName;
		this.port=port;
		this.userName=userName;
		this.password=password;
		this.dbName=dbName;
		this.url= "jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.dbName+ "?serverTimeZone=UTC";
		//this.url= "jdbc:sqlserver://" + this.hostName + ":" + this.port + "/" + this.dbName;
	}

	public void connection() {
		
		//Register the driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver has been Registered");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connector=DriverManager.getConnection(this.url,this.userName,this.password);  //create connection
	      	System.out.println("Connetced to Sql");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	    private String toJSON(ArrayList<User> users) {
	    	ObjectMapper mapper=new ObjectMapper();
	    	String json="";
	    	try {
				json=mapper.writeValueAsString(users);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;	
	    }
	
	    public String performQuery(String query) throws Exception {
	    	if(this.connector==null) {
	    	System.out.println("connection is not initialized ");
	    	throw new Exception("exception");
	    	}
	    	else {	
	    	 System.out.println("connection is initialized");
				ArrayList<User> users=new ArrayList<User>();
				Statement stmt = this.connector.createStatement();
				ResultSet rs;
				rs = stmt.executeQuery(query);
	    	 
			try {
				
	    		
				while(rs.next()) {
					 int id=rs.getInt("id");
					 String firstName=rs.getString("firstName");
					 String lastName=rs.getString("lastName");
					 int age=rs.getInt("age");
					 User user=new User(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getInt("age"));	 
					 users.add(user);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 return this.toJSON(users);
	    		 
	    	 }       
	    	
	    }

		public String getHostName() {
			return hostName;
		}

		public void setHostName(String hostName) {
			this.hostName = hostName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getDbName() {
			return dbName;
		}

		public void setDbName(String dbName) {
			this.dbName = dbName;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	  	    
}
	
	
	

