package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification  reqSpec;

    public RequestSpecification requestSpecification() throws IOException {

        if (reqSpec==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseuri"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return reqSpec;
        }
        return reqSpec;
    }

    public static String getGlobalValue(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/issdoc/IdeaProjects/APIFramwork/src/test/java/utilities/global.properties");

        prop.load(fis);

       return prop.getProperty(key);

    }

    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
