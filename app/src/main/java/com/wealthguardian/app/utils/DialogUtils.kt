package com.wealthguardian.app.utils
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wealthguardian.app.R
import com.wealthguardian.app.databinding.DialogLinkAaBinding
import com.wealthguardian.app.databinding.DialogVaultUploadBinding

object DialogUtils {
    fun showLinkAADialog(context: Context) {
        val b = DialogLinkAaBinding.inflate(LayoutInflater.from(context))
        val d = MaterialAlertDialogBuilder(context, R.style.Theme_WealthGuardian_Dialog).setView(b.root).create()
        b.btnConfirm.setOnClickListener { Toast.makeText(context,"Account linked via AA!",Toast.LENGTH_SHORT).show(); d.dismiss() }
        b.btnCancel.setOnClickListener { d.dismiss() }
        d.show()
    }
    fun showVaultUploadDialog(context: Context, onImport: () -> Unit) {
        val b = DialogVaultUploadBinding.inflate(LayoutInflater.from(context))
        val d = MaterialAlertDialogBuilder(context, R.style.Theme_WealthGuardian_Dialog).setView(b.root).create()
        b.btnUploadDevice.setOnClickListener { Toast.makeText(context,"Document uploaded!",Toast.LENGTH_SHORT).show(); d.dismiss() }
        b.btnImportDigilocker.setOnClickListener { Toast.makeText(context,"Importing from DigiLocker...",Toast.LENGTH_SHORT).show(); d.dismiss(); onImport() }
        b.btnClose.setOnClickListener { d.dismiss() }
        d.show()
    }
    fun showRevokeConsentDialog(context: Context, name: String, onRevoke: () -> Unit) {
        MaterialAlertDialogBuilder(context, R.style.Theme_WealthGuardian_Dialog)
            .setTitle("Revoke Consent")
            .setMessage("Revoke data access for $name? This stops fetching data from this institution.")
            .setPositiveButton("Revoke") { _, _ -> onRevoke() }
            .setNegativeButton("Cancel", null).show()
    }
}
