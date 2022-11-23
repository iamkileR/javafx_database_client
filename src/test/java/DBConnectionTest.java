import org.example.Connect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DBConnectionTest {
    Connect service = new Connect();

    public DBConnectionTest() throws SQLException {
        String jdbc ="jdbc:oracle:thin:@localhost:1521:ORCL";
        String userid = "humba";
        String password = "12345";


        service.getDBConnection(jdbc,userid,password);

    }

    @Test
    public void isClosedd_throwables_printStackTrace(){

            //then
            Assertions.assertThrows(IllegalArgumentException.class,()->service.isClosedd());

    }


}
