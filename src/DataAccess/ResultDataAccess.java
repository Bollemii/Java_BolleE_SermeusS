package DataAccess;

import Exceptions.DataException;
import Exceptions.ValueException;
import Model.Result;

import java.util.ArrayList;

public interface ResultDataAccess {
	ArrayList<Result> getAllResults() throws DataException, ValueException;

	int addResult(Result result) throws DataException;
}
