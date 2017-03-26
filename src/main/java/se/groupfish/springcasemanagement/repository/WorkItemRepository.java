package se.groupfish.springcasemanagement.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import se.groupfish.springcasemanagement.model.WorkItem;

public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Long>, CrudRepository<WorkItem, Long> {

	Page<WorkItem> findAll(Pageable page);

	@Transactional
	@Query("select w.state from WorkItem w, User u where u.id = ?1")
	public List<String> getAllWorkItemsState(Long id);

	@Transactional
	@Query("select w from WorkItem w, User u where u.id = ?1")
	public List<WorkItem> getAllWorkItems(Long id);

	Collection<WorkItem> findByState(String state);

	Collection<WorkItem> findByDescriptionContaining(String description);

	Collection<WorkItem> findByUserId(Long id);

	Collection<WorkItem> findByUserTeamId(Long id);
	
	Collection<WorkItem> findByIssueId(Long id);
	

//	@Query("select w from WorkItem w where w.issue is not null")
//	Collection<WorkItem> getAllWorkItemsWithIssue();

//	@Transactional
//	@Query("select w from WorkItem w where state = ?1 and lastModifiedDate between ?2 and ?3")
//	Collection<WorkItem> getAllWorkItemsFromOneMonth(String state, String fromWhichDate, String toWhichDate);

	@Query("SELECT w FROM WorkItem w WHERE w.state = :state AND w.lastModifiedDate BETWEEN :startDate AND :endDate")
	Page<WorkItem> getWorkItemsByStatusAndPeriod(@Param("state") String state, @Param("startDate") Date startDate,
	@Param("endDate") Date endDate, Pageable pageable);
	
//	public WorkItem addWorkItemToUser(Long workItemid, Long userId);
}
