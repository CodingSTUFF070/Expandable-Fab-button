package com.example.expandablefab

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.expandablefab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isExpanded = false

    private val fromBottomFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_bottom_fab)
    }
    private val toBottomFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.to_bottom_fab)
    }
    private val rotateClockWiseFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_clock_wise)
    }
    private val rotateAntiClockWiseFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_anti_clock_wise)
    }
    private val fromBottomBgAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)
    }
    private val toBottomBgAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainFabBtn.setOnClickListener {

            if (isExpanded) {
                shrinkFab()
            } else {
                expandFab()
            }

        }


        binding.galleryFabBtn.setOnClickListener {
            onGalleryClicked()
        }

        binding.galleryTv.setOnClickListener {
            onGalleryClicked()
        }

        // you can set rest of the click listeners

    }

    private fun onGalleryClicked() {
        Toast.makeText(this, "Gallery Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun shrinkFab() {

        binding.transparentBg.startAnimation(toBottomBgAnim)
        binding.mainFabBtn.startAnimation(rotateAntiClockWiseFabAnim)
        binding.galleryFabBtn.startAnimation(toBottomFabAnim)
        binding.shareFabBtn.startAnimation(toBottomFabAnim)
        binding.sendFabBtn.startAnimation(toBottomFabAnim)
        binding.galleryTv.startAnimation(toBottomFabAnim)
        binding.shareTv.startAnimation(toBottomFabAnim)
        binding.sendTv.startAnimation(toBottomFabAnim)



        isExpanded = !isExpanded
    }

    private fun expandFab() {

        binding.transparentBg.startAnimation(fromBottomBgAnim)


        binding.mainFabBtn.startAnimation(rotateClockWiseFabAnim)
        binding.galleryFabBtn.startAnimation(fromBottomFabAnim)
        binding.shareFabBtn.startAnimation(fromBottomFabAnim)
        binding.sendFabBtn.startAnimation(fromBottomFabAnim)
        binding.galleryTv.startAnimation(fromBottomFabAnim)
        binding.shareTv.startAnimation(fromBottomFabAnim)
        binding.sendTv.startAnimation(fromBottomFabAnim)


        isExpanded = !isExpanded
    }

    override fun onBackPressed() {

        if (isExpanded) {
            shrinkFab()
        } else {
            super.onBackPressed()

        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (ev?.action == MotionEvent.ACTION_DOWN) {

            if (isExpanded) {
                val outRect = Rect()
                binding.fabConstraint.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    shrinkFab()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

}