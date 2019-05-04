package com.rodak.cocktails.cocktails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodak.cocktails.R

class CocktailsFragment : Fragment() {

    companion object {
        fun newInstance() = CocktailsFragment()
    }

    private lateinit var viewModel: CocktailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cocktails_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CocktailsViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
