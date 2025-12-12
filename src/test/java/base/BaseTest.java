package base;

import utils.DataBaseClient;
import utils.RestClient;

import java.sql.SQLException;

public class BaseTest extends SetupExtension{
    public static RestClient restClient;
    public static DataBaseClient dataBaseClient;

    @Override
    void setup() {
        restClient = new RestClient();
        dataBaseClient = new DataBaseClient();

        dataBaseClient.establishConnection("JM");
    }

    @Override
    public void close() throws Throwable {
        if (dataBaseClient != null) {
            dataBaseClient.closeConnection();
        }
    }
}
