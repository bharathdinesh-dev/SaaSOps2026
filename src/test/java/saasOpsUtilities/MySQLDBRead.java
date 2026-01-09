package saasOpsUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import saasOpsTestBase.SaasOpsBaseClass;

public class MySQLDBRead {

    public static void main(String[] args) {
    	MySQLDBRead obj = new MySQLDBRead();
    	System.out.println(obj.mfaGetter("2026-01-08 14:46:52","dinesh@corenttech.com","jdbc:mysql://10.10.44.57:3306/ss465","root","MySQL123$$"));
    }
    
    public String mfaGetter(String datee,String email,String dburl,String username,String password) {
    	
    	String mfaCode=null;
        String query = "SELECT VERIFICATIONCODE " +
                       "FROM c_identitycode " +
                       "WHERE USERNAME=? AND TYPE='Forget' AND STATUS='Active' AND CREATED_DATE >? " +
                       "ORDER BY CREATED_DATE DESC LIMIT 1";

        try {
            // Load MySQL Driver
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
                System.out.println("MFA Code: " + mfaCode);
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
        }return mfaCode;
    }
}

