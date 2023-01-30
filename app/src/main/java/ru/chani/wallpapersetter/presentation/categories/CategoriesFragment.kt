package ru.chani.wallpapersetter.presentation.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.chani.wallpapersetter.R
import ru.chani.wallpapersetter.databinding.FragmentCategoriesBinding
import ru.chani.wallpapersetter.presentation.AppViewModelFactory
import ru.chani.wallpapersetter.presentation.navigator

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding: FragmentCategoriesBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoriesBinding == null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            AppViewModelFactory(requireContext())
        )[CategoryViewModel::class.java]
    }
    private lateinit var categoriesRvAdapter: CategoriesRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setObserver()

        return binding.root
    }

    private fun setupRecyclerView() {
        with(binding.rv) {
            categoriesRvAdapter = CategoriesRvAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesRvAdapter
            setClickListener()
        }
    }

    private fun setObserver() {
        viewModel.listOfCategories.observe(viewLifecycleOwner) { listOfCategories ->
            categoriesRvAdapter.setListOfCategories(listOfCategories)
        }
    }

    private fun setClickListener() {
        categoriesRvAdapter.onItemClickListener = { category ->
            navigator().goToListFragment(category)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }
}