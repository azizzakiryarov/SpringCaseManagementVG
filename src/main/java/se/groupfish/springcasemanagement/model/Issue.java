package se.groupfish.springcasemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Issue extends AbstractEntity {

	@Column(nullable = false)
	private String comment;

	@OneToOne(mappedBy = "issue")
	private WorkItem workItem;

	protected Issue() {
	}

	public Issue(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public WorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}

	@Override
	public String toString() {
		return "Issue [comment=" + comment + "]";
	}

}
