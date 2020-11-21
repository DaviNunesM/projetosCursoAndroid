package com.example.intencoes

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.text.SimpleDateFormat

class calendario : AppCompatActivity() {
    lateinit var formatData:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        val calendario_view:CalendarView = findViewById(R.id.calendario_view)
        calendario_view.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Toast.makeText(this,"Ano: "+year,Toast.LENGTH_LONG).show();
            formatData = "$dayOfMonth/$month/$year"
            Toast.makeText(this, formatData, Toast.LENGTH_LONG).show();
        }


        val nome = findViewById<TextView>(R.id.nome)
        val intent = intent;
        val pegar = intent.extras;
// colocar dentro do TextView dessa tela
        nome.text = pegar!!["nome"].toString()
    }


    fun VerData(view: View){
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val calendario_view:CalendarView = findViewById(R.id.calendario_view)
        val intent = Intent(this, VerData::class.java)
        intent.putExtra("data",sdf.format(formatData))
        startActivity(intent)
    }

    fun Notificacao(view: View){
        val builder = NotificationCompat.Builder(this).setSmallIcon(
            R.drawable.add_photo).setContentTitle("Exemplo de notificação").setContentText("Data");
        val notificationIntent = Intent(this, MainActivity::class.java);
        val contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager;
        if (manager != null) {
            manager.notify(0,builder.build())
        };
    }
}