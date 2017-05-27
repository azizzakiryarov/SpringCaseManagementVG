package se.groupfish.springcasemanagement.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import se.groupfish.springcasemanagement.exception.ServiceException;
import se.groupfish.springcasemanagement.model.User;
import se.groupfish.springcasemanagement.model.WorkItem;
import se.groupfish.springcasemanagement.repository.UserRepository;
import se.groupfish.springcasemanagement.repository.WorkItemRepository;

@Component
public class WorkItemService {

	private final WorkItemRepository workItemRepository;
	private final UserRepository userRepository;

	@Autowired
	public WorkItemService(WorkItemRepository workItemRepository, UserRepository userRepository) {
		this.workItemRepository = workItemRepository;
		this.userRepository = userRepository;
	}

	public WorkItem createWorkItem(WorkItem workItem) throws ServiceException {
		WorkItem saveWorkItem = workItem;
		for (WorkItem item : workItemRepository.findAll()) {
			if (workItem.equals(item)) {
				throw new ServiceException("Unable to comply. WorkItem already exist.");
			}
		}
		try {
			return workItemRepository.save(saveWorkItem);
		} catch (DataAccessException e) {
			throw new ServiceException("WorkItem could not be created.");
		}
	}

	public WorkItem updateWorkItemDescription(Long workItemId, String description) throws ServiceException {
		if (workItemRepository.exists(workItemId)) {
			try {
				WorkItem workItem = workItemRepository.findOne(workItemId);
				workItem.setDescription(description);
				return workItemRepository.save(workItem);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not update workItem.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown workitem.");
		}
	}

	public WorkItem updateWorkItemTitle(Long id, String title) throws ServiceException {
		if (workItemRepository.exists(id)) {
			try {

				WorkItem workItem = workItemRepository.findOne(id);
				workItem.setTitle(title);
				return workItemRepository.save(workItem);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not update workItem.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown workitem.");
		}
	}

	public WorkItem updateWorkItemState(Long id, String state) throws ServiceException, IOException {
		if (workItemRepository.exists(id)) {
			if (userRepository.exists(userRepository.findByWorkItemsId(id).getId())
					&& userRepository.findByWorkItemsId(id).getState().equals("Inactive")) {
				throw new ServiceException("Unable to change state of workItem when the User is Inactive.");
			}
			try {
				WorkItem workItem = workItemRepository.findOne(id);
				workItem.setState(state);
				return workItemRepository.save(workItem);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not update workItem.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown workitem.");
		}
	}

	public void removeWorkItem(Long id) throws ServiceException {
		if (workItemRepository.exists(id)) {
			try {
				workItemRepository.delete(id);
			} catch (DataAccessException e) {
				throw new ServiceException("Could not delete workItem.");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown workitem.");
		}
	}

	@Transactional
	public WorkItem addWorkItemToUser(Long workItemid, Long userId) throws ServiceException {
		if (workItemRepository.exists(workItemid) && userRepository.exists(userId)) {
			if (!userRepository.findOne(userId).getState().equals("Active")) {
				throw new ServiceException("Unable to comply. Unable to assign workitems to non-active users.");
			}
			if (workItemRepository.findByUserId(userId).size() >= 5) {
				throw new ServiceException("Unable to comply. User cannot have more than 5 workitems assigned to it.");
			}
			try {
				WorkItem addWorkItem = workItemRepository.findOne(workItemid);
				User targetUser = userRepository.findOne(userId);
				addWorkItem.setUser(targetUser);
				return workItemRepository.save(addWorkItem);

			} catch (DataAccessException e) {

				throw new ServiceException("Could not add user to workItem");
			}
		} else {
			throw new ServiceException("Unable to comply. Unknown user or workitem.");
		}
	}

	public Collection<WorkItem> getWorkItemByState(String state) throws ServiceException {
		try {
			return workItemRepository.findByState(state);
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to get workitems by state.", e);
		}
	}

	public Collection<WorkItem> getAllWorkItemforTeam(Long id) throws ServiceException {
		try {
			return workItemRepository.findByUserTeamId(id);
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to get workitems from Team.", e);
		}
	}

	public Collection<WorkItem> getAllWorkItemforUser(Long id) throws ServiceException {
		try {
			return workItemRepository.findByUserId(id);
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to get workitems from User.", e);
		}
	}

	public Collection<WorkItem> getAllWorkItemByDescriptionContent(String descriptionContent) throws ServiceException {
		try {
			return workItemRepository.findByDescriptionContaining(descriptionContent);
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to get workitems by description content.", e);
		}
	}

	public Page<WorkItem> findAllWorkItems(Pageable page) {
		return workItemRepository.findAll(page);
	}

	public Page<WorkItem> getWorkItemsByStatusAndPeriod(String state, Date startDate, Date endDate, Pageable pageable)
			throws ServiceException {

		try {
			return workItemRepository.getWorkItemsByStatusAndPeriod(state, startDate, endDate, pageable); // fix
		} catch (DataAccessException e) {
			throw new ServiceException("It's not possible to get all workItems by state and period" + e);
		}
	}

	public boolean isWorkItemPersisted(WorkItem workItem) {
		return workItem.getId() != null;
	}

	public boolean isUserPersisted(User user) {
		return user.getId() != null;
	}

	public WorkItem getWorkItemById(Long id) throws ServiceException {
		try {
			return workItemRepository.findOne(id);
		} catch (DataAccessException e) {
			throw new ServiceException("Unable to get workitem by id " + id, e);
		}
	}
}
