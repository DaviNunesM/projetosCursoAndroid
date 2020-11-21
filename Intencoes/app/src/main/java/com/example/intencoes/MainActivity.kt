package com.example.intencoes

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun AbrirCamera(view: View){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    fun Calendario(view: View){
        val intent = Intent(this, calendario::class.java)
        intent.putExtra("nome","Davi")
        startActivity(intent)
    }


    fun Notificacao(){
        val builder = NotificationCompat.Builder(this).setSmallIcon(
            R.drawable.add_photo).setContentTitle("Exemplo de notificação").setContentText("Esse é um teste de notificação");
        val notificationIntent = Intent(this, MainActivity::class.java);
        val contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager;
        if (manager != null) {
            manager.notify(0,builder.build())
        };
    }

}