package se.groupfish.springcasemanagement.repository;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.groupfish.springcasemanagement.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>, PagingAndSortingRepository<Team, Long> {

	@Modifying
	@Transactional
	@Query("update Team set teamName = ?1 where id = ?2")
	public void updateTeam(String teamName, long teamId);

	@Query("select t.teamName from Team t")
	public List<Team> getAllTeamNames();

	@Modifying
	@Transactional
	@Query("update Team set state = 'Inactive' where id = ?1")
	public void disableTeam(long id);

	@Modifying
	@Transactional
	@Query("update Team set state = 'Active' where id = ?1")
	public void activateTeam(long id);

	@Query("select t.id from Team t")
	public List<Long> getAllIds();

	@Query("select t.state from Team t where t.id = ?1")
	public List<Long> getStateFromTeam(long id);

	@Query("select t from Team t")
	public List<Team> getAllTeams();

	@Modifying
	@Transactional
	@Query("update User set team_id = ?1 where id = ?2")
	public void addUserToTeam(long teamId, long userId);

	Collection<Team> findByTeamName(String teamName);
	
	

}
