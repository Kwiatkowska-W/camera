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
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var actualImage = 1

        val myImage = findViewById<ImageView>(R.id.imageView)
        val myButton = findViewById<Button>(R.id.next)

        myButton.setOnClickListener {
            when(actualImage){
                1 -> {
                    myImage.setImageResource(R.drawable.zdj2)
                    actualImage++
                }
                2 -> {
                    myImage.setImageResource(R.drawable.zdj3)
                    actualImage++
                }
                3 -> {
                    myImage.setImageResource(R.drawable.zdj4)
                    actualImage++
                }
                4 -> {
                    myImage.setImageResource(R.drawable.zdj1)
                    actualImage = 1
                }
            }
        }

        val myButton2 = findViewById<Button>(R.id.previous)

        myButton2.setOnClickListener {
            when(actualImage){
                1 -> {
                    myImage.setImageResource(R.drawable.zdj4)
                    actualImage = 4
                }
                2 -> {
                    myImage.setImageResource(R.drawable.zdj1)
                    actualImage = 1
                }
                3 -> {
                    myImage.setImageResource(R.drawable.zdj2)
                    actualImage = 2
                }
                4 -> {
                    myImage.setImageResource(R.drawable.zdj3)
                    actualImage = 3
                }
            }
        }

        findViewById<Button>(R.id.finish).setOnClickListener {
            //    findViewById<Button>(R.id.przycisk).text="działa"
            findViewById<ImageView>(R.id.imageView).rotationX=findViewById<EditText>(R.id.textX).text.toString().toFloat()
            findViewById<ImageView>(R.id.imageView).scaleY=findViewById<EditText>(R.id.textY).text.toString().toFloat()
            findViewById<ImageView>(R.id.imageView).alpha=findViewById<EditText>(R.id.alpha).text.toString().toFloat()
        }

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
        findViewById<Button>(R.id.red).setOnClickListener{
            findViewById<ImageView>(R.id.imageView).setColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN)
        }
        findViewById<Button>(R.id.blue).setOnClickListener{
            findViewById<ImageView>(R.id.imageView).setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN)
        }
        findViewById<Button>(R.id.green).setOnClickListener{
            findViewById<ImageView>(R.id.imageView).setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN)
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