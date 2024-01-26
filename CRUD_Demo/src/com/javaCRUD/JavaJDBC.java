/**
 * 
 */
package com.javaCRUD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 */



/*
 * C -CREATE  ****INSERT****
 * R -READ	  ****SELECT****
 * U -UPDATE  ****UPDATE****
 * D -DELETE  ****DELETE****
 * */


public class JavaJDBC {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {

		Connection conn=null;
		final String url="jdbc:mysql://localhost:3306/javajdbc";
		final String username="YourUsername";
		final String password="YourPassword";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, username, password);
			
			File file=new File("EmployeeData.txt");
			BufferedReader buffer=new BufferedReader(new FileReader(file));
			String st;
			while((st=buffer.readLine())!=null) {
				String[] data=st.split(",");
				int id=Integer.parseInt(data[0]);
				String name=data[1];
				String dept=data[2];
				long Salary=Long.parseLong(data[3]);
				
				// CREATE//
				String sqlInsert="insert into employee values(?,?,?,?)";
				PreparedStatement stmt=conn.prepareStatement(sqlInsert);
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setString(3, dept);
				stmt.setLong(4, Salary);
				stmt.executeUpdate();
				
				stmt.close();
				
			}
			
			// READ //
			
			
			String readSql="select * from employee";
			PreparedStatement stmt=conn.prepareStatement(readSql);
			ResultSet result= stmt.executeQuery();
			while(result.next()) {
			int id=	result.getInt(1);
			String name=	result.getString(2);
			String dept=	result.getString(3);
			long salary=	result.getLong(4);
			System.out.println(id+","+name+","+dept+","+salary);
			}
			
			stmt.close();
			
			// UPDATE //
			int uid=4;
			String updatedName="Uplar";
			String udept="VP";
			long usalary=6789084;
			
			String updateSql="update employee set emp_name=?,emp_dept=?,emp_salary=? where emp_id=?";
			
			PreparedStatement ustmt=conn.prepareStatement(updateSql);
			ustmt.setInt(4, uid);
			ustmt.setString(1, updatedName);
			ustmt.setString(2, udept);
			ustmt.setLong(3, usalary);
			ustmt.executeUpdate();
			ustmt.close();
			
			// DELETE //
			int did=9;
			
			String deleteSql="Delete  from employee where emp_id=?";
			
			PreparedStatement dstmt=conn.prepareStatement(deleteSql);
			dstmt.setInt(1, did);
			
			dstmt.executeUpdate();
			dstmt.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		finally {
		
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
	
			
		
	}

}
