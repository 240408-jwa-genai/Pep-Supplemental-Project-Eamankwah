package com.revature.controller;

import com.revature.exceptions.PlanetFailException;
import com.revature.models.Planet;
import com.revature.service.PlanetService;

import java.util.List;

public class PlanetController {
	
	private PlanetService planetService;

	public PlanetController(PlanetService planetService){
		this.planetService = planetService;
	}

	public void getAllPlanets(int currentUserId) {
		// TODO: implement
		try {
			List<Planet> allPlanets = planetService.getAllPlanets(currentUserId);
			if(allPlanets.size() > 0) {
				System.out.println("\nYour Planets\n-----------------");
				allPlanets.forEach(System.out::println);
			}
			else{
				throw new PlanetFailException("\nYou have no planets in your Planetarium");
			}
		}catch (PlanetFailException e){
			System.out.println(e.getMessage());
		}

	}

	public void getPlanetByName(int currentUserId, String name) {
		try {
			Planet planet = planetService.getPlanetByName(currentUserId, name);
			if(planet == null){
				throw new PlanetFailException("Double check planet name and try again");
			}
			else System.out.println(planet);
		}
		catch (PlanetFailException e){
			System.out.println(e.getMessage());
		}

	}

	public Planet getPlanetByID(int currentUserId, int id) {
		// TODO: implement
		try {
			Planet checkPlanet = planetService.getPlanetById(currentUserId, id);
			if(checkPlanet != null){
				//if planet exists
				return checkPlanet;
			}
			else{
				throw new PlanetFailException("Error! Please double check planet id");
			}
		}
		catch (PlanetFailException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void createPlanet(int currentUserId, Planet planet) {
		// TODO: implement
		try {
			Planet planet1 = planetService.createPlanet(currentUserId, planet);
			if (planet1 != null) {
				System.out.printf("\n%s was successfully created", planet1.getName());
			}
			else {
				throw  new PlanetFailException("There's was an error adding your planet. Please try again\n");
			}
		}catch (PlanetFailException e){
			System.out.println(e.getMessage());
		}
	}

	public void deletePlanet(int currentUserId, int id) {
		// TODO: implement
		try {
			boolean rowDeleted = planetService.deletePlanetById(id);
			if (rowDeleted) {
				System.out.printf("\nPlanet with id of %d was successfully deleted",id);
			}
			else{
				throw new PlanetFailException("Planet removal unsuccessful, double check id\n");
			}
		}catch (PlanetFailException e){
			System.out.println(e.getMessage());
		}

	}
}
