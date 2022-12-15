package uz.gita.saiga_user.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.gita.saiga_user.databinding.DialogSuccessBinding
import uz.gita.saiga_user.utils.extensions.config

// Created by Jamshid Isoqov an 10/12/2022
class SuccessDialog(ctx: Context, private val message: String) : Dialog(ctx) {

    private lateinit var binding: DialogSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogSuccessBinding.inflate(layoutInflater)
        config(binding)
        binding.tvMessage.text = message

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}