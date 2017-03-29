package se.groupfish.springcasemanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.groupfish.springcasemanagement.model.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {
	
	Page<Issue> findAll (Pageable page);

}
