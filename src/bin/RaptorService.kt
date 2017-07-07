package bin

import java.io.IOException
import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger
import models.TempVisit
import utils.Page
import utils.URL
import config.connect
import config.logs

/**
 * Created by David on 5/7/2017.
 */
class RaptorService constructor(uri: String? = null) : Thread() {

	var url = if (uri != null) uri else "http://news.ycombinator.com"	// Initial url for scan
	var conn: Connection = connect()!!

	@Throws(SQLException::class)
	fun getLastURL(): String? {
		var t = TempVisit("")!!.lastSiteToVisit(conn!!)
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
						val tv = TempVisit(url_temp_2)
						tv.save(conn)
					}
				}

				if (!model.title!!.trim({ it <= ' ' }).isEmpty() && !model.content!!.trim({ it <= ' ' }).isEmpty()) {
					model.save(conn)
					val result_update = TempVisit(this.url).update(conn)
					if (logs) {
						println("Visited url :result: $result_update")
					}
				}

				if (logs) {
					println("Actual url: ${this.url}")
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