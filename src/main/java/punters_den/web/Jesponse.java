package punters_den.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Jesponse {
    static Logger logger = Logger.getLogger(Jesponse.class);
    private Map<String, Object> json;
    private Response response;

    public Jesponse(Response response) {
        json = new HashMap<String, Object>();
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public HttpServletResponse raw() {
        return response.raw();
    }

    public void status(int status) {
        response.status(status);
    }

    public void addData(String key, Object value) {
        json.put(key, value);
    }

    public Object removeData(String key) {
        return json.remove(key);
    }

    public String toResponse() {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, json);
            response.body(writer.toString());
        } catch (IOException e) {
            logger.error("Error writing json", e);
            response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.body("{\"status\":\"error\",\"statusMessage\":\"Error generating JSON\"}");
        }
        return writer.toString();
    }
}