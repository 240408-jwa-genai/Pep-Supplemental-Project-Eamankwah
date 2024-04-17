package com.revature.controller;

import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.service.MoonService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public void getAllMoons(int currentUserId) {
		// TODO: implement
		try {
			List<Moon> allMoons = moonService.getAllMoons(currentUserId);
			if(allMoons.size() > 0){
				System.out.println("------ Your Moons ------");
				allMoons.forEach(System.out::println);
			}
			else{
				throw new MoonFailException("You have no Moons in your Planetarium");
			}
		}catch (MoonFailException e){
			System.out.println(e.getMessage());
		}
	}

	public void getMoonByName(int currentUserId, String name) {
		// TODO: implement
	}

	public void getMoonById(int currentUserId, int id) {
		// TODO: implement
	}

	public void createMoon(int currentUserId, Moon moon) {
		try {
			Moon createdMoon = moonService.createMoon(moon);
			if(createdMoon != null){
				System.out.printf("\n%s was successfully created!",createdMoon.getName());
			}
			else{
				throw new MoonFailException("Error occured, try again.");
			}
		}
		catch (MoonFailException e){
			System.out.println(e.getMessage());
		}

	}

	public void deleteMoon(int id) {
		try {
			boolean moonDeleted = moonService.deleteMoonById(id);
			if(moonDeleted){
				System.out.printf("Moon with id of %d, was removed successfully",id);
			}
			else{
				throw new MoonFailException("Moon was not removed successfully, check id");
			}
		}
		catch (MoonFailException e){
			System.out.println(e.getMessage());
		}

	}
	
	public void getPlanetMoons(int myPlanetId) {
		// TODO: implement
	}
}
