package ru.chani.wallpapersetter.presentation.screens.imageItem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.chani.wallpapersetter.R
import ru.chani.wallpapersetter.databinding.FragmentImageItemBinding
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.presentation.utils.*

class ImageIItemFragment : Fragment() {

    private var imageFromBundle: Image? = null

    private var _binding: FragmentImageItemBinding? = null
    private val binding: FragmentImageItemBinding
        get() = _binding ?: throw RuntimeException("FragmentImageIItemBinding == null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            AppViewModelFactory(requireContext())
        )[ImageViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        header().hideHeader()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageFromBundle = it.getParcelable(ARG_IMAGE)
        }
        imageFromBundle?.let { viewModel.setCurrentImage(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()

        binding.fabSetWallpaper.setOnClickListener {
            viewModel.setWallpaper(binding.iv.drawable)
            showSnack()
        }
    }


    private fun setObservers() {
        viewModel.currentImage.observe(viewLifecycleOwner) { image ->
            setImageIntoView(image)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                ScreenStateError -> setScreenStateError()
                ScreenStateLoading -> setScreenStateLoading()
                ScreenStateSuccess -> setScreenStateSuccess()
            }
        }
    }

    private fun setScreenStateLoading() {
        binding.fabSetWallpaper.isEnabled = false
        binding.iv.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.llError.visibility = View.GONE
    }

    private fun setScreenStateSuccess() {
        binding.fabSetWallpaper.isEnabled = true
        binding.iv.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.llError.visibility = View.GONE
    }

    private fun setScreenStateError() {
        binding.fabSetWallpaper.isEnabled = false
        binding.iv.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.llError.visibility = View.VISIBLE
    }

    private fun setImageIntoView(image: Image) {
        Picasso.get().load(image.largeImageURL).into(binding.iv, object : Callback {
            override fun onSuccess() {
                viewModel.setStateSuccess()
            }

            override fun onError(e: Exception?) {
                viewModel.setStateError()
            }
        })
    }

    private fun showSnack() {
        Snackbar.make(
            binding.root,
            getString(R.string.wallpaper_changed),
            Snackbar.LENGTH_LONG
        ).setAnchorView(binding.fabSetWallpaper)
            .show()
    }


    companion object {

        private const val ARG_IMAGE = "ARG_IMAGE"

        @JvmStatic
        fun newInstance(image: Image) =
            ImageIItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_IMAGE, image)
                }
            }
    }
}