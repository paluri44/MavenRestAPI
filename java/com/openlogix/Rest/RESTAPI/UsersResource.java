package com.openlogix.Rest.RESTAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import implementation.UserDAO;

@Path("user")
public class UsersResource{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() throws Exception {
    	UserDAO userInfo=new UserDAO();
    	String output=userInfo.getUsers();
        return output;
    }
}
