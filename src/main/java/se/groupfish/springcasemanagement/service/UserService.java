package se.groupfish.springcasemanagement.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.model.WorkItem;
import se.groupfish.springcasemanagement.repository.TeamRepository;
import se.groupfish.springcasemanagement.repository.UserRepository;
import se.groupfish.springcasemanagement.repository.WorkItemRepository;

@Component
public class UserService {

	private final UserRepository userRepository;
	private final WorkItemRepository workItemRepository;
	private final TeamRepository teamRepository;

	@Autowired
	public UserService(UserRepository userRepository, WorkItemRepository workItemRepository,
			TeamRepository teamRepository) {
		this.userRepository = userRepository;
		this.workItemRepository = workItemRepository;
		this.teamRepository = teamRepository;
	}

	public User createUser(User user) throws ServiceException {
		User userToSave = user;
		if (isUserPersisted(userToSave)) {
			throw new ServiceException("Unable to comply. User is alreday persisted.");
		}
		if (user.getUserName().length() < 10) {
			throw new ServiceException("Username must be 10 characters long minimum. " + user.getUserName());
		}
		if (userRepository.getAllUsersName().contains(user.getUserName())) {
			throw new ServiceException("Cannot create user. Username is already in use.");
		}
		if (userRepository.getUsersNumber().contains(user.getUserNumber())) {
			throw new ServiceException("Cannot create user. User number is already in use.");
		}
		try {
			return userRepository.save(userToSave);
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to create User.", e);
		}
	}

	public void updateUserUsername(Long id, String userName) throws ServiceException {
		if (userRepository.exists(id)) {
			if (userName.length() < 10) {
				throw new ServiceException("Username must be 10 characters long minimum. " + userName);
			}
			try {
				User userToUpdate = userRepository.findOne(id);
				userToUpdate.setUserName(userName);
				userRepository.save(userToUpdate);
			} catch (DataAccessException e) {
				throw new ServiceException("Unable to update user.", e);
			}
		} else {
			throw new ServiceException("Unable to update user. User does not exist.");
		}
	}

	@Transactional
	public void updateUserState(Long id, String state) throws ServiceException {
		if (userRepository.exists(id)) {
			try {
				User userToUpdate = userRepository.findOne(id);
				if (state.equals("Inactive")) {
					Collection<WorkItem> workItems = workItemRepository.findByUserId(id);
					for (WorkItem temp : workItems) {
						temp.setState("Unstarted");
						workItemRepository.save(temp);
					}
				}
				userToUpdate.setState(state);
				userRepository.save(userToUpdate);
			} catch (DataAccessException e) {
				throw new ServiceException("Unable to update user.", e);
			}
		} else {
			throw new ServiceException("Unable to update user. User does not exist.");
		}
	}

	public User findUserByNumber(String number) throws ServiceException {
		if (userRepository.findByUserNumber(number) == null) {
			throw new ServiceException("No user exist with that number!");
		}
		try {
			return userRepository.findUserByNumber(number);
		} catch (DataAccessException e) {

			throw new ServiceException("Unable to find users by user number.", e);
		}
	}

	public User findUserByUserName(String username) throws ServiceException {
		if (userRepository.findByUserName(username) == null) {
			throw new ServiceException("No user exist with that username!");
		}
		try {
			return userRepository.findByUserName(username);
		} catch (DataAccessException e) {

			throw new ServiceException("Unable to find users by username.", e);
		}
	}

	//It was a Collection type so I changed to User object
	public User findUserByFirstName(String firstName) throws ServiceException {
		if (userRepository.findByFirstName(firstName) == null) {
			throw new ServiceException("No user exist with that first name!");
		}
		try {
			return userRepository.findByFirstName(firstName);
		} catch (DataAccessException e) {

			throw new ServiceException("Unable to find users by firstname.", e);
		}
	}

	public List<User> findAllUsersFromOneTeam(Long id) throws ServiceException {
		if (!teamRepository.exists(id)) {
			throw new ServiceException("The team does not exist!");
		}
		if (userRepository.findByTeamId(id).isEmpty()) {
			throw new ServiceException("The team has no users.");
		}
		try {
			return userRepository.findByTeamId(id);
		} catch (DataAccessException e) {

			throw new ServiceException("Unable to find users from Team.", e);
		}
	}

	public boolean isUserPersisted(User user) {
		return user.getId() != null;
	}
	
	public Page<User> findAllUsers(Pageable page){
		return userRepository.findAll(page);
	}
	
	/*
	 *This method use for my RestCaseManagement projekt 
	 */
	 
	public User getUserById(long id){
		return userRepository.findOne(id);
	}
}
