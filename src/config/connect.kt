package config

import java.sql.Connection
import java.sql.DriverManager.getConnection
import java.sql.SQLException


/**
 * Created by David on 5/7/2017.
 */

private var cnx : Connection? = null

@Throws(SQLException::class, ClassNotFoundException::class)
fun connect(): Connection {
    if (cnx == null) {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            cnx = getConnection(jdbc_connection(), db_user, db_pass) as Connection
        } catch (ex: SQLException) {
            throw SQLException(ex)
        } catch (ex: ClassNotFoundException) {
            throw ClassCastException(ex.message)
        }
    }
    return cnx as Connection
}

@Throws(SQLException::class)
fun close() {
    if (cnx != null) {
        (cnx as Connection).close()
    }
}
