( ns clojure-rest.handler
( : import com.mchange.v2.c3p0.ComboPooledDataSource )
( : use compojure.core )
( : use cheshire.core )
( : use ring.util.response )
( : require [ compojure.handler : as handler ]
[ ring.middleware.json : as middleware ]
[ clojure.java.jdbc : as sql ]
[ compojure.route : as route ] ) )

;URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
 ;
 ;        String username = dbUri.getUserInfo().split(":")[0];
 ;        String password = dbUri.getUserInfo().split(":")[1];
 ;        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();


( def db-config
     { : classname "com.mysql.jdbc.Driver"
     : protocol "CLEARDB_DATABASE_URL"
     : user "punter"
     : password "Janeka1974" } )

( defn pool
[ config ]
( let [ cpds ( doto ( ComboPooledDataSource. )
      ( .setDriverClass ( : classname config ) )
      ( .setJdbcUrl ( str "jdbc:" ) )
      ( .setUser ( : user config ) )
      ( .setPassword ( : password config ) )
      ( .setMaxPoolSize 6 )
      ( .setMinPoolSize 1 )
      ( .setInitialPoolSize 1 ) ) ]
{ : datasource cpds } ) )

( def pooled-db ( delay ( pool db-config ) ) )

( defn db-connection [ ] @pooled-db )