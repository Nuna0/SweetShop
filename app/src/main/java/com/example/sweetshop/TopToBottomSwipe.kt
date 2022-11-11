package com.example.sweetshop

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

import android.view.MotionEvent

import android.widget.Scroller

import android.view.ViewConfiguration

import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout


class TopToBottomSwipe(context: Context, attrs: AttributeSet?, defStyle: Int) :
        ConstraintLayout(context, attrs, defStyle) {
        /**
         * The parent layout of the TopToBottomFinishLayout layout
         */
        private var mParentView: ViewGroup? = null

        /**
         * Minimum distance to slide
         */
        private val mTouchSlop: Int

        /**
         * X coordinate of the pressed point
         */
        private var downX = 0

        /**
         * Y coordinate of the pressed point
         */
        private var downY = 0

        /**
         * Temporary storage of X coordinates
         */
        private var tempY = 0

        /**
         * Slide class
         */
        private val mScroller: Scroller

        /**
         * Width of TopToBottomFinishLayout
         */
        private var viewHeight = 0
        private var isSilding = false
        private var onFinishListener: OnFinishListener? = null
        private var isFinish = false

        constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

        /**
         * Event interception operation
         */
        override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
            when (ev.action) {
                MotionEvent.ACTION_DOWN -> {
                    downX = ev.rawX.toInt()
                    run {
                        tempY = ev.rawY.toInt()
                        downY = tempY
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    val moveY = ev.rawY.toInt()
                    // This condition is met to block touch events of subclasses in SildingFinishLayout
                    if (Math.abs(moveY - downY) > mTouchSlop
                        && Math.abs(ev.rawX.toInt() - downX) < mTouchSlop
                    ) {
                        return true
                    }
                }
            }
            return super.onInterceptTouchEvent(ev)
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    val moveY =
                        event.rawY.toInt() // The location of the touch point relative to the screen
                    val deltaY = tempY - moveY
                    tempY = moveY
                    if (Math.abs(moveY - downY) > mTouchSlop
                        && Math.abs(event.rawX.toInt() - downX) < mTouchSlop
                    ) {
                        isSilding = true
                    }
                    if (moveY - downY >= 0 && isSilding) {
                        mParentView!!.scrollBy(0, deltaY)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    isSilding = false
                    if (mParentView!!.scrollY <= -viewHeight / 3) {
                        isFinish = true
                        scrollBottom()
                    } else {
                        scrollOrigin()
                        isFinish = false
                    }
                }
            }
            return true
        }

        override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
            super.onLayout(changed, l, t, r, b)
            if (changed) {
                mParentView = this.parent as ViewGroup
                viewHeight = this.height
            }
        }

        /***
         * Interface callback
         */
        fun setOnFinishListener(
            onSildingFinishListener: OnFinishListener?
        ) {
            onFinishListener = onSildingFinishListener
        }

        /**
         * Scroll out of the interface
         */
        private fun scrollBottom() {
            val delta = viewHeight + mParentView!!.scrollY
            mScroller.startScroll(
                0, mParentView!!.scrollY, 0, -delta + 1,
                Math.abs(delta)
            )
            postInvalidate()
        }

        /**
         * Scroll to the starting position
         */
        private fun scrollOrigin() {
            val delta = mParentView!!.scrollY
            mScroller.startScroll(
                0, mParentView!!.scrollY, 0, -delta,
                Math.abs(delta)
            )
            postInvalidate()
        }

        override fun computeScroll() {
            if (mScroller.computeScrollOffset()) {
                mParentView!!.scrollTo(mScroller.currX, mScroller.currY)
                postInvalidate()
                if (mScroller.isFinished && isFinish) {
                    if (onFinishListener != null) {
                        onFinishListener!!.onFinish()
                    } else {
                        // OnSildingFinishListener is not set to scroll to the actual position
                        scrollOrigin()
                        isFinish = false
                    }
                }
            }
        }

        interface OnFinishListener {
            fun onFinish()
        }

        init {
            mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
            mScroller = Scroller(context)
        }
    }

