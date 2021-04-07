package com.funcrib.weektwotask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STACK_POSITION = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentThree.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentThree : Fragment() {

    private var stackPosition: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stackPosition = it.getString(STACK_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_three, container, false)
        /*Initialize TextView*/
        val textView = view.findViewById<TextView>(R.id.tvFragmentBlank)
        /*Set TextView Text */
        textView.text = stackPosition
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param stackPosition Parameter 1.
         * @return A new instance of fragment FragmentThree.
         */
        @JvmStatic
        fun newInstance(stackPosition: String) =
            FragmentThree().apply {
                arguments = Bundle().apply {
                    putString(STACK_POSITION, stackPosition)
                }
            }
    }
}