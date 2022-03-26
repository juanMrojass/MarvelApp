package com.manuelmarvelapp.ui.main.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.manuelmarvelapp.ui.main.view.MainActivity

open class BaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) : Fragment() {

    private var _binding: T? = null
    private val binding: T get() = _binding!!

    val activity: AppCompatActivity by lazy {
        requireActivity() as MainActivity
    }

    open fun T.initialize() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.initialize()
        return binding.root
    }
}