package utils

import java.util.regex.Pattern;


/**
 * Created by David on 5/7/2017.
 */
class URL {
	private val WEB_URL = Pattern.compile("^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")

	fun clean(url: String): String {
		var url = url
		url = if (url.endsWith("/")) url.substring(0, url.length - 1) else url
		return url
	}

	// Pattern to check if this is a valid URL address
	fun isValid(url: String): Boolean {
		val m = WEB_URL.matcher(url)
		return m.matches()
	}
}