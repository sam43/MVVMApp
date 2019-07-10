package com.example.mvvmapp.view.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapp.R
import com.example.mvvmapp.viewModel.TestActivityVM
import kotlinx.android.synthetic.main.activity_image_upload.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class ImageUploadActivity : AppCompatActivity() {

    private lateinit var vm: TestActivityVM
    private val map = HashMap<String, MultipartBody.Part>()
    private var part: MultipartBody.Part? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this@ImageUploadActivity).get(TestActivityVM::class.java)
        setContentView(R.layout.activity_image_upload)
        setupUI()
    }

    private fun setupUI() {
        pickImage.setOnClickListener {
            //check runtime permission
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }
        upload.setOnClickListener {
            part?.let { it1 -> vm.uploadFile2Server(this@ImageUploadActivity, it1) }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "*/*" // '*/*' for all type of files, 'image/*' for only image type files
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val imageUri = data?.data
            val path = imageUri?.let { getRealPathFromURI(applicationContext, it) }
            //Create a file object using file path
            val file = File(path)
            // Create a request body with file and image media type
            val fileReqBody = RequestBody.create(MediaType.parse("*/*"), file)
            // Create MultipartBody.Part using file request-body,file name and part name
            part = MultipartBody.Part.createFormData("files", file.name, fileReqBody)
            //Create request body with text description and text media type
            val description = RequestBody.create(MediaType.parse("text/plain"), "image-type")

            //map["files"] = part
            //map["name"] = description

            preview.setImageURI(data?.data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun getRealPathFromURI(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        } finally {
            assert(cursor != null)
            cursor?.close()
        }
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000
        //Permission code
        private val PERMISSION_CODE = 1001
    }

}