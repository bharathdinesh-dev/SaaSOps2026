package saasOpsUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.testng.annotations.Test;

public class MfaGetter {
	@Test
	public void excecutor() {
		System.out.println(mfaGetter("2026-01-29","dinesh@corenttech.com","jdbc:mysql://10.10.44.52:3306/ss447","root","MySQL123$$"));
		
	}

	public String mfaGetter(String datee,String email,String dburl,String username,String password) {
    	
		String mfaCode = null;
	    String accountId = null;

	    String query = "SELECT VERIFICATIONCODE, ACCOUNTID " +
	                   "FROM c_identitycode " +
	                   "WHERE USERNAME=? AND TYPE='Forget' AND STATUS='Active' AND CREATED_DATE >? " +
	                   "ORDER BY CREATED_DATE DESC LIMIT 1";

	    String updateQuery = "UPDATE c_users SET mfaenabled='false' WHERE AccountID = ?";

	    try {
	        // Load MySQL Driver mfaenabled
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Create Connection
	        Connection con = DriverManager.getConnection(dburl, username, password);

	        // Prepare Statement
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, email);
	        ps.setString(2, datee);

	        // Execute Query
	        ResultSet rs = ps.executeQuery();

	        // Read Result
	        if (rs.next()) {
	        	
	            mfaCode = rs.getString("VERIFICATIONCODE");
	            accountId = rs.getString("ACCOUNTID");

	            System.out.println("MFA Code: " + mfaCode);
	            System.out.println("Account ID: " + accountId);

	            /* --------- NEW EDIT FUNCTIONALITY START --------- */
	            PreparedStatement updatePs = con.prepareStatement(updateQuery);
	            updatePs.setString(1, accountId);
	            int updatedRows = updatePs.executeUpdate();

	            System.out.println("MFAenabled updated rows: " + updatedRows);
	            updatePs.close();
	            /* --------- NEW EDIT FUNCTIONALITY END --------- */
	        } 
	        else {
	            System.out.println("No record found");
	        }

	        // Close connections
	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return mfaCode;
    }
}
