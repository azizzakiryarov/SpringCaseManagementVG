//package se.groupfish;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import se.groupfish.springcasemanagement.exception.ServiceException;
//import se.groupfish.springcasemanagement.model.User;
//import se.groupfish.springcasemanagement.model.WorkItem;
//import se.groupfish.springcasemanagement.repository.WorkItemRepository;
//import se.groupfish.springcasemanagement.service.WorkItemService;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest()
//public final class SpringCaseManagementApplicationTests {
//
//	@Rule
//	public ExpectedException expectedException = ExpectedException.none();
//
//	@Autowired
//	private WorkItemRepository workItemRepository;
//
//	@Autowired
//	private WorkItemService workItemService;
//
//	@Test
//	public void shouldThrowExceptionWhenWillDistributeMoreThanFiveWorkItem()
//			throws ServiceException {
//
//		long workItemId = 6L;
//		long userId = 1L;
//		String title = "W1";
//		String description = "Go to bed";
//
//		String exceptionMessage = "It's more than 5 workitem for one user...";
//
//		expectedException.expect(ServiceException.class);
//		expectedException.expectMessage(exceptionMessage);
//
//		WorkItem workitem = new WorkItem(title, description);
//
//		List<WorkItem> listWorkItem = new ArrayList<>();
//		listWorkItem.add(workitem);
//
//		for (WorkItem workItems : listWorkItem) {
//			if (workItems.getId() > 5) {
//				throw new ServiceException(exceptionMessage);
//			}
//		}
//
//		when(workItemRepository.findByUserId(userId).contains(listWorkItem))
//				.thenThrow(new ServiceException(exceptionMessage));
//
//		workItemService.addWorkItemToUser(workItemId, userId);
//
//		verify(workItemRepository).addWorkItemToUser(workItemId, userId);
//
//	}
//
//	@Test
//	public void shouldThrowExceptionWhenAssignToInactiveUser() throws ServiceException {
//
//		long userId = 4L;
//		String userName = "1000001-abcd";
//		String firstName = "Malin";
//		String lastName = "Stromberg";
//		String state = "Inactive";
//		String userNumber = "100";
//
//		long workItemId = 2L;
//
//		String exceptionMessage = "You couldn't assign task to user: " + userId + " id "
//				+ " because user is 'Inactive'";
//
//		expectedException.expect(ServiceException.class);
//		expectedException.expectMessage(exceptionMessage);
//
//		User user = new User(firstName, lastName, userName, userNumber);
//
//		if (user.getState().equals(state)) {
//			throw new ServiceException(exceptionMessage);
//		}
//
//		when(workItemRepository.addWorkItemToUser(workItemId, userId)).thenThrow(new ServiceException(exceptionMessage));
//
//		workItemService.addWorkItemToUser(workItemId, userId);
//
//		verify(workItemRepository).addWorkItemToUser(workItemId, userId);
//
//	}
//
//	@Test
//	public void shouldThrowExceptionWhenUserIdIsAlreadyExists() throws ServiceException {
//
//		long userId = 4L;
//		String userName = "1000001-abcd";
//		String firstName = "Malin";
//		String lastName = "Stromberg";
//		String userNumber = "100";
//		
//		long userId2 = 4L;
//		String userName2 = "1000001-abcd";
//		String firstName2 = "Malin";
//		String lastName2 = "Stromberg";
//		String userNumber2 = "100";
//
//		long workItemId = 1L;
//		long workItemId2 = 1L;
//
//		String exceptionMessage = "This user " + userId + " id " + " is already exists";
//
//		expectedException.expect(ServiceException.class);
//		expectedException.expectMessage(exceptionMessage);
//
//		User user = new User(firstName, lastName, userName, userNumber);
//		
//		User user2 = new User(userName2, firstName2, lastName2, userNumber2);
//
//		List<User> userStorage = new ArrayList<>();
//		userStorage.add(user);
//		userStorage.add(user2);
//
//		for (User users : userStorage) {
//
//			if (users.getUserName().equals(user.getUserName())) {
//				throw new ServiceException(exceptionMessage);
//			}
//
//			when(workItemRepository.addWorkItemToUser(workItemId, userId))
//					.thenThrow(new ServiceException(exceptionMessage));
//
//			workItemService.addWorkItemToUser(workItemId, userId);
//
//			verify(workItemRepository).addWorkItemToUser(workItemId, userId);
//
//		}
//
//	}
//}
