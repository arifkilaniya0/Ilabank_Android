package com.ilabank.demo.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ilabank.demo.R
import com.ilabank.demo.databinding.ActivityMainBinding
import com.ilabank.demo.ui.adapters.ImagesPagerAdapter
import com.ilabank.demo.ui.adapters.ListItemAdapter
import com.ilabank.demo.utils.HorizontalMarginItemDecoration
import com.ilabank.demo.viewmodels.APICommonViewModel
import java.lang.Math.abs
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    // Instance of ViewBinding that allows you to more easily write code that interacts with views.
    private lateinit var mBinding: ActivityMainBinding

    // Instance of the ViewModel to interact with business logic.
    private lateinit var mAPICommonViewModel: APICommonViewModel

    // Instance of ListItemsAdapter
    private lateinit var mArrayItemAdapter: ListItemAdapter

    // A variable for the position of the image from Images Carousel.
    private var initialPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // Function to setup ViewModel class.
        setupViewModel()

        // Function to setup view content
        setupViewContent()
    }

    /**
     * Function to setup ViewModel class.
     */
    private fun setupViewModel() {
        mAPICommonViewModel =
            ViewModelProvider(this@MainActivity).get(APICommonViewModel::class.java)
    }

    /**
     * Function to setup ViewModel class.
     */
    private fun setupViewContent() {

        val list = mAPICommonViewModel.getList()

        mBinding.carouselViewPager.apply {
            adapter = ImagesPagerAdapter(this@MainActivity, list)
        }
        mBinding.carouselViewPager.offscreenPageLimit = 1

        // Add a PageTransformer that translates the next and previous items horizontally
        // towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }
        mBinding.carouselViewPager.setPageTransformer(pageTransformer)

        // The ItemDecoration gives the current (centered) item horizontal margin so that
        // it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            this@MainActivity,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        mBinding.carouselViewPager.addItemDecoration(itemDecoration)

        mBinding.carouselViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mArrayItemAdapter.updatedListItems(list[position].data)
            }
        })

        TabLayoutMediator(
            mBinding.indicatorTabLayout,
            mBinding.carouselViewPager
        ) { _, _ -> }.attach()

        mArrayItemAdapter = ListItemAdapter(this, list[0].data)
        mBinding.rvItems.adapter = mArrayItemAdapter

        mBinding.etSearchViewToolbar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filterSearchedItem(p0.toString())

                if (p0.toString().isNotEmpty()) {
                    mBinding.ivClearToolbarSearch.visibility = View.VISIBLE
                } else {
                    mBinding.ivClearToolbarSearch.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        mBinding.ivClearToolbarSearch.setOnClickListener {
            mBinding.etSearchViewToolbar.setText("")
        }
    }

    /**
     * Function to filter searched items from the list of items.
     *
     * @param searchedText :- The text entered for search
     */
    fun filterSearchedItem(searchedText: String) {

        val filteredListItems: ArrayList<String> = ArrayList()

        val listItems: ArrayList<String> = mAPICommonViewModel.getList()[initialPosition].data

        for (item in listItems) {
            if (item.lowercase(Locale.getDefault())
                    .contains(searchedText.lowercase(Locale.getDefault()))
            ) {
                filteredListItems.add(item)
            }
        }

        if (filteredListItems.size > 0) {
            mBinding.tvNoSearchResultAvailable.visibility = View.GONE
            mBinding.rvItems.visibility = View.VISIBLE
        } else {
            mBinding.tvNoSearchResultAvailable.visibility = View.VISIBLE
            mBinding.rvItems.visibility = View.GONE
        }

        //calling a method of the adapter class and passing the filtered list
        mArrayItemAdapter.updatedListItems(filteredListItems)
    }
}