package implementation;

import com.openlogix.Rest.DB.MySql;

public class UserDAO {
	
	public UserDAO() {
		
	}
	
	public String getUsers() throws Exception {
		
		//MySql mysql=new MySql("localhost",1433,"VGNET\sateesh.paluri","","TrainingDB");
		MySql mysql=new MySql("127.0.0.1",3306,"root","root","training");
		mysql.connection();
		String data=" ";
		data=mysql.performQuery("Select * from Users");
		return data;	
		
	}
}