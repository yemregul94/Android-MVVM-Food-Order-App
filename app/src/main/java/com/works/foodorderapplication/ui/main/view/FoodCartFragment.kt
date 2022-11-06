package com.works.foodorderapplication.ui.main.view

import android.graphics.Color
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
import com.google.android.material.snackbar.Snackbar
import com.works.foodorderapplication.R
import com.works.foodorderapplication.databinding.FragmentFoodCartBinding
import com.works.foodorderapplication.ui.main.MainViewModel
import com.works.foodorderapplication.ui.main.adapter.FoodCartAdapter
import com.works.foodorderapplication.ui.main.viewmodel.FoodCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodCartFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentFoodCartBinding
    private lateinit var viewModel: FoodCartViewModel
    private lateinit var sharedViewModel: MainViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_cart, container, false)
        binding.foodCartFragment = this
        binding.cartToolbarTitle = getString(R.string.cartToolbar)
        (activity as AppCompatActivity).setSupportActionBar(binding.cartToolbar)

        viewModel.cartList.observe(viewLifecycleOwner){
            if(it != null){
                val adapter = FoodCartAdapter(requireContext(), it, viewModel)
                binding.foodCartAdapter = adapter
                binding.cartTotal = viewModel.getCartTotal()
            }
        }

       addMenu()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: FoodCartViewModel by viewModels()
        viewModel = tempViewModel
        val tempSharedViewModel: MainViewModel by activityViewModels()
        sharedViewModel = tempSharedViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.userName = sharedViewModel.userName
        viewModel.getUser()
    }

    fun buttonOrder(){
        sharedViewModel.orderList.value = viewModel.cartList.value
        viewModel.clearCart()

        val nav = FoodCartFragmentDirections.cartToResult()
        Navigation.findNavController(requireView()).navigate(nav)
    }

    fun buttonClearCart(){
        Snackbar.make(requireView(),"Sepet temizlensin mi?", Snackbar.LENGTH_LONG).setActionTextColor(
            Color.CYAN)
            .setAction("EVET"){
                viewModel.clearCart()
                Snackbar.make(requireView(),"Sepet temizlendi", Snackbar.LENGTH_SHORT).show()
            }.show()
        }

    fun sortCart(criteria: String){
        viewModel.sortCart(criteria)
    }

    fun searchCart(query: String){
        viewModel.searchCart(query)
    }

    fun signOut(){
        sharedViewModel.signOut()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchCart(query)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        searchCart(query)
        return true
    }

    fun addMenu(){
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_food_cart, menu)

                val searchItem = menu.findItem(R.id.action_search_cart)
                val searchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(this@FoodCartFragment)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    R.id.menu_cart_sort_id_asc -> { sortCart("idAsc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_id_desc -> { sortCart("idDesc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_name_asc -> { sortCart("nameAsc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_name_desc -> { sortCart("nameDesc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_price_asc -> { sortCart("priceAsc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_price_desc -> { sortCart("priceDesc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_qty_asc -> { sortCart("qtyAsc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_qty_desc -> { sortCart("qtyDesc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_total_asc -> { sortCart("totalAsc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_sort_total_desc -> { sortCart("totalDesc")
                        menuItem.isChecked = true
                    }
                    R.id.menu_cart_logout -> { signOut()
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}