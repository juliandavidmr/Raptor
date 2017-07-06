package utils

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.GenericModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by David on 5/7/2017.
 */
class Page {

    /**
     * Obtiene el contenido de la pagina solicitada
     * @param uri
     * @return html content page
     * @throws java.net.MalformedURLException
     */
    @Throws(MalformedURLException::class, IOException::class)
    fun getContentPage(uri: String): String {
        val url = URL(uri)
        val urlConnection = url.openConnection()
        val `is` = urlConnection.getInputStream()
        val isr = InputStreamReader(`is`)

        var numCharsRead: Int
        val charArray = CharArray(1024)
        val sb = StringBuilder()
        numCharsRead = isr.read(charArray)
        while (numCharsRead > 0) {
            sb.append(charArray, 0, numCharsRead)
            numCharsRead = isr.read(charArray)
        }
        return sb.toString()
    }

    fun getContenctPage(url: String): Document {
        try {
            return Jsoup.connect(url).get()
        } catch (ex: IOException) {
            Logger.getLogger(Page::class.java.name).log(Level.SEVERE, null, ex)
        }

        return Document(url)
    }

    /**
     * Get links from document
     * @param doc
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun getLinks(doc: Document): ArrayList<String> {
        val _links = ArrayList<String>()
        val links = doc.select("a[href]")

        for (link in links) {
            val hrf = link.attr("abs:href")
            _links.add(hrf)
        }
        return _links
    }

    @Throws(IOException::class)
    fun getDataPage(url: String): GenericModel {
        return getDataPage(getContenctPage(url))
    }

    @Throws(IOException::class)
    fun getDataPage(doc: Document): GenericModel {
        val dp = GenericModel()
        dp.content = doc.body().text()
        dp.description = doc.select("meta[property='og:description']").text()
        dp.ogType = doc.select("meta[property='og:type']").text()
        dp.ogUpdatedTime = doc.select("meta[property='og:updated_time']").text()
        dp.ogLocale = doc.select("meta[property='og:locale']").text()
        dp.keywords = getKeyWords(doc)
        dp.title = doc.title()
        dp.links = getLinks(doc)
        dp.url = doc.location()
        return dp
    }

    private fun getKeyWords(doc: Document): String {
        val keywordsString = doc.select("meta[name='keywords']").text()
        return Arrays.toString(keywordsString.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
    }

    @Throws(IOException::class)
    fun getLinks(url: String): ArrayList<String> {
        return getLinks(Jsoup.connect(url).get())
    }
}