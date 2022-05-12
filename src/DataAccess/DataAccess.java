package DataAccess;

import Exceptions.DataException;

public interface DataAccess {
	void closeConnection() throws DataException;
}
