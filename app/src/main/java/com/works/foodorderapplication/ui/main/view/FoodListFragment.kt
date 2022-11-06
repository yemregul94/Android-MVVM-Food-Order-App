package com.works.foodorderapplication.ui.main.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.works.foodorderapplication.R
import com.works.foodorderapplication.databinding.FragmentFoodListBinding
import com.works.foodorderapplication.ui.main.MainViewModel
import com.works.foodorderapplication.ui.main.adapter.FoodListAdapter
import com.works.foodorderapplication.ui.main.viewmodel.FoodListViewModel
import com.works.foodorderapplication.utils.BASE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodListFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentFoodListBinding
    private lateinit var viewModel: FoodListViewModel
    private lateinit var sharedViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_list, container, false)
        binding.foodListFragment = this
        binding.listToolbarTitle = getString(R.string.foodsToolbar)
        binding.userName = sharedViewModel.userName.value.toString()

        (activity as AppCompatActivity).setSupportActionBar(binding.listToolbar)

        viewModel.foodList.observe(viewLifecycleOwner){
            if(it != null){
                val adapter = FoodListAdapter(requireContext(), it, viewModel)
                binding.foodListAdapter = adapter
            }
        }

        sharedViewModel.cartCount.observe(viewLifecycleOwner){
            if(it != null){
                binding.cartCount = sharedViewModel.cartCount.value.toString()
            }else{
                binding.cartCount = "0"
            }
        }

        sharedViewModel.cartTotal.observe(viewLifecycleOwner){
            if(it != null){
                binding.cartTotal = sharedViewModel.cartTotal.value.toString()
            }else{
                binding.cartTotal = "0"
            }
        }

        viewModel.selectionFoodList.observe(viewLifecycleOwner){
            if(it != null){
                getSelection()
                getDaily()
            }
        }

        addMenu()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodListViewModel by viewModels()
        viewModel = tempViewModel

        val tempSharedViewModel: MainViewModel by activityViewModels()
        sharedViewModel = tempSharedViewModel
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.getCartCount()
        sharedViewModel.getCartTotal()
    }

    fun buttonGoToCart(){
        val nav = FoodListFragmentDirections.listToCart()
        Navigation.findNavController(requireView()).navigate(nav)
    }

    fun sortList(criteria: String){
        viewModel.sortList(criteria)
    }

    fun searchList(query: String){
        viewModel.searchList(query)
    }

    fun getDaily(){
        val dailyImage = viewModel.getDaily().food_image_name
        Glide.with(binding.ibSelection3).load("$BASE_URL/yemekler/resimler/$dailyImage").override(200, 200).into(binding.imageViewDaily)
    }

    fun goToDaily(){
        val dailyFood = viewModel.getDaily()
        val nav = FoodListFragmentDirections.listToDetails(dailyFood)
        Navigation.findNavController(requireView()).navigate(nav)
    }

    fun getSelection(){
        val foodList = viewModel.getSelection()
        Glide.with(binding.ibSelection1).load("$BASE_URL/yemekler/resimler/${foodList.get(0).food_image_name}").override(400, 400).into(binding.ibSelection1)
        Glide.with(binding.ibSelection2).load("$BASE_URL/yemekler/resimler/${foodList.get(1).food_image_name}").override(400, 400).into(binding.ibSelection2)
        Glide.with(binding.ibSelection3).load("$BASE_URL/yemekler/resimler/${foodList.get(2).food_image_name}").override(400, 400).into(binding.ibSelection3)
    }

    fun goToSelection(foodID: Int){
        val foodList = viewModel.getSelection()
        val nav = FoodListFragmentDirections.listToDetails(foodList.get(foodID))
        Navigation.findNavController(requireView()).navigate(nav)
    }

    fun filterCategories(category: Int){
        viewModel.filterCategories(category)
    }

    fun signOut(){
        sharedViewModel.signOut()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchList(query)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        searchList(query)
        return true
    }

    fun addMenu(){
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_food_list,menu)

                val item = menu.findItem(R.id.action_search_list)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@FoodListFragment)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    R.id.menu_list_sort_name_asc -> { sortList("nameAsc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_list_sort_name_desc -> { sortList("nameDesc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_list_sort_price_asc -> { sortList("priceAsc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_list_sort_price_desc -> { sortList("priceDesc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_list_logout -> { signOut()
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}