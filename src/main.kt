import java.util.logging.Level
import java.util.logging.Logger
import java.sql.SQLException

/**
 * Created by David on 5/7/2017.
 */

fun main(args: Array<String>) {
    println("Hello")
    try {
        val rs = RaptorService()
        rs.start()
    } catch (ex: SQLException) {
        Logger.getLogger(Class::class.java.name).log(Level.SEVERE, null, ex)
    } catch (ex: ClassNotFoundException) {
        Logger.getLogger(Class::class.java.name).log(Level.SEVERE, null, ex)
    }
}