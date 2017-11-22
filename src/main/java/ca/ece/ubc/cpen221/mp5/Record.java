package ca.ece.ubc.cpen221.mp5;

/**
 * Most generic data type for an entry in the database
 * 
 * @author nancy
 *
 */

public abstract class Record {
	protected String id;
	protected String type;

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.id;
	}
}
