package se.groupfish.springcasemanagement;


import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import se.groupfish.springcasemanagement.model.Issue;
import se.groupfish.springcasemanagement.model.Team;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.model.WorkItem;
import se.groupfish.springcasemanagement.service.IssueService;
import se.groupfish.springcasemanagement.service.TeamService;
import se.groupfish.springcasemanagement.service.UserService;
import se.groupfish.springcasemanagement.service.WorkItemService;

@SpringBootApplication
public class SpringCaseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCaseManagementApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(ApplicationContext context){
		return args -> {
			
			TeamService teamService = context.getBean(TeamService.class);
			UserService userService = context.getBean(UserService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);
			IssueService issueService = context.getBean(IssueService.class);
//___________________________________________________________________________________________________//
			
//			Team team1 = new Team("Chicago");
//			Team team2 = new Team("Kazakhstan");
//			Team team3 = new Team("Kanada");

			//HEJ HEJ
			
			
//			User user1 = new User("Erik1", "Eriksson", "100001-Erik", "001");
//			User user2 = new User("Erik2", "Eriksson", "100002-Erik", "002");
//			User user3 = new User("Erik3", "Eriksson", "100003-Erik", "003");
//			User user4 = new User("Erik4", "Eriksson", "100004-Erik", "004");
//			User user5 = new User("Erik5", "Eriksson", "100005-Erik", "005");
//			User user6 = new User("Erik6", "Eriksson", "100006-Erik", "006");
//			User user7 = new User("Erik7", "Eriksson", "100007-Erik", "007");
//			User user8 = new User("Erik8", "Eriksson", "100008-Erik", "008");
//			User user9 = new User("Erik9", "Eriksson", "100009-Erik", "009");
//			User user10 = new User("Erik10", "Eriksson", "100010-Erik", "010");
//			User user11 = new User("Erik11", "Eriksson", "100011-Erik", "011");
//			User user12 = new User("Erik12", "Eriksson", "100012-Erik", "012");
//			User user13 = new User("Erik13", "Eriksson", "100013-Erik", "013");
//			User user14 = new User("Erik14", "Eriksson", "100014-Erik", "014");
//			User user15 = new User("Erik15", "Eriksson", "100015-Erik", "015");
//			User user16 = new User("Erik16", "Eriksson", "100016-Erik", "016");
//			User user17 = new User("Erik17", "Eriksson", "100017-Erik", "017");
//			User user18 = new User("Erik18", "Eriksson", "100018-Erik", "018");
//			User user19 = new User("Erik19", "Eriksson", "100019-Erik", "019");
//			User user20 = new User("Erik20", "Eriksson", "100020-Erik", "020");
			
			WorkItem workItem1 = new WorkItem("WI1", "Bye a car");
			WorkItem workItem2 = new WorkItem("WI2", "Bye a car");
			WorkItem workItem3 = new WorkItem("WI3", "Bye a car");
			WorkItem workItem4 = new WorkItem("WI4", "Bye a car");
			WorkItem workItem5 = new WorkItem("WI5", "Bye a car");
		
			WorkItem workItem6 = new WorkItem("WI6", "Bye a car");
			WorkItem workItem7 = new WorkItem("WI7", "Bye a car");
			WorkItem workItem8 = new WorkItem("WI8", "Bye a car");
			WorkItem workItem9 = new WorkItem("WI9", "Bye a car");
			WorkItem workItem10 = new WorkItem("WI10", "Bye a car");
			
			Issue issue = new Issue("Goodjob");
			Issue issue2 = new Issue("Goodjob2");
			Issue issue3 = new Issue("Goodjob3");
			Issue issue4 = new Issue("Goodjob4");
			Issue issue5 = new Issue("Goodjob5");
			Issue issue6 = new Issue("Goodjob6");
			Issue issue7 = new Issue("Goodjob7");
			Issue issue8 = new Issue("Goodjob8");
			Issue issue9 = new Issue("Goodjob9");
			
			//------------CREATE-----------//
			

//			teamService.createTeam(team1);                    //Create all tables>>>>>
//			teamService.createTeam(team2); 

//			userService.createUser(user1);
//			userService.createUser(user2);
//			userService.createUser(user3);                    //Create all tables>>>>>
//			userService.createUser(user4);
//			userService.createUser(user5);
//			
//			userService.createUser(user6);
//			userService.createUser(user7);
//			userService.createUser(user8);                    //Create all tables>>>>>
//			userService.createUser(user9);
//			userService.createUser(user10);
//			
//			userService.createUser(user11);
//			userService.createUser(user12);
//			userService.createUser(user13);                    //Create all tables>>>>>
//			userService.createUser(user14);
//			userService.createUser(user15);
//			
//			userService.createUser(user16);
//			userService.createUser(user17);
//			userService.createUser(user18);                    //Create all tables>>>>>
//			userService.createUser(user19);
//			userService.createUser(user20);
//			
//			workItemService.createWorkItem(workItem1);
//			workItemService.createWorkItem(workItem2);
//			workItemService.createWorkItem(workItem3);     //Create all tables>>>>>
//			workItemService.createWorkItem(workItem4);
//			workItemService.createWorkItem(workItem5);
			
			/*_______________________________SERVICE______________________________________*/
			
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>USER<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
//			userService.createUser(user11);                                                 // createUser     //√
//			userService.updateUserState(3L, "Active");                         //updateAndDisableUserState    //√
//			userService.updateUserUsername(3L, "KausKille-10000");                       //updateUserUserName //√
//			System.out.println(userService.findUserByNumber("004"));                    //getUserByNumber     //√
//			System.out.println(userService.findUserByFirstName("Mikael"));               //getByFirstName     //√
//			System.out.println(userService.findAllUsersFromOneTeam(0L));         //getAllUsersFromOneTeam     //√           
			
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TEAM<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
//			teamService.createTeam(team3);                                                  //create Team      //√
//			teamService.updateTeamName(14L, "China");                                       //update Team      //√
//			teamService.disableTeam(2L);                                                    //disable Team     //√
//			System.out.println(teamService.getAllTeam());                                   //get All Teams    //√
			
//			teamService.addUserToTeam(1L, 3L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 4L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 5);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 6L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 7L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 13L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 14L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 15L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 16L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 17L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(1L, 18L);	  	                                        //add User To Team //√
			
//			teamService.addUserToTeam(2L, 3L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 4L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 5);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 6L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 7L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 13L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 14L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 15L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 16L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 17L);	  	                                        //add User To Team //√
//			teamService.addUserToTeam(2L, 18L);	  	                                        //add User To Team //√
			
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>WORKITEM<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//			workItemService.createWorkItem(workItem7);                                       //create WorkItem  //√
//			workItemService.updateWorkItemState(26L, "Done");                           //update WorkItem State //√
//			workItemService.updateWorkItemDescription(23L, "Should code more");   //update WorkItem Description  //√
//			workItemService.updateWorkItemTitle(23L, "Programmering");                  //update WorkItem Title  //√
//			workItemService.removeWorkItem(8L);                                        //remove WorkItem        //√
//			workItemService.addWorkItemToUser(26L, 4L);                                 //assign WorkItem        //√
//			workItemService.addWorkItemToUser(10L, 3L);                                //assign WorkItem        //√
//			workItemService.addWorkItemToUser(11L, 3L);                                //assign WorkItem        //√
//			workItemService.addWorkItemToUser(12L, 3L);                                //assign WorkItem        //√
//			workItemService.addWorkItemToUser(19L, 3L);                                //assign WorkItem        //√
//			workItemService.addWorkItemToUser(34L, 3L);                                //assign WorkItem        //√
//			System.out.println(workItemService.getWorkItemByState("Started"));    //get All WorkItems By State  //√
//			System.out.println(workItemService.getAllWorkItemforTeam(1L));        //get All WorkItems By Team Id//√
//			workItemService.getAllWorkItemforUser(3L).forEach(System.out::println);        //get All WorkItems By User Id//√
//			System.out.println(workItemService.getAllWorkItemByDescriptionContent("car")); //By Description     //√
			
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ISSUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
//			issueService.createIssue(issue, 25L);                                  //assign issue to WorkItem    //√
//			issueService.updateIssueComment(20L, "Brilliantly");                  //update issue                 //√
//			System.out.println(issueService.getAllWorkItemWithIssue());           //get all WorkItem             //√
			
			//---------------------------------------TEST---------------------------------------//
			
//			System.out.println(userService.getByTeamId(1L));
			
			//-------------------------------------Paging-------------------------------------//
			
//			userService.findAllUsers(new PageRequest(0, 10)).forEach(System.out::println);                 //√
//			workItemService.findAllWorkItems(new PageRequest(0, 5)).forEach(System.out::println);          //√
//			issueService.findAllIssues(new PageRequest(0, 2)).forEach(System.out::println);                //√
			
			//------------------------------------HistoryOfWorkItems-------------------------//
			
//			Date start = new Date(System.currentTimeMillis()-24*60*60*1000);
//			
//			Date end = new Date(System.currentTimeMillis());
//			
//			workItemService.getWorkItemsByStatusAndPeriod("Done", start, end, new PageRequest(0, 5)).forEach(System.out::println);
			
			
		};		
	}
	
}
