package DataAccess;

import Exceptions.DataException;
import Model.Result;

import java.util.ArrayList;

public interface ResultDataAccess {
	ArrayList<Result> getAllResults() throws DataException;

	int addResult(Result result) throws DataException;
}
