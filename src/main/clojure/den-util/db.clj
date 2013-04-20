( ns clojure-rest.handler
( : import com.mchange.v2.c3p0.ComboPooledDataSource )
( : use compojure.core )
( : use cheshire.core )
( : use ring.util.response )
( : require [ compojure.handler : as handler ]
[ ring.middleware.json : as middleware ]
[ clojure.java.jdbc : as sql ]
[ compojure.route : as route ] ) )

( def db-config
{ : classname "com.mysql.jdbc.Driver"
: subprotocol "h2"
: subname "mem:documents"
: user "punter"
: password "Janeka1974" } )

( defn pool
[ config ]
( let [ cpds ( doto ( ComboPooledDataSource. )
      ( .setDriverClass ( : classname config ) )
      ( .setJdbcUrl ( str "jdbc:" ( : subprotocol config ) ":" ( : subname config ) ) )
      ( .setUser ( : user config ) )
      ( .setPassword ( : password config ) )
      ( .setMaxPoolSize 6 )
      ( .setMinPoolSize 1 )
      ( .setInitialPoolSize 1 ) ) ]
{ : datasource cpds } ) )

( def pooled-db ( delay ( pool db-config ) ) )

( defn db-connection [ ] @pooled-db )