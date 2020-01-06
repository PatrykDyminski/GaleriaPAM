package com.example.galeria

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.pwittchen.swipe.library.rx2.Swipe
import com.github.pwittchen.swipe.library.rx2.SwipeListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private lateinit var gestureDetector: GestureDetector
    private val swipe = Swipe()

    companion object {
        private const val redText = "To jest super telewizor"
        private const val blueText = "To jest genialny regał"
        private const val greenText = "To są dzbany"

        private const val NUMBER_OF_IMAGES = 200
        var imageNumber = 1
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(
        downEvent: MotionEvent?,
        moveEvent: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        swipe.dispatchTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        klatki.setImageResource(R.drawable.k1)

        gestureDetector = GestureDetector(this, this)

        swipe.setListener(object : SwipeListener {
            override fun onSwipingLeft(event: MotionEvent) {
                imageNumber += 2
                if (imageNumber > NUMBER_OF_IMAGES) {
                    imageNumber = NUMBER_OF_IMAGES
                }
                val id = resources.getIdentifier("k$imageNumber", "drawable", packageName)
                val obraz = resources.getDrawable(id, theme)
                klatki.setImageDrawable(obraz)
                checkKlatka()
            }

            override fun onSwipedLeft(event: MotionEvent): Boolean {
                return false
            }

            override fun onSwipingRight(event: MotionEvent) {
                imageNumber -= 2
                if (imageNumber <= 1) {
                    imageNumber = 1
                }
                val id = resources.getIdentifier("k$imageNumber", "drawable", packageName)
                val obraz = resources.getDrawable(id, theme)
                klatki.setImageDrawable(obraz)
                checkKlatka()
            }

            override fun onSwipedRight(event: MotionEvent): Boolean {
                return false
            }

            override fun onSwipingUp(event: MotionEvent) {
                //info.setText("SWIPING_UP")
            }

            override fun onSwipedUp(event: MotionEvent): Boolean {
                //info.setText("SWIPED_UP")
                return false
            }

            override fun onSwipingDown(event: MotionEvent) {
                //info.setText("SWIPING_DOWN")
            }

            override fun onSwipedDown(event: MotionEvent): Boolean {
                //info.setText("SWIPED_DOWN")
                return false
            }
        })
    }

    private fun checkKlatka() {

        val builder = AlertDialog.Builder(this)

        when (imageNumber) {
            57 -> {
                builder.setTitle("Telewizor")
                builder.setMessage(redText)

                builder.setPositiveButton("OK") { _, _ -> }
                val dialog: AlertDialog = builder.create()
                dialog.create()
                dialog.show()
            }
            115 -> {
                builder.setTitle("Regał")
                builder.setMessage(blueText)

                builder.setPositiveButton("OK") { _, _ -> }
                val dialog: AlertDialog = builder.create()
                dialog.create()
                dialog.show()
            }
            187 -> {
                builder.setTitle("Wazony")
                builder.setMessage(greenText)

                builder.setPositiveButton("OK") { _, _ -> }
                val dialog: AlertDialog = builder.create()
                dialog.create()
                dialog.show()
            }
        }
    }
}
