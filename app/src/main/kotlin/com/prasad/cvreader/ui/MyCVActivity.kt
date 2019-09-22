package com.prasad.cvreader.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.prasad.cvreader.R
import com.prasad.cvreader.databinding.ActivityPostListBinding
import com.prasad.cvreader.injection.ViewModelFactory
import com.prasad.cvreader.model.Post

class MyCVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: MyCVViewModel
    private var errorSnackbar: Snackbar? = null
    private lateinit var cvBodyContainer: View
    private lateinit var cvBasicInfo: TextView
    private lateinit var cvAddress: TextView
    private lateinit var cvPhoneNumber: TextView
    private lateinit var cvSummary: TextView
    private lateinit var cvSkills: TextView
    private lateinit var cvProjects: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)

        cvBodyContainer = findViewById(R.id.myCvBodyContainer)
        cvBasicInfo = findViewById(R.id.myCvBasicInfo)
        cvAddress = findViewById(R.id.myCvAddress)
        cvPhoneNumber = findViewById(R.id.myCVPhoneNumber)
        cvSummary = findViewById(R.id.myCVSummary)
        cvSkills = findViewById(R.id.myCVSkills)
        cvProjects = findViewById(R.id.myCVProjects)
        progressBar = findViewById(R.id.progressBar)

        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(MyCVViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.response.observe(this, Observer { response ->
            if (response != null) displayBody(response) else hideBody()
        })
        binding.viewModel = viewModel
    }

    private fun displayBody(response: Post) {
        progressBar.visibility = View.GONE
        cvBodyContainer.visibility = View.VISIBLE
        cvBasicInfo.text = getBasicInfo(response)
        cvAddress.text = response.address.toString()
        cvPhoneNumber.text = response.phoneNumber.toString()
        cvSummary.text = response.summary
        cvSkills.text = response.skills.toString()
        cvProjects.text = response.projects.toString()
    }

    private fun getBasicInfo(response: Post): String {
        val sb = StringBuilder(100)
        return sb.append(response.firstName)
                .append(" ")
                .append(response.lastName)
                .append("\n")
                .append(response.gender)
                .append("\n")
                .append(response.dob)
                .append("\n")
                .append(response.email)
                .toString()
    }

    private fun hideBody() {
        cvBodyContainer.visibility = View.GONE
    }

    private fun showError(@StringRes errorMessage: Int) {
        progressBar.visibility = View.GONE
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}