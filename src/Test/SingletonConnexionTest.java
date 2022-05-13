package Test;

import DataAccess.SingletonConnexion;
import Exceptions.DataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingletonConnexionTest {

    @Test
    void getInstance() {
        //assert for the exception
        assertThrows(DataException.class,
                () -> SingletonConnexion.getInstance()
        );
    }
}