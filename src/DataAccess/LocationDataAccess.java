package DataAccess;

import Exceptions.DataException;
import Model.Location;

import java.util.ArrayList;

public interface LocationDataAccess {
	ArrayList<Location> getAllLocations() throws DataException;
}
