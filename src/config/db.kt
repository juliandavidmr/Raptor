package config

/**
 * Created by David on 5/7/2017.
 */

val db_host = "localhost"
var db_database = "spider_raptor"
var db_user = "root"
var db_pass = "root"

fun jdbc_connection(): String {
    return "jdbc:mysql://$db_host/$db_database"
}