package com.ilabank.demo.viewmodels

import androidx.lifecycle.ViewModel
import com.ilabank.demo.R
import com.ilabank.demo.ui.models.SubData

/**
 * A ViewModelClass for writing a business logic. Here we can update it using Local DB, Read File from Assets or Network.
 */
class APICommonViewModel :
    ViewModel() {

    fun getList(): ArrayList<SubData> {

        val dataList = ArrayList<SubData>()

        val obj1 = SubData(R.drawable.image1, getLabelsList(1))
        dataList.add(obj1)
        val obj2 = SubData(R.drawable.image2, getLabelsList(2))
        dataList.add(obj2)
        val obj3 = SubData(R.drawable.image3, getLabelsList(3))
        dataList.add(obj3)
        val obj4 = SubData(R.drawable.image4, getLabelsList(4))
        dataList.add(obj4)
        val obj5 = SubData(R.drawable.image5, getLabelsList(5))
        dataList.add(obj5)
        val obj6 = SubData(R.drawable.image3, getLabelsList(6))
        dataList.add(obj6)

        // return model.data
        return dataList
    }

    /**
     * Function to prepare the labels list based on the image number.
     *
     * @param imageNumber :- Image number for which the labels are prepared for.
     */
    fun getLabelsList(imageNumber: Int): ArrayList<String> {

        val labelsListItems = ArrayList<String>()

        for (i in 1..20) {
            labelsListItems.add("Label $i Image $imageNumber")
            println(i)
        }

        return labelsListItems
    }
}