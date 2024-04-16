package com.revature.service;

import java.sql.SQLOutput;
import java.util.List;

import com.revature.exceptions.PlanetFailException;
import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets(int ownerId) {
		// TODO Auto-generated method stub
		List<Planet> allPlanets=dao.getAllPlanets(ownerId);

		if(allPlanets.size() == 0){
			System.out.println("You have no planets in your Planetarium");
		}
		else{
			System.out.println("\nYour Planets\n-----------------");
			allPlanets.forEach(System.out::println);
		}
		return null;
	}

	public Planet getPlanetByName(int ownerId, String planetName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet createPlanet(int ownerId, Planet planet) {
		// TODO Auto-generated method stub
		try {
			if (planet.getName().length() < 30) {
				Planet checkPlanet= dao.getPlanetByName(planet.getName());
				if(!planet.getName().equals(checkPlanet.getName())) {
					planet.setOwnerId(ownerId);
					Planet newPlanet = dao.createPlanet(planet);
					return newPlanet;
				}
				else{
					throw new PlanetFailException("\nThis planet name is taken.");
				}
			}//if
			else{
				throw new PlanetFailException("\nYour planet's name exceeds 30 characters");
			}
		}
		catch(PlanetFailException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public boolean deletePlanetById(int planetId) {
		// TODO Auto-generated method stub
		boolean rowDeleted= dao.deletePlanetById(planetId);
		return rowDeleted;
	}
}
