package com.revature.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
    public List<Planet> getAllPlanets(int ownerId) {
		// TODO: implement
		List<Planet> planets= new ArrayList<>();

		try(Connection conn= ConnectionUtil.createConnection()){
			String sql= "Select * from planets where ownerId = ?";
			PreparedStatement preparedStatement= conn.prepareStatement(sql);

			preparedStatement.setInt(1,ownerId);

			ResultSet resultSet= preparedStatement.executeQuery();

			while(resultSet.next()){
				Planet planet= new Planet();
				planet.setId(resultSet.getInt(1));
				planet.setName(resultSet.getString(2));
				planet.setOwnerId(resultSet.getInt(3));

				planets.add(planet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return planets;
	}

	public Planet getPlanetByName(String planetName) {
		// TODO: implement
		try(Connection conn= ConnectionUtil.createConnection()){
			String sql= "SELECT * FROM planets WHERE name = ?";

			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,planetName);

			ResultSet resultSet= ps.executeQuery();
			Planet planet=new Planet();
			if(resultSet.next()){
				planet.setId(resultSet.getInt(1));
				planet.setName(resultSet.getString(2));
				planet.setOwnerId(resultSet.getInt(3));
			}
			return planet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Planet getPlanetById(int planetId) {
		// TODO: implement
		try(Connection conn= ConnectionUtil.createConnection()) {
			String sql= "SELECT * FROM planets WHERE id = ?";

			PreparedStatement preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1,planetId);

			Planet returnedPlanet= new Planet();
			ResultSet resultSet=preparedStatement.executeQuery();

			if(resultSet.next()){
				returnedPlanet.setId(resultSet.getInt(1));
				returnedPlanet.setName(resultSet.getString(2));
				returnedPlanet.setOwnerId(resultSet.getInt(3));
			}
			return returnedPlanet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Planet createPlanet(Planet p) {
		// TODO: implement
		try(Connection conn= ConnectionUtil.createConnection()){
			String sql= "INSERT INTO planets(name, ownerId) VALUES (?,?)";

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // the second argument tells the database to return the id that is generated for the new record
			ps.setString(1,p.getName());
			ps.setInt(2,p.getOwnerId());

			ps.executeUpdate();

			ResultSet rs=ps.getGeneratedKeys();

			Planet newPlanet= new Planet();
			newPlanet.setName(p.getName());
			newPlanet.setOwnerId(p.getOwnerId());

			if(rs.next()){
				newPlanet.setId(rs.getInt(1));
			}
			return newPlanet;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean deletePlanetById(int planetId) {
		// TODO: implement
		try(Connection conn=ConnectionUtil.createConnection()){
			String sql="DELETE FROM planets WHERE id= ?";

			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setInt(1,planetId);

			int rowsaffected= ps.executeUpdate();

			return rowsaffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
