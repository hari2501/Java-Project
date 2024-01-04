package com.secure.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.secure.Database.Database;
import com.secure.Interface.Interface;
import com.secure.bean.Register;

public class Implementation implements Interface{

	Connection con;

	@Override
	public int datasharingRegister(Register datareg) {


		int result = 0;

		try {

			con = Database.createConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO datasharingregister VALUES (?,?,?,?)");
			ps.setInt(1, datareg.getReg_id());
			ps.setString(2, datareg.getUsername());
			ps.setString(3, datareg.getEmail());
			ps.setString(4, datareg.getPassword());


			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		return result;

	}

	@Override
	public int datasharingLogin(String username, String password) {

		int result = 0;

		try {
			con = Database.createConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * from datasharingregister where username='"+username+"' and password='"+password+"'");
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				String user = rs.getString("username");
				String pass = rs.getString("password");

				if(username.equals(user) && password.equals(pass))
				{
					result = 1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return result;
	}

	@Override
	public int datareceiverRegister(Register dataReceiverRegister) {

		int result = 0;

		try { 
			con = Database.createConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO dataReceiverRegister VALUES(?,?,?,?)");
			ps.setInt(1, dataReceiverRegister.getReg_id());
			ps.setString(2, dataReceiverRegister.getUsername());
			ps.setString(3, dataReceiverRegister.getEmail());
			ps.setString(4, dataReceiverRegister.getPassword());
			
			result = ps.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}


		return result;
	}

	@Override
	public int datareciverLogin(String username, String password) {

		int result = 0;

		try {
			con = Database.createConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * from datareceiverregister where username='"+username+"' and password='"+password+"'");
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				String user = rs.getString("username");
				String pass = rs.getString("password");

				if(username.equals(user) && password.equals(pass))
				{
					result = 1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return result;
	}



}
