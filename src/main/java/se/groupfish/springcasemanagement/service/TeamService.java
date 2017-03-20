package se.groupfish.springcasemanagement.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.Team;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.repository.TeamRepository;
import se.groupfish.springcasemanagement.repository.UserRepository;

@Component
public class TeamService {

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;

	@Autowired
	public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
	}

	public Team createTeam(Team team) throws ServiceException {
		Team teamToSave = team;
		if (isTeamPersisted(teamToSave)) {
			throw new ServiceException("This team is already persisted!");
		}
		if (!teamRepository.findByTeamName(teamToSave.getTeamName()).isEmpty()) {
			throw new ServiceException("A Team already exists with that name.");
		}
		try {
			return teamRepository.save(teamToSave);
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to comply.", e);
		}
	}

	public Team updateTeamName(Long teamId, String teamName) throws ServiceException {
		if (teamRepository.exists(teamId)) {
			Team team = teamRepository.findOne(teamId);
			if (teamRepository.getAllTeamNames().contains(teamName)) {
				throw new ServiceException("A Team already exists with that name.");
			}
			try {
				team.setTeamName(teamName);
				return teamRepository.save(team);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not update Team.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown Team!");
		}
	}

	public void disableTeam(Long id) throws ServiceException {
		if (teamRepository.exists(id)) {
			if (teamRepository.getStateFromTeam(id).contains("Inactive")) {
				throw new ServiceException("This Team is already 'Inactive'");
			}
			try {
				teamRepository.disableTeam(id);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not disable Team.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown Team!");
		}
	}

	public void activateTeam(Long id) throws ServiceException {
		if (teamRepository.exists(id)) {
			if (teamRepository.getStateFromTeam(id).contains("Active")) {
				throw new ServiceException("This Team is already 'Active'");
			}
			try {
				teamRepository.activateTeam(id);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not activate Team.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown Team!");
		}
	}

	@Transactional
	public void addUserToTeam(long teamId, long userId) throws ServiceException {

		if (teamRepository.exists(teamId) && userRepository.exists(userId)) {

			if (userRepository.findByTeamId(teamId).size() > 9) {
				throw new ServiceException("Can only have 10 users in a team.");
			}
			if (userRepository.findOne(userId).getTeam() != null) {
				throw new ServiceException("User is already in a team...");
			}
			try {
				teamRepository.addUserToTeam(teamId, userId);
			} catch (DataAccessException e) {
				throw new ServiceException("Cannot add user to team");
			}
		} else {
			throw new ServiceException("Cannot find team or user.");
		}
	}

	public Collection<Team> getAllTeam() {
		return teamRepository.getAllTeams();
	}

	public Collection<User> getAllUsersFromOneTeam(long id) {
		return userRepository.findByTeamId(id);
	}

	public boolean isTeamPersisted(Team team) {
		return team.getId() != null;
	}
	
	// This method I used for RestCaseManagemenent
		public Team getTeamById(long id) {
			return teamRepository.findOne(id);
		}
	
	
}
