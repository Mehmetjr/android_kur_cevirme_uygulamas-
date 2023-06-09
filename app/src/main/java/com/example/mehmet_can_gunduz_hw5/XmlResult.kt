package com.example.mehmet_can_gunduz_hw5

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class XmlResult {


    fun date() :String{
        val url ="https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc: Document = Jsoup.connect(url).timeout(10000).ignoreContentType(true).get()
        val date = doc.getElementsByTag("Tarih_Date").attr("Tarih")

        return date

    }
    fun xmlCurr() : MutableList<Currency>{
        val arr = mutableListOf<Currency>()
        val url ="https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc: Document = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
        var elements: Elements = doc.getElementsByTag("Currency ")

        for (item in elements){
            val Unit = item.getElementsByTag("Unit").text()
            val Name = item.getElementsByTag("Isim").text()
            val ForexBuying = item.getElementsByTag("ForexBuying").text()
            val ForexSelling = item.getElementsByTag("ForexSelling").text()
            val BanknoteBuying = item.getElementsByTag("BanknoteBuying").text()
            val BanknoteSelling = item.getElementsByTag("BanknoteSelling").text()


            val currency = Currency(Unit,Name,ForexBuying, ForexSelling, BanknoteBuying, BanknoteSelling)
            arr.add(currency)

        }
        return arr
    }

}