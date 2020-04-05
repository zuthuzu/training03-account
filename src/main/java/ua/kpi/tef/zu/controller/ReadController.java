package ua.kpi.tef.zu.controller;

import ua.kpi.tef.zu.model.Model;
import ua.kpi.tef.zu.view.View;

import java.sql.*;

/**
 * Created by Anton Domin on 2020-04-04
 */
public class ReadController {
	private Model model;
	private View view;

	public ReadController(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void startRecordOutput() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "masterkey");
			Statement query = con.createStatement();
			ResultSet rs = query.executeQuery("SELECT * FROM country");
			while(rs.next()) {
				System.out.println(rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
