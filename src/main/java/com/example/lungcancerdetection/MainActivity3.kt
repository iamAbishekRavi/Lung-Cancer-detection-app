package com.example.lungcancerdetection

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.lungcancerdetection.ml.BestFloat16
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

lateinit var Uploadbutton: Button
lateinit var Detectbutton: Button
lateinit var result: TextView
lateinit var imageView: ImageView
lateinit var bitmap: Bitmap

class MainActivity3 : AppCompatActivity() {
    var maxIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Uploadbutton = findViewById(R.id.Uploadbutton)
        Detectbutton = findViewById(R.id.Detectbutton)
        result = findViewById(R.id.result)
        imageView = findViewById(R.id.imageView)
        var labels = application.assets.open("labels.txt").bufferedReader().readLines()
        var ImageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(640, 640, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        Uploadbutton.setOnClickListener {
            var intent = Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        Detectbutton.setOnClickListener {
            var tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(bitmap)
            tensorImage = ImageProcessor.process(tensorImage)
            val model = BestFloat16.newInstance(this)

            // Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 640, 640, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(tensorImage.buffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

            outputFeature0.forEachIndexed { index, fl ->
                if (outputFeature0[maxIdx] < fl)
                    maxIdx = index
            }

            // Set the prediction result here
            result.text = labels[maxIdx]

            // Releases model resources
            model.close()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            var uri = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            imageView.setImageBitmap(bitmap)
        }
    }
}
