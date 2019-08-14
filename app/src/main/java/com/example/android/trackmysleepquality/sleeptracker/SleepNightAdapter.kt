/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

// was able to change this because we defined the ViewHolder class down below
//class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
    var data = listOf<SleepNight>()
        // ((!!)) to here (from bottom)
        set(value) {
            // to actually set the value in the setter
            // then once the data is updated, it tells the recylcler view that the entire dataset has
                // changed and recycler view should redraw everthing on screen right away
            field = value
            // tells the recyclerview that the entire list is potentially invalid
            notifyDataSetChanged()
            // becuase of this call, recycler view rebinds and redraws every item in the list
        }

    // recycler view needs to know how many items it will be displaying
    override fun getItemCount(): Int {
        return data.size
    }

    // AFTER CHANGING THE SIGNIGURE OF THE CLASS THIS METHOD HAS TO CHANGE (goes to ((**)))
//    // position is the position in the list we are supposed to be binding
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = data[position]
//        // when a view scross off the screen the recycler view will notice and reuse its view to
//            // display the next data
//        if (item.sleepQuality <= 1) {
//            holder.textView.setTextColor(Color.RED)
//        } else {
//            // want to reset the color black
//            holder.textView.setTextColor(Color.BLACK)
//        }
//        holder.textView.text = item.sleepQuality.toString()
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
//        val res = holder.itemView.context.resources

        // START HERE
        // because the code in this block mixes up display code with manage view holder code
        // onbind viewholder knows too much
        // becasue recycler view supports multiple types of view holders this code should be moved elsewhere
        // that is why we extracted it into bind
        // STEP 1: extract it into a function, bind, and then next we will put it into the viewholder class
        holder.bind(item)

        // STOP HERE


    }
    // then it changed to this
//    private fun bind(holder: ViewHolder, item: SleepNight, res: Resources) {
//        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//        holder.quality.text = convertNumericQualityToString(item.sleepQuality, res)
//
//        holder.qualityImage.setImageResource(when (item.sleepQuality) {
//            0 -> R.drawable.ic_sleep_0
//            1 -> R.drawable.ic_sleep_1
//            2 -> R.drawable.ic_sleep_2
//            3 -> R.drawable.ic_sleep_3
//            4 -> R.drawable.ic_sleep_4
//            5 -> R.drawable.ic_sleep_5
//            else -> R.drawable.ic_sleep_active
//        })
//    }

    // then because we want to turn this function into an extention on ViewHolder, we want bind
        // to be a member of view holder
    // that is why we converted parameter to receiver (convert to extension function)
    // then we cut from here and put it in viewholder
//    private fun ViewHolder.bind(item: SleepNight, res: Resources) {
//        sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//        quality.text = convertNumericQualityToString(item.sleepQuality, res)
//
//        qualityImage.setImageResource(when (item.sleepQuality) {
//            0 -> R.drawable.ic_sleep_0
//            1 -> R.drawable.ic_sleep_1
//            2 -> R.drawable.ic_sleep_2
//            3 -> R.drawable.ic_sleep_3
//            4 -> R.drawable.ic_sleep_4
//            5 -> R.drawable.ic_sleep_5
//            else -> R.drawable.ic_sleep_active
//        })
//    }

    // parent is the view group into which the new view will be added after its bound to an adapter position
    // aka view will be added to aview group before it is displayed to the screen
    // view type is needed to render multiple views in the same recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        // can create a layout inflator from any view by passing in the context
////        // means we will create a layout inflater based on the partent view
////        val layoutInflater = LayoutInflater.from(parent.context)
////
////        // layout inflator needs to be told about the parent so it can set up the layout correctly
////        // TBH any time you use the layoutInflator.inflate it will always take the layout as the
////            // first arg, and the second and third args will always be the same
////// 1      val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
////
////        // since we are now inflating a constraint layout, we dont need to case to a TextView ^^^^
////        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
////
////        // now we need to wrap up the view in a holder and pass it back to the recycler view
////        return ViewHolder(view)

        // change the companion because is now in ViewHolder
//        return Companion.from(parent)
        return ViewHolder.from(parent)
    }

    // becuase this function never uses the `this` keyword, we can move it to a companion object
//    fun from(parent: ViewGroup): ViewHolder {
//        // can create a layout inflator from any view by passing in the context
//        // means we will create a layout inflater based on the partent view
//        val layoutInflater = LayoutInflater.from(parent.context)
//
//        // layout inflator needs to be told about the parent so it can set up the layout correctly
//        // TBH any time you use the layoutInflator.inflate it will always take the layout as the
//        // first arg, and the second and third args will always be the same
//        // 1      val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
//
//        // since we are now inflating a constraint layout, we dont need to case to a TextView ^^^^
//        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
//
//        // now we need to wrap up the view in a holder and pass it back to the recycler view
//        return ViewHolder(view)
//    }

    // need to let the recycler view know when the data changes
    // go back to the top! ((!!))



    // good idea to hold a refernce to the views that this viewholder will updtae
    // every time you bind to the view holder you need to access the image and both text views
    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)

            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
                // now we need to wrap up the view in a holder and pass it back to the recycler view
                return ViewHolder(view)
            }
        }
    }




}