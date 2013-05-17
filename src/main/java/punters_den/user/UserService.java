package punters_den.user;

import punters_den.db.DB;
import punters_den.web.Jesponse;
import punters_den.web.Joute;
import spark.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static spark.Spark.get;

public class UserService {
    public void addRoutes() {
        get(new Joute("/api/user/:username/login") {
            @Override
            public void handle(Request request, Jesponse jesponse) throws Exception {
                final List<User> users = DB.query("select * from user where username = ? and password = ?", Arrays.asList(request.params(":username"), request.raw().getParameter("password")), User.class);
                if (users != null && users.size() > 0) {
                    final String authKey = UUID.randomUUID().toString();
                    DB.update("user", new HashMap<String, Object>() {{
                                put("auth_key", authKey);
                            }}, new HashMap<String, Object>() {{
                                put("id", users.get(0).getId());
                            }}
                    );
                    jesponse.addData("user", users.get(0));
                    jesponse.addData("auth-key", authKey);
                } else {
                    throw new RuntimeException("user not found");
                }
            }
        });
        get(new Joute("/api/user/:username/logout") {
            @Override
            public void handle(final Request request, Jesponse jesponse) throws Exception {
                DB.update("user", new HashMap<String, Object>() {{
                            put("auth_key", null);
                        }}, new HashMap<String, Object>() {{
                            put("auth_key", request.cookie("auth-key"));
                        }}
                );
            }
        });
    }
}
