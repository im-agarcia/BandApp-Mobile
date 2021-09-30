package grupo1.tp3.ort.bandapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            //pantalla que se abre cuando inicia la aplicacion (this)
        startActivity(Intent(this,MainActivity::class.java))
    }
}