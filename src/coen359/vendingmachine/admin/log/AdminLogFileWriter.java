package coen359.vendingmachine.admin.log;
import java.io.*;
import java.sql.*;
import java.util.*;

public class AdminLogFileWriter {	
	
	    public static void main(String[] args) {
	    	List<String> data = new ArrayList<String>();
	    	String path = "C:\\Users\\FarzaShereef\\workspace\\vendingmachine1\\src\\coen359\\vendingmachine\\resources\\LogFile.txt";
	    	File file = new File(path);
	    	File parent_directory = file.getParentFile();
	    	if (parent_directory != null) {
	    	   parent_directory.mkdirs();
	    	}
	            try {
	                    Connection con = null;
	                    Class.forName("com.mysql.jdbc.Driver");
	                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine", "root", "admin");
	                    Statement st = con.createStatement();
	                    ResultSet rs = st.executeQuery("Select user_name, date from Admin_Log order by date desc");
	                    

	                    while (rs.next()) {
	                            //String id = rs.getString("id");
	                            String userName = rs.getString("user_name");
	                            String date = rs.getString("date");
	                            data.add(userName + "              " + date);

	                    }
	                    writeToFile(data, path);
	                    rs.close();
	                    st.close();
	            } catch (Exception e) {
	                    System.out.println(e);
	            }
	    }

	    private static void writeToFile(java.util.List<String> list, String path) {
	            BufferedWriter out = null;
	            try {
	                    File file = new File(path);
	                    out = new BufferedWriter(new FileWriter(file, true));
	                    for (String s : list) {
	                            out.write(s);
	                            out.newLine();

	                    }
	                    out.close();
	            } catch (IOException e) {
	            }
	    }
}
