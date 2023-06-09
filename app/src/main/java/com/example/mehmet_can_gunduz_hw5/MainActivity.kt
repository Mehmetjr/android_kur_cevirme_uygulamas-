package com.example.mehmet_can_gunduz_hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var btn_currency : Button
    lateinit var txt_sp_satis : TextView
    lateinit var txt_sp_alis : TextView
    lateinit var txt_banka_satis : TextView
    lateinit var txt_banka_alis : TextView
    lateinit var txt_cur : TextView
    lateinit var txt_tarih : TextView
    lateinit var arr : MutableList<Currency>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_cur = findViewById(R.id.txt_cur)
        btn_currency = findViewById(R.id.btn_currency)
        txt_banka_satis = findViewById(R.id.txt_banka_satis)
        txt_banka_alis = findViewById(R.id.txt_banka_alis)
        txt_sp_alis = findViewById(R.id.txt_sp_alis)
        txt_sp_satis = findViewById(R.id.txt_sp_satis)
        txt_tarih = findViewById(R.id.txt_tarih)

        val xml = XmlResult()

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        val date = xml.date()
        arr = xml.xmlCurr()
        txt_tarih.setText(date)
        //context register
        registerForContextMenu(btn_currency)

        for ( item in arr ) {
            Log.d("item", item.ForexSelling)
        }
        btn_currency.setOnClickListener(btnOnClickListener)


    }

    val btnOnClickListener = View.OnClickListener {

        val popupMenu: PopupMenu = PopupMenu(this,btn_currency)

        popupMenu.menuInflater.inflate(R.menu.currency_menu,popupMenu.menu)
        for(item in arr){
            popupMenu.getMenu().add(item.Name);
        }
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { curr ->
            var data =arr.filter {
                it.Name==curr.toString()
            }
            val value=data.get(0)
            txt_cur.text = value.Name
            txt_sp_alis.text=value.ForexBuying
            txt_sp_satis.text=value.ForexSelling
            Log.d("item", value.ForexSelling)
            txt_banka_alis.text=value.BanknoteBuying
            txt_banka_satis.text=value.BanknoteSelling

            true
        })
        popupMenu.show()
    }

}