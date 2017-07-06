import java.io.IOException
import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger
import models.TempVisit
import utils.Page
import utils.URL
import config.connect

/**
 * Created by David on 5/7/2017.
 */
class RaptorService : Thread() {
    var url = "http://news.ycombinator.com"
    var logs = true
    var conn: Connection = connect()!!

    fun RaptorService(conn: Connection): RaptorService {
        this.conn = conn
        return this
    }

    @Throws(SQLException::class, ClassNotFoundException::class)
    fun RaptorService(): RaptorService {
        this.conn = connect() as Connection
        return this
    }


    @Throws(SQLException::class)
    fun getLastURL(): String? {
        var t: TempVisit? = TempVisit()
        t = t!!.lastSiteToVisit(conn!!)
        return if (t != null) t.url else null
    }

    override fun run() {
        try {
            while (this.url != null) {
                val model = Page().getDataPage(this.url)

                // Save all links
                for (url_temp in model.links!!) {
                    if (URL().isValid(url_temp)) {
                        var url_temp_2 = URL().clean(url_temp)
                        val tv = TempVisit().TempVisit(url_temp_2)
                        tv.save(conn)
                    }
                }

                if (!model.title!!.trim({ it <= ' ' }).isEmpty() && !model.content!!.trim({ it <= ' ' }).isEmpty()) {
                    model.save(conn as Connection)
                }

                if (logs) {
                    System.out.println("Actual url: " + this.url)
                }
                this.url = getLastURL() as String
            }
        } catch (ex: IOException) {
            Logger.getLogger(RaptorService::class.java!!.getName()).log(Level.SEVERE, null, ex)
        } catch (ex: SQLException) {
            Logger.getLogger(RaptorService::class.java!!.getName()).log(Level.SEVERE, null, ex)
        }

    }
}