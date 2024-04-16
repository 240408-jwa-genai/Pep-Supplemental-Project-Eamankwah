package com.revature;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.controller.PlanetController;
import com.revature.controller.UserController;
import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.PlanetDao;
import com.revature.repository.UserDao;
import com.revature.service.PlanetService;
import com.revature.service.UserService;
import com.revature.utilities.ConnectionUtil;

public class MainDriver {

    public static UserDao userDao= new UserDao();
    public static UserService userService=new UserService(userDao);
    public static UserController userController= new UserController(userService);

    public static PlanetDao planetDao= new PlanetDao();
    public static PlanetService planetService= new PlanetService(planetDao);
    public static PlanetController planetController= new PlanetController(planetService);
    public static int loggedInUserId=0;
    public static String loggedInUserName="";

    public static void main(String[] args) {
        // TODO: implement main method to initialize layers and run the application

        /*
         *Prompt user to Create an Account Or Login
         */
        try( Scanner scan = new Scanner(System.in)){
            while(loggedInUserId == 0) {
                System.out.println("Welcome to the Planetarium! Please Choose One of the Following By their numbers: ");
                System.out.println("1. Create an Account");
                System.out.println("2. Login to Planetarium");
                System.out.println("3. EXIT");
                System.out.print("Your choice:");
                String unknownUserChoice= scan.nextLine();
                if (unknownUserChoice.equals("1")) {
                    String unknownUsername="";
                    String unknownPassword="";
                    //begin user registration process
                    System.out.println("------- Create An Account -------");
                    System.out.print("Please enter a Username: ");
                    unknownUsername=scan.nextLine();

                    System.out.print("Please enter a Password: ");
                    unknownPassword= scan.nextLine();

                    User unknownUser= new User();
                    unknownUser.setUsername(unknownUsername);
                    unknownUser.setPassword(unknownPassword);

                    //System.out.println(unknownUser.getUsername() + " : "+unknownUser.getPassword());

                    userController.register(unknownUser);

                } else if (unknownUserChoice.equals("2")) {
                    String unknownUsername="";
                    String unknownPassword="";
                    //begin user registration process
                    System.out.println("------- Login -------");
                    System.out.print("Please enter a Username: ");
                    unknownUsername=scan.nextLine();

                    System.out.print("Please enter a Password: ");
                    unknownPassword= scan.nextLine();

                    UsernamePasswordAuthentication unknownUser= new UsernamePasswordAuthentication();
                    unknownUser.setUsername(unknownUsername);
                    unknownUser.setPassword(unknownPassword);

                    userController.authenticate(unknownUser);

                } else if (unknownUserChoice.equals("3")) {
                    System.exit(0);
                } else {
                    System.out.println("\nPlease choose from the provided options");
                    System.out.println("1. Create an Account");
                    System.out.println("2. Login to Planetarium");
                    System.out.print("Your choice:");
                    unknownUserChoice=scan.nextLine();
                }

            }//while !userIsLoggedIn

            //------------------------------------------------------------------
            //while user is Logged in
            /*
             *User is logged in
             */

            while(loggedInUserId >0){
                System.out.printf("\n%s\'s Planetarium\n",loggedInUserName);
                System.out.println("------------------");
                System.out.println("Please Choose One of the Following By their numbers: ");
                System.out.println("1. View Planets");
                System.out.println("2. View Moons");
                System.out.println("3. Add a Planet");
                System.out.println("4. Add a Moon");
                System.out.println("5. Remove a Planet");
                System.out.println("6. Exit");
                System.out.print("Your choice:");

                String choice= scan.nextLine();
                if(choice.equals("1")){
                    planetController.getAllPlanets(loggedInUserId);
                }
                else if(choice.equals("3")){
                    Planet tmp=new Planet();
                    System.out.print("Enter new Planet name: ");
                    tmp.setName(scan.nextLine());
                    planetController.createPlanet(loggedInUserId, tmp);
                }
                else if(choice.equals("5")){
                    System.out.print("Enter id of planet to delete:");
                    int planetId=Integer.parseInt(scan.nextLine());
                    planetController.deletePlanet(loggedInUserId,planetId);
                }
                else if (choice.equals("6")) {
                    System.exit(0);
                }

            }

        }//try with resources





    }

}
