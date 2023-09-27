import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.rentclothes.CategoriesActivity
import com.example.rentclothes.Fragment.KhmereditorFragment
import com.example.rentclothes.Fragment.TrendingnowFragment
import com.example.rentclothes.R
import com.example.rentclothes.databinding.HomeFragmentBinding


class HomeFragment:Fragment(){
    private lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel("https://i.pinimg.com/564x/ec/9e/98/ec9e986325ccf790aacbe2a33f1ebcc9.jpg"))
        imageList.add(SlideModel("https://image.wconcept.co.kr/images/img/contents/20200529_wstyle/pc_00.jpg"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/6a/47/27/6a47273d5af5cc4e4d0f697ad5e9552d.jpg"))

        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        resetButtonColors()
        startFragment2()
        changeButtonColor(binding.TrendingNow)
        binding.TrendingNow.setOnClickListener{
            val fragment = TrendingnowFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.lyFragment2bt,fragment)?.commit()
            resetButtonColors()
            changeButtonColor(binding.TrendingNow)
        }
        binding.KhmerEditor.setOnClickListener{
            resetButtonColors()
            changeButtonColor(binding.KhmerEditor)
            val fragment = KhmereditorFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.lyFragment2bt,fragment)?.commit()
        }
        binding.imKhmer.setOnClickListener{
            val intent = Intent(requireActivity(), CategoriesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun resetButtonColors() {
        val defaultColor = resources.getColor(R.color.default_button_color)
        (binding.TrendingNow.background as GradientDrawable).setColor(defaultColor)
        (binding.KhmerEditor.background as GradientDrawable).setColor(defaultColor)
    }

    private fun changeButtonColor(textView: TextView){
        val selectedColor = resources.getColor(R.color.selected_button_color)
        (textView.background as GradientDrawable).setColor(selectedColor)
    }
    private fun startFragment2(){
        val fragment = TrendingnowFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.lyFragment2bt,fragment)?.commit()
        resetButtonColors()
        changeButtonColor(binding.TrendingNow)
    }
}