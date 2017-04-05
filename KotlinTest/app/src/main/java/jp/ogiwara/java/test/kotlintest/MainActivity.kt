package jp.ogiwara.java.test.kotlintest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.button1) as Button
        button.text = BottomNavigationActivity::class.java.simpleName
        button.setOnClickListener {
            val intent = Intent(this@MainActivity,BottomNavigationActivity::class.java)
            startActivity(intent)
        }
        val button2 = findViewById(R.id.button2) as Button
        button2.text = ScrollingActivity::class.java.simpleName
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity.applicationContext,ScrollingActivity::class.java)
            startActivity(intent)
        }
        val button3 = findViewById(R.id.button3) as Button
        button3.text = AnimateActivity::class.java.simpleName
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity.applicationContext,AnimateActivity::class.java)
            startActivity(intent)
        }
    }
}
