package ontologie_lab3.utils.sparql;

import com.bordercloud.sparql.Endpoint;
import com.bordercloud.sparql.EndpointException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class EndpointExecutor {
    private static final Logger logger = LogManager.getLogger(EndpointExecutor.class.getName());

    private Endpoint sp;

    public EndpointExecutor() {
        sp = new Endpoint(SparqlConstants.ENDPOINT, false);
        sp.setMethodHTTPRead("GET");
    }

    public HashMap<String, HashMap> executeQuery(String queryTemplate, Object... parameters) throws EndpointException {
        String query = String.format(queryTemplate, parameters);
        logger.info(query);

        long start = System.currentTimeMillis();
        HashMap<String, HashMap> result = sp.query(query);
        long finish = System.currentTimeMillis();
        logger.info(String.format("Elapsed time: %d ms", finish - start));
        logger.info(String.format("Number of records: %d",
                ((ArrayList) result.get("result").get("rows")).size()));

        return result;
    }


}
