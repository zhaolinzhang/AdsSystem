package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLAdsTableCreation {
	public static void main(String[] args) {
		// connect to mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
			
			if (conn == null) {
				return;
			}
			
			// drop existing table
			Statement stmt = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS ad";
			stmt.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS advertiser";
			stmt.executeUpdate(sql);
			
			// create new table
			// create advertiser table
			sql = "CREATE TABLE advertiser ("
					+ "advertiser_id int NOT NULL AUTO_INCREMENT,"
					+ "name VARCHAR(255),"
					+ "budget FLOAT,"
					+ "PRIMARY KEY (advertiser_id)"
					+ ")";
			
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			// create ad table
			sql = "CREATE TABLE ad ("
					+ "ad_id int NOT NULL AUTO_INCREMENT,"
					+ "bid FLOAT,"
					+ "image_url VARCHAR(2083),"
					+ "advertiser_id int NOT NULL,"
					+ "ad_score float,"
					+ "PRIMARY KEY (ad_id),"
					+ "FOREIGN KEY (advertiser_id) REFERENCES advertiser(advertiser_id)"
					+ ")";
			
			System.out.println(sql);
			stmt.executeUpdate(sql);
			System.out.println("Import done successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
