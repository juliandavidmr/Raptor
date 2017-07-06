package models


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by David on 5/7/2017.
 */
class TempVisit {


    var id: Int = 0
    var url: String = ""
    var visited: Date? = null
    var createAt = Date()
    var updateAt = Date()

    fun TempVisit(url: String): TempVisit {
        this.url = url
        return this
    }

    fun TempVisit(id: Int, url: String): TempVisit {
        this.id = id
        this.url = url
        return this
    }

    private val _table_name_ = "tempvisit"


    fun getStatementSelectFist(): String {
        return "SELECT * FROM $_table_name_	WHERE visited IS NULL LIMIT 1"
    }

    @Throws(SQLException::class)
    fun lastSiteToVisit(conn: Connection): TempVisit? {
        var tv: TempVisit? = null
        try {
            val consulta = conn.prepareStatement(getStatementSelectFist())
            val resultado = consulta.executeQuery()
            while (resultado.next()) {
                tv = TempVisit(resultado.getInt("id"), resultado.getString("url"))
            }
        } catch (ex: SQLException) {
            throw SQLException(ex)
        }

        return tv
    }

    /**
     * Save a data url to visit
     * @param conn
     * @return
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun save(conn: Connection): Int {
        try {
            val query = conn.prepareStatement("SELECT PR_INSERT_VISIT(?) AS res")
            query.setString(1, this.url)
            val result = query.executeQuery()
            try {
                return result.getInt("res")
            } catch (e: SQLException) {
                return 0
            }
        } catch (ex: SQLException) {
            throw SQLException(ex)
        }
    }
}