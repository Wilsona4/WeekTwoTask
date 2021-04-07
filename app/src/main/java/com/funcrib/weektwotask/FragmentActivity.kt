package com.funcrib.weektwotask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : AppCompatActivity() {

    private lateinit var fragmentTransaction: FragmentTransaction
    var stackPosition = supportFragmentManager.backStackEntryCount + 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        /*Initialise Default Fragment*/
        initFragment()

        /*Add a new fragment on each button click*/
        btAddFragment.setOnClickListener {
            addFragment()
        }

        /*Remove Fragment from top of back stack or return to main activity if stack is empty*/
        btRemoveFragment.setOnClickListener {
            /*Return to main activity if that's the last fragment in the stack*/
            if (supportFragmentManager.backStackEntryCount == 1) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                //Remove Fragment at the top
                supportFragmentManager.popBackStack()
            }
        }
    }

    /*Method to Add Fragment Dynamically*/
    private fun addFragment() {
        val fragment: Fragment
        /*initialize stackPosition to backStackCount*/
        stackPosition = supportFragmentManager.backStackEntryCount + 1

        /*Create a bundle to hold data to be passed between activity and fragments*/
        val bundle = Bundle()
        bundle.putInt("STACK_POSITION", stackPosition)

        /*Create an Instance of each fragment class and pass argument to fragment based on the stack position*/
        when(stackPosition) {
            1 -> {
                fragment = FragmentOne()
                fragment.arguments = bundle
            }
            2 -> {
                fragment =FragmentTwo()
                fragment.arguments = bundle
            }
            3 -> {
                fragment = FragmentThree.newInstance(
                    "BackStack Position: $stackPosition"
                )
            }
            else -> {
                fragment = FragmentOne()
                fragment.arguments = bundle
            }
        }
        /*Add fragment dynamically based on the backStack position*/
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
            commit()
        }
    }

    /*Method to Add Initial Fragment*/
    private fun initFragment() {
        stackPosition = supportFragmentManager.backStackEntryCount + 1
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainer, FragmentThree.newInstance(
                "BackStack Position: $stackPosition"))
            addToBackStack(null)
            commit()
        }
    }

}

