package com.example.sweetshop.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.navArgs
import com.example.sweetshop.CustPagerTransformer
import com.example.sweetshop.adapters.SlidingImageAdapter
import com.example.sweetshop.databinding.ActivityOpenHeaderBinding
import kotlinx.android.synthetic.main.fragment_header_open.*


class OpenHeaderActivity : AppCompatActivity() {
    private var _binding: ActivityOpenHeaderBinding? = null
    private val binding get() = _binding!!
    private  val args by navArgs<OpenHeaderActivityArgs>()

    private lateinit var detector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityOpenHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager()
        detector = GestureDetectorCompat(this, DiaryGestureListener())

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if(detector.onTouchEvent(event)){
            true
        }else {
            return super.onTouchEvent(event)
        }
    }

    fun viewPager(){
        val pagerAdapter = this?.let {
            SlidingImageAdapter(
                applicationContext,
                args.header.imgMax,
            )
        }
        pager.apply {
            this.adapter = pagerAdapter
            setPageTransformer(false,
                CustPagerTransformer(applicationContext)
            )
            clipToPadding = false
            //setPadding(0, 0, 0, 0)
            //pageMargin = 20
        }

        indicator.setViewPager(pager)
        val density = resources.displayMetrics.density
        indicator.radius = density * 3

        binding.icCancel.setOnClickListener {
            // requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            //requireActivity().supportFragmentManager.popBackStack()
            //findNavController().navigate(R.id.action_fragmentHeaderOpen_to_actualFragmenrt)
            finish()
        }


    }

    inner class DiaryGestureListener : GestureDetector.SimpleOnGestureListener(){
        private val SWIPE_THRESHOLD = 50
        private val SWIPE_VELOCITY_THRESHOLD = 50
        override fun onFling(
            downEvent: MotionEvent,
            moveEvent: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?:0.0F
            var diifyY = moveEvent?.y?.minus(downEvent!!.y) ?:0.0F

            return if(Math.abs(diffX)> Math.abs(diifyY))
            {
                //right,left swipe
                if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){

                    if(diffX>0){
                        this@OpenHeaderActivity.onSwipeRight()
                    }else{
                        this@OpenHeaderActivity.onSwipeLeft()
                    }
                    true
                }else{
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{

                if(Math.abs(diifyY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD){

                    if(diifyY>0){
                        this@OpenHeaderActivity.onSwipeTop()
                    }else{
                        this@OpenHeaderActivity.onSwipeBottom()
                    }
                    true
                }else{
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }

            }

        }
    }

    private fun onSwipeRight() {
        finish()

    }

    private fun onSwipeLeft() {
    }

    private fun onSwipeBottom() {
    }

    private fun onSwipeTop() {
        finish()
    }
}