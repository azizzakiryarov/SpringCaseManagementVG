package se.groupfish.springcasemanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import se.groupfish.springcasemanagement.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {

	Page<User> findAll(Pageable page);

	@Query("select u from User u")
	public List<User> getAllUsers();

	@Modifying
	@Transactional
	@Query("update User u set u.firstName = ?1, u.lastName = ?2, u.userName = ?3 where u.id = ?4")
	public void updateUser(String firstName, String lastName, String userName, long id);

	@Modifying
	@Transactional
	@Query("update User u set u.state = 'Inactive' where u.id = ?1")
	public void disableUser(long id);

	@Query("select u.state from User u where u.id = ?1")
	public List<String> getUsersState(long id);

	@Query("select u.firstName from User u")
	public List<String> getUsersName();

	@Query("select u.userName from User u")
	public List<String> getAllUsersName();

	@Query("select t.id from Team t")
	public List<Long> getAllIdsFromTeam();

	@Query("select u.userNumber from User u")
	public List<String> getUsersNumber();

	@Query("select u.id from User u")
	public List<String> getUsersId();

	@Query("select u from User u where u.userNumber = ?1")
	User findUserByNumber(String number);

	//Here it was a Collections type so I changed it to User object
	User findByFirstName(String firstName);

	List<User> findByTeamId(Long id);

	User findByWorkItemsId(Long id);

	User findByUserNumber(String number);

	User findByUserName(String username);

}
