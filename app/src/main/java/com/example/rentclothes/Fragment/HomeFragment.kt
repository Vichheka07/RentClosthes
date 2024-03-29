import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.rentclothes.adapter.ProductsAdapter
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.CategoriesActivity
import com.example.rentclothes.Fragment.KhmereditorFragment
import com.example.rentclothes.R
import com.example.rentclothes.viewModel.HomeScreenViewModel
import com.example.rentclothes.databinding.HomeFragmentBinding
import com.example.rentclothes.viewModel.SigninScreenViewModel


class HomeFragment:Fragment(){
    private val viewModel = HomeScreenViewModel()
    private lateinit var binding: HomeFragmentBinding
    private val name = listOf<String>("KHMER","GRADUATION","WEDDING","WEEKEND","HOME")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intent = Intent(requireActivity(), CategoriesActivity::class.java)
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>() // Create image list
        changeStatusBarColor(R.color.background_color_home)



        imageList.add(SlideModel("https://i.pinimg.com/564x/ec/9e/98/ec9e986325ccf790aacbe2a33f1ebcc9.jpg"))
        imageList.add(SlideModel("https://image.wconcept.co.kr/images/img/contents/20200529_wstyle/pc_00.jpg"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/6a/47/27/6a47273d5af5cc4e4d0f697ad5e9552d.jpg"))

        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        resetButtonColors()
        changeButtonColor(binding.TrendingNow)
        binding.TrendingNow.setOnClickListener{
            resetButtonColors()
            changeButtonColor(binding.TrendingNow)
        }
        binding.KhmerEditor.setOnClickListener{
            resetButtonColors()
            changeButtonColor(binding.KhmerEditor)
        }
        binding.imKhmer.setOnClickListener{
            intent.putExtra("name", name[0]);
            startActivity(intent)
        }
        binding.imGraduate.setOnClickListener{
            intent.putExtra("name", name[1]);
            startActivity(intent)
        }
        binding.imWedding.setOnClickListener{
            intent.putExtra("name", name[2]);
            startActivity(intent)
        }
        binding.imWeekend.setOnClickListener{
            intent.putExtra("name", name[3]);
            startActivity(intent)
        }
        binding.imHome.setOnClickListener{
            intent.putExtra("name", name[4]);
            startActivity(intent)
        }
        viewModel.loadHomeScreen()
        viewModel.homescreenData.observe(viewLifecycleOwner) { resource ->
            if (resource.status == Status.SUCCESS) {
                val data = resource.data?.data
                data?.let { items ->
                    showProducts(items)
                }
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun showProducts(items: List<Datum>) {
        val layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView1.layoutManager = layoutManager

        val adapter = ProductsAdapter()
        adapter.setUserList(items)
        binding.recyclerView1.adapter = adapter
    }



    private fun resetButtonColors() {
        val defaultColor = resources.getColor(R.color.default_button_color)
        (binding.TrendingNow.background as GradientDrawable).setColor(defaultColor)
        (binding.KhmerEditor.background as GradientDrawable).setColor(defaultColor)
    }

    private fun changeButtonColor(textView: TextView){
        val selectedColor = resources.getColor(R.color.purple_700)
        (textView.background as GradientDrawable).setColor(selectedColor)
    }
    private fun changeStatusBarColor(colorResId: Int) {
        activity?.let { fragmentActivity ->
            // Check if the version is at least Lollipop (API level 21)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window: Window = fragmentActivity.window
                val color: Int = ContextCompat.getColor(fragmentActivity, colorResId)

                // Set the status bar color
                window.statusBarColor = color
            }
        }
    }


}

