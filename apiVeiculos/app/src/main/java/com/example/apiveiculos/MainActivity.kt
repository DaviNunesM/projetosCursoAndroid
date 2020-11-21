package com.example.apiveiculos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import model.Veiculo
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var listViewVeiculo:ListView
    lateinit var arrayListViewVeiculo:ArrayList<Veiculo>
    lateinit var veiculo: Veiculo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listViewVeiculo = findViewById(R.id.listViewVeiculo)
        arrayListViewVeiculo = ArrayList()
        carregarDadosVeiculos()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.adicionarButton->{
                veiculo = Veiculo("dav-1234", "Davi", "Nunes", 1850, 17.0)
                insert(veiculo)
                Toast.makeText(this, veiculo.toString(), Toast.LENGTH_LONG).show()
                carregarDadosVeiculos()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    fun carregarDadosVeiculos(){
        val url = "https://aulahttpphpmysql.000webhostapp.com/webservice/getAllVeiculos.php"
        val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { s->
                    val array = JSONArray(s)
                    for(i in 0..array.length()-1){
                        val objVeiculo = array.getJSONObject(i)
                        veiculo = Veiculo(
                                objVeiculo.getString("placa"),
                                objVeiculo.getString("modelo"),
                                objVeiculo.getString("marca"),
                                objVeiculo.getInt("ano"),
                                objVeiculo.getDouble("motor")
                        )
                        arrayListViewVeiculo.add(veiculo)
                    }
                    val ArrayAdapter = ArrayAdapter<Veiculo>(
                            this,
                            android.R.layout.simple_list_item_1,
                            arrayListViewVeiculo
                    )
                    listViewVeiculo.adapter = ArrayAdapter
                },
                Response.ErrorListener { VolleyError ->  })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }

    fun insert(veiculo: Veiculo){
        val url = "https://aulahttpphpmysql.000webhostapp.com/webservice/addVeiculo.php?" +
                "HTTP_PLACA=${veiculo.placa}&" +
                "HTTP_MARCA=${veiculo.marca}&" +
                "HTTP_MODELO=${veiculo.modelo}&" +
                "HTTP_ANO=${veiculo.ano}&" +
                "HTTP_MOTOR=${veiculo.motor}"
        val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { s->
                },
                Response.ErrorListener { VolleyError ->  })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }


}