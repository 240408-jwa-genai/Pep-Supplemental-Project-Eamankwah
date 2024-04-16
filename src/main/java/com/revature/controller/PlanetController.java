package com.revature.controller;

import com.revature.exceptions.PlanetFailException;
import com.revature.models.Planet;
import com.revature.service.PlanetService;

public class PlanetController {
	
	private PlanetService planetService;

	public PlanetController(PlanetService planetService){
		this.planetService = planetService;
	}

	public void getAllPlanets(int currentUserId) {
		// TODO: implement
		planetService.getAllPlanets(currentUserId);

	}

	public void getPlanetByName(int currentUserId, String name) {
		// TODO: implement
	}

	public void getPlanetByID(int currentUserId, int id) {
		// TODO: implement
	}

	public void createPlanet(int currentUserId, Planet planet) {
		// TODO: implement
		try {
			Planet planet1 = planetService.createPlanet(currentUserId, planet);
			if (planet1 != null) {
				System.out.printf("\n%s was successfully created", planet1.getName());
			}
			else {
				throw  new PlanetFailException("\nThere's was an error adding your planet. Please try again");
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
				throw new PlanetFailException("Planet removal unsuccessful, double check id");
			}
		}catch (PlanetFailException e){
			System.out.println(e.getMessage());
		}

	}
}
