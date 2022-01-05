package xml.example.camera

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import android.Manifest
import android.graphics.Bitmap
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    // var btn = findViewById<Button>(R.id.button)
    // var img = findViewById<ImageView>(R.id.imageViev)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button)
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( this, arrayOf(Manifest.permission.CAMERA), 111)
        }
        else
            findViewById<Button>(R.id.button).isEnabled = true

        findViewById<Button>(R.id.button).setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)


    }
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==101){
            var mojObrazek: Bitmap?
            mojObrazek = data?.getParcelableExtra<Bitmap>("data")
            findViewById<ImageView>(R.id.imageView).setImageBitmap(mojObrazek)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            findViewById<Button>(R.id.button).isEnabled = true
        }
    }
}