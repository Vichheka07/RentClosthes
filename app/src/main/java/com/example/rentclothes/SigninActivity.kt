package com.example.rentclothes
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rentclothes.Fragment.SigninFragment
import com.example.rentclothes.Fragment.SignupFragment
import com.example.rentclothes.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayout

class SigninActivity: AppCompatActivity (){
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(SigninFragment())
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                if (tab != null) {
                    // Handle the selected tab and replace the fragment in the FrameLayout
                    when (tab.position) {
                        0 -> {
                            // Handle the first tab (Province) selection
                            replaceFragment(SigninFragment())
                        }
                        1 -> {
                            // Handle the second tab (Post) selection
                            replaceFragment(SignupFragment())
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselected if needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselected if needed
            }
        })
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layfragment, fragment)
            .commit()
    }

}