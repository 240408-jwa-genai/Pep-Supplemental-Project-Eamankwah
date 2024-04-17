package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons(int ownerId) {
		try{
			List<Moon> allMoons= dao.getAllMoons(ownerId);
			if(allMoons != null){
				return allMoons;
			}
			else{
				throw new MoonFailException("There was an error retrieving your moons");
			}
		}catch(MoonFailException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Moon getMoonByName(int myPlanetId, String moonName) {
		try{
			Moon moon= dao.getMoonByName(moonName);
			if(moon.getId() > 0){
				return moon;
			}
			else{
				throw new MoonFailException("Moon doesn't exist");
			}

		}catch(MoonFailException e){
			//System.out.println(e.getMessage());
			return null;
		}
	}

	public Moon getMoonById(int myPlanetId, int moonId) {
		// TODO Aimplement
		return null;
	}

	public Moon createMoon(Moon m) {
		// TODO implement
		try{
			if(m.getName().length() < 30){
				//if moon doesn't exist
				if((getMoonByName(m.getMyPlanetId(), m.getName()) == null)){
					Moon createdMoon=dao.createMoon(m);
					return createdMoon;
				}
				else{
					throw new MoonFailException("A moon with this name exists");
				}
			}
			else{
				throw new MoonFailException("Your moon's name exceeds 30 characters");
			}
		}catch (MoonFailException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean deleteMoonById(int moonId) {
		return dao.deleteMoonById(moonId);
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		// TODO Auto-generated method stub
		return null;
	}
}
