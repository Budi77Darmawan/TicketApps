package com.example.ticketapps

<<<<<<< HEAD
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ticketapps.databinding.ActivitySearchFlightBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_searchflight.view.*
import java.text.SimpleDateFormat
import java.util.*

class SearchFlightActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySearchFlightBinding
    private lateinit var bottomSheetProject: BottomSheetDialog
    private var city_depature: String = "CGK"
    private var city_destination: String = "JFK"
    private var planeClass: String = "Economy"
    private var valueAdult: String = "1"
    private var valueChild: String = "1"
    private var years = 0
    private var months = 0
    private var days = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_flight)

        val c = Calendar.getInstance()
        years = c.get(Calendar.YEAR)
        months = c.get(Calendar.MONTH)
        days = c.get(Calendar.DAY_OF_MONTH)
        c[years, months] = days
        val format = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
        val dateString = format.format(c.time)
        binding.tvDate.text = dateString

        bottomSheetProject = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottomsheet_searchflight, null)
        bottomSheetProject.setContentView(view)

        view.number_adult.maxValue = 10
        view.number_adult.minValue = 1
        view.number_child.maxValue = 10
        view.number_child.minValue = 1

        binding.btnBack.setOnClickListener(this)
        binding.tvDate.setOnClickListener(this)
        binding.tvAdult.setOnClickListener(this)
        binding.tvChild.setOnClickListener(this)

        view.number_adult.setOnValueChangedListener { _, _, _ ->
            valueAdult = view.number_adult.value.toString()
            binding.tvAdult.text = "$valueAdult Adult"
        }
        view.number_child.setOnValueChangedListener { _, _, _ ->
            valueChild = view.number_adult.value.toString()
            binding.tvChild.text = "$valueChild Child"
        }

        binding.rgClass.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.tv_economy -> {
                    planeClass = "Economy"
                }
                R.id.tv_business -> {
                    planeClass = "Business"
                }
                R.id.tv_firstclass -> {
                    planeClass = "First Class"
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                onBackPressed()
            }
            R.id.tv_date -> {
                val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    years = year
                    months = monthOfYear
                    days = dayOfMonth
                    calendar[year, monthOfYear] = dayOfMonth
                    val format = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
                    val dateString = format.format(calendar.time)
                    binding.tvDate.text = dateString
                }, years, months, days)
                dpd.show()
            }
            R.id.tv_adult -> {
                bottomSheetProject.show()
            }
            R.id.tv_child -> {
                bottomSheetProject.show()
            }
        }
=======
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ticketapps.databinding.ActivitySearchFlightBinding
import com.example.ticketapps.searchResult.SearchResultActivity

class SearchFlightActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchFlightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_flight)
        binding.buttonSearchFlight.setOnClickListener {
            val intent = Intent(this, SearchResultActivity::class.java)
            startActivity(intent)
        }

>>>>>>> 0277b5d276ee868de57283c89ff35da22c1c0191
    }
}