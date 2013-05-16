package punters_den.web;

import spark.Request;
import spark.Response;
import spark.Route;
import java.util.Map;
import java.util.HashMap;
import java.io.StringWriter;

public class Jesponse {
    private Map <String, ?> data;
    private Response response;
    
    public Jesponse (Response response) {
        data = new HashMap<String, ?>();        
        this.response = response;
    }
    
    public Response getResponse() {
        return response;
    }
    
    public HttpServletResponse raw() {
        return response.raw();
    }
    
    public void status (int status) {
        response.status(status);
    }
    
    public void addData(String key, Object value) {
        data.put(key, value);
    }
    
    public Object removeData(String key) {
        return data.remove(key);
    }
    
    public Response toResponse() {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer userInMap);
        response.body(writer.toString());
        return response;
    }    
}