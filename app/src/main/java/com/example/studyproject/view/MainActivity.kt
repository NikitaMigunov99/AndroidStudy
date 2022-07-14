package com.example.studyproject.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studyproject.R
import com.example.studyproject.contracts.PersonalDataResultContract
import com.example.studyproject.models.PersonalDataModel
import com.example.studyproject.view.FirstActivity.Companion.FIRST_ACTIVITY_DATA
import com.example.studyproject.view.InputNameActivity.Companion.PERSONAL_DATA
import com.example.studyproject.view.SecondActivity.Companion.SECOND_ACTIVITY_DATA

class MainActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var secondNameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var phoneNumberTextView: TextView

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data?.getSerializableExtra(PERSONAL_DATA) is PersonalDataModel) {
                    (result.data?.getSerializableExtra(PERSONAL_DATA) as? PersonalDataModel)?.let {
                        setPersonalData(it)
                    }
                }
                if (result.data?.getSerializableExtra(FIRST_ACTIVITY_DATA) is PersonalDataModel) {
                    (result.data?.getSerializableExtra(FIRST_ACTIVITY_DATA) as? PersonalDataModel)?.let {
                        setPersonalData(it)
                    }
                }
                if (result.data?.getSerializableExtra(SECOND_ACTIVITY_DATA) is PersonalDataModel) {
                    (result.data?.getSerializableExtra(SECOND_ACTIVITY_DATA) as? PersonalDataModel)?.let {
                        setPersonalData(it)
                    }
                }
            }
        }

    /**
     * We will get result also in method [onActivityResult]
     */
    private val fullDataActivityResultLauncher = registerForActivityResult(PersonalDataResultContract()) {
        it?.let {
            setPersonalData(it)
        }
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.e("DEBUG", "${MainActivity::class.java} ${it.key} = ${it.value}")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    /**
     * If we start Activity with [activityResultLauncher] we will get result here too
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.getSerializableExtra(PERSONAL_DATA) is PersonalDataModel) {
            (data.getSerializableExtra(PERSONAL_DATA) as? PersonalDataModel)?.let {
                setPersonalData(it)
            }
        }
        if (data?.getSerializableExtra(FIRST_ACTIVITY_DATA) is PersonalDataModel) {
            (data.getSerializableExtra(FIRST_ACTIVITY_DATA) as? PersonalDataModel)?.let {
                setPersonalData(it)
            }
        }
    }

    /**
     * If we request permissions with [ActivityResultContracts.RequestMultiplePermissions] we will get result here too
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initView() {
        val getButtonData = findViewById<Button>(R.id.get_data_button)
        getButtonData.setOnClickListener {
            activityResultLauncher.launch(Intent(this, InputNameActivity::class.java))
        }
        val getFullButtonData = findViewById<Button>(R.id.get_full_data_button)
        getFullButtonData.setOnClickListener {
            fullDataActivityResultLauncher.launch(true)
        }
        val getPermissionsButton = findViewById<Button>(R.id.get_permissions)
        getPermissionsButton.setOnClickListener {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }

        val firstActivityButton = findViewById<Button>(R.id.first_activity_button)
        firstActivityButton.setOnClickListener {
            activityResultLauncher.launch(Intent(this, FirstActivity::class.java))
        }
        val secondActivityButton = findViewById<Button>(R.id.second_activity_button)
        secondActivityButton.setOnClickListener {
            activityResultLauncher.launch(Intent(this, SecondActivity::class.java))
        }

        val startActivityForResultButton = findViewById<Button>(R.id.start_for_result)
        startActivityForResultButton.setOnClickListener {
            /**
             * Result we will get only in method [onActivityResult]
             */
            startActivityForResult(Intent(this, InputNameActivity::class.java), 5)
        }

        nameTextView = findViewById(R.id.first_name)
        secondNameTextView = findViewById(R.id.second_name)
        ageTextView = findViewById(R.id.age)
        phoneNumberTextView = findViewById(R.id.phone_number)

        nameTextView.visibility = View.GONE
        secondNameTextView.visibility = View.GONE
        ageTextView.visibility = View.GONE
        phoneNumberTextView.visibility = View.GONE
    }

    private fun setPersonalData(data: PersonalDataModel) {
        nameTextView.text = data.name
        secondNameTextView.text = data.secondName
        nameTextView.visibility = View.VISIBLE
        secondNameTextView.visibility = View.VISIBLE
        if (data.age != 0 && data.phoneNumber.isNotEmpty()) {
            ageTextView.text = data.age.toString()
            phoneNumberTextView.text = data.phoneNumber
            ageTextView.visibility = View.VISIBLE
            phoneNumberTextView.visibility = View.VISIBLE
        } else {
            ageTextView.visibility = View.GONE
            phoneNumberTextView.visibility = View.GONE
        }
    }

    companion object {
        const val FULL_DATA = "fullData"
    }
}