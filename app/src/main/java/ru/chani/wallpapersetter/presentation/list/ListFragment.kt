package ru.chani.wallpapersetter.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.chani.wallpapersetter.databinding.FragmentListBinding
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.presentation.AppViewModelFactory
import ru.chani.wallpapersetter.presentation.navigator


class ListFragment : Fragment() {
    private var category: Category? = null

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding ?: throw RuntimeException("FragmentListBinding == null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            AppViewModelFactory(requireContext(), category!!)
        )[ListViewModel::class.java]
    }

    private val imageListRvAdapter = ImageListRvAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getParcelable(ARG_CATEGORY) as Category?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.listOfImages.observe(viewLifecycleOwner) {listOfImages ->
           imageListRvAdapter.setNewList(listOfImages)
        }

    }

    private fun setupRecyclerView() {
        with(binding.rv) {
            layoutManager = StaggeredGridLayoutManager(COLUMN_COUNT, GridLayoutManager.VERTICAL)
            adapter = imageListRvAdapter
            setClickListener()
        }
    }

    private fun setClickListener() {
        imageListRvAdapter.onItemClickListener = { imageItem ->
            navigator().goToImageItemFragment(imageItem)
        }
    }

    companion object {

        private const val ARG_CATEGORY = "ARG_CATEGORY"
        private const val COLUMN_COUNT = 2

        @JvmStatic
        fun newInstance(category: Category) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CATEGORY, category)
                }
            }
    }
}