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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((workItem == null) ? 0 : workItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (workItem == null) {
			if (other.workItem != null)
				return false;
		} else if (!workItem.equals(other.workItem))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Issue [comment=" + comment + "]";
	}

}
