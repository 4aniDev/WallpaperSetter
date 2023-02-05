package ru.chani.wallpapersetter.presentation.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.chani.wallpapersetter.R
import ru.chani.wallpapersetter.presentation.utils.header

class SplashFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)
        header().hideHeader()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}