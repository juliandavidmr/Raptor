package models

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by David on 5/7/2017.
 */
class GenericModel {
    var title: String? = null
    var ogTitle: String? = null
    var ogDescription: String? = null
    var ogType: String? = null
    var ogUpdatedTime: String? = null
    var ogLocale: String? = null
    var description: String? = null
    var lastVisited = Date()
    var keywords: String? = null
    var content: String? = null
    var url: String? = null
    var active = true
    var orderNumber = java.util.UUID.randomUUID().toString()
    var createdAt = Date()
    var updatedAt = Date()

    // Links
    var links: ArrayList<String>? = null

    // Configs
    private val _table_name_ = "generic"

    fun getStatementInsert(): String {
        return "INSERT INTO $_table_name_ (title, ogTitle, ogDescription, ogType, ogUpdatedTime, ogLocale, description, lastVisited, keywords, content, url, active, orderNumber, createdAt, updatedAt) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
    }

    @Throws(SQLException::class)
    fun save(conn: Connection): Int {
        try {
            var index = 1
            val query: PreparedStatement = conn.prepareStatement(getStatementInsert())
            query.setString(index++, this.title)
            query.setString(index++, this.ogTitle)
            query.setString(index++, this.ogDescription)
            query.setString(index++, this.ogType)
            query.setString(index++, this.ogUpdatedTime)
            query.setString(index++, this.ogLocale)
            query.setString(index++, this.description)
            query.setObject(index++, this.lastVisited)
            query.setString(index++, this.keywords)
            query.setString(index++, this.content)
            query.setString(index++, this.url)
            query.setBoolean(index++, this.active)
            query.setString(index++, this.orderNumber)
            query.setObject(index++, this.createdAt)
            query.setObject(index++, this.updatedAt)

            val rows_affected = query.executeUpdate()
            return rows_affected
        } catch (ex: SQLException) {
            throw SQLException(ex)
        }
    }
}