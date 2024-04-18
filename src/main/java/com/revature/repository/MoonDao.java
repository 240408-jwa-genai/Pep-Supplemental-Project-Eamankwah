package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;
import com.sun.source.tree.WhileLoopTree;

public class MoonDao {
    
    public List<Moon> getAllMoons(int ownerId) {
		// TODO: implement
		try(Connection conn= ConnectionUtil.createConnection()) {
			String sql="SELECT m.id ,m.name,m.myPlanetId \n" +
					"FROM users u \n" +
					"join planets p on u.id =p.ownerId \n" +
					"join moons m on p.id = m.myPlanetId \n" +
					"WHERE u.id = ? ";

			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setInt(1,ownerId);

			ResultSet resultSet= ps.executeQuery();

			List<Moon> allMoons= new ArrayList<>();
			while(resultSet.next()){
				Moon moon= new Moon();
				moon.setId(resultSet.getInt(1));
				moon.setName(resultSet.getString(2));
				moon.setMyPlanetId(resultSet.getInt(3));

				allMoons.add(moon);
			}
			return allMoons;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Moon getMoonByName(String moonName) {
		// TODO: implement
		try(Connection conn= ConnectionUtil.createConnection()) {
			String sql="Select * from moons WHERE name = ?";

			PreparedStatement preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1,moonName);

			Moon returnedMoon= new Moon();
			ResultSet resultSet=preparedStatement.executeQuery();

			if(resultSet.next()){
				returnedMoon.setId(resultSet.getInt(1));
				returnedMoon.setName(resultSet.getString(2));
				returnedMoon.setMyPlanetId(resultSet.getInt(3));

			}
			return  returnedMoon;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Moon getMoonById(int moonId) {
		try(Connection conn=ConnectionUtil.createConnection()){
			String sql= "Select * FROM moons WHERE id = ?";

			PreparedStatement preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1,moonId);

			Moon moon = new Moon();

			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()){
				moon.setId(resultSet.getInt(1));
				moon.setName(resultSet.getString(2));
				moon.setMyPlanetId(resultSet.getInt(3));
			}

			return moon;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Moon createMoon(Moon m) {
		// TODO: implement
		try(Connection conn= ConnectionUtil.createConnection()) {
			String sql="INSERT INTO moons (name, myPlanetId) VALUES (?,?)";

			PreparedStatement preparedStatement= conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,m.getName());
			preparedStatement.setInt(2,m.getMyPlanetId());

			preparedStatement.executeUpdate();

			ResultSet resultSet=preparedStatement.getGeneratedKeys();

			Moon createdMoon= new Moon();
			createdMoon.setName(m.getName());
			createdMoon.setMyPlanetId(m.getMyPlanetId());
			if(resultSet.next()){
				createdMoon.setId(resultSet.getInt(1));
			}
			return createdMoon;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteMoonById(int moonId) {
		// TODO: implement
		try(Connection conn=ConnectionUtil.createConnection()) {
			String sql="DELETE FROM moons WHERE id = ?";

			PreparedStatement preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1,moonId);

			int rowsUpdated= preparedStatement.executeUpdate();

			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		try(Connection conn= ConnectionUtil.createConnection()){
			String sql= "SELECT m.id,m.name ,m.myPlanetId  FROM moons m \n" +
					"join planets p on m.myPlanetId = p.id \n" +
					"WHERE m.myPlanetId = ?";

			PreparedStatement preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, planetId);

			List<Moon> allMoons= new ArrayList<>();
			ResultSet resultSet=preparedStatement.executeQuery();

			while(resultSet.next()){
				Moon moon= new Moon();
				moon.setId(resultSet.getInt(1));
				moon.setName(resultSet.getString(2));
				moon.setMyPlanetId(resultSet.getInt(3));

				allMoons.add(moon);
			}

			return allMoons;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
