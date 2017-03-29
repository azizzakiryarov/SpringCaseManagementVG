package se.groupfish.springcasemanagement.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.Issue;
import se.groupfish.springcasemanagement.model.WorkItem;
import se.groupfish.springcasemanagement.repository.IssueRepository;
import se.groupfish.springcasemanagement.repository.WorkItemRepository;

@Component
public class IssueService {

	private final IssueRepository issueRepository;
	private final WorkItemRepository workItemRepository;

	@Autowired
	public IssueService(IssueRepository issueRepository, WorkItemRepository workItemRepository) {
		this.issueRepository = issueRepository;
		this.workItemRepository = workItemRepository;
	}

	@Transactional
	public Issue createIssue(Issue issue, Long workItemId) throws ServiceException {
		if (workItemRepository.exists(workItemId)) {
			if (!workItemRepository.findOne(workItemId).getState().equals("Done")) {
				throw new ServiceException("Unable to comply. Can only put an issue on 'Done' workitems.");
			}
			try {
				Issue saveIssue = issue;
				WorkItem workItem = workItemRepository.findOne(workItemId);
				issue.setWorkItem(workItem);
				workItem.setIssue(saveIssue);
				issueRepository.save(saveIssue);
				workItemRepository.save(workItem).setState("Unstarted");
				return issue;
			} catch (DataAccessException e) {
				throw new ServiceException("Could not create issue.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown workitem.");
		}
	}

	public Issue updateIssueComment(Long id, String comment) throws ServiceException {
		if (issueRepository.exists(id)) {
			try {
				Issue issue = issueRepository.findOne(id);
				issue.setComment(comment);
				return issueRepository.save(issue);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not update issue.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown issue.");
		}
	}

	public Collection<WorkItem> getAllWorkItemsWithIssue() throws ServiceException {
		try {
			return workItemRepository.getAllWorkItemsWithIssue();
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to comply. Cannot access data.", e);
		}
	}

	public Page<Issue> findAllIssues(Pageable page) {
		return issueRepository.findAll(page);
	}

	// This method I used for RestCaseManagement
	public Issue getById(Long id) {
		return issueRepository.findOne(id);
	}

}
