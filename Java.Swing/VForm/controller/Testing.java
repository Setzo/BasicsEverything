package controller;

import java.sql.SQLException;

import data.Database;

public class Testing {
	public static void main(String[] args) {
		
		Database db = new Database();
		
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			db.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.disconnect();
	}
}
