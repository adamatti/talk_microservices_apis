package adamatti

import groovy.json.JsonBuilder
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import spark.Request
import spark.Response
import spark.Spark

@Slf4j
@CompileStatic
class Main {
    private static List<Map> countries = [
        [id: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093f', name : 'Brasil - Groovy'] as Map,
        [id: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093d', name : 'Argentina - Groovy'] as Map
    ]

    static void main(String [] args){
        Spark.port(8081)

        Spark.get("/countries"){ Request req, Response res ->
            res.header("Content-Type","application.json")

            toJson([
                countries: filter(req.queryMap().toMap(), countries)
            ])
        }
        log.info("Started")
    }

    // TODO this method need to be improved
    private static List filter(Map<String,String[]> filter, List<Map> values){
        if (filter.id){
            return values.findAll { row ->
                filter.id.contains(row.id)
            }
        }
        values
    }

    private static String toJson(obj){
        new JsonBuilder(obj).toString()
    }
}