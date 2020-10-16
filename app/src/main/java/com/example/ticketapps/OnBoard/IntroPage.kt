package com.example.ticketapps.OnBoard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ticketapps.R
import kotlinx.android.synthetic.main.intro_layout.*

class IntroPage : Fragment() {
    var position = 0
    val mResources = intArrayOf(R.drawable.ic_illustration, R.drawable.ic_illustration, R.drawable.ic_illustration)
    private val mTitle = arrayOf("Easy", "Fast","Get Started")
    private val mDes = arrayOf("easy to apply and anywhere",
        "fast in searching",
        "free of charge",
    )


    fun newInstance(position: Int): IntroPage {
        val fragment = IntroPage()
        val arguments = Bundle()
        arguments.putInt("POSITION", position)
        fragment.setArguments(arguments)
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.intro_layout, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = arguments
            position = args!!.getInt("POSITION")



        intro_image.setImageDrawable(resources.getDrawable(mResources[position]))
        title.text = mTitle[position]
        description.text = mDes[position]

    }
}
