package com.example.countbarvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // mengenalkan object view pada layout ke activity (belum diinisiasi)
    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    // STATE_RESULT adalah konstanta yang menyimpan key untuk nilai yang akan disimpan dan dipulihkan
    // menggunakan onSaveInstaceState
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    // Mendeklarasikan sebuah fungsi onCreate yang dipanggil saat aktivitas (activity) pertama kali dibuat.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Memanggil fungsi onCreate dari kelas induk (superclass), biasanya Activity.onCreate.
        super.onCreate(savedInstanceState)
        // Mengatur tata letak (layout) aktivitas dengan menggunakan file XML activity_main.
        // Fungsi ini menentukan antarmuka pengguna yang akan ditampilkan pada layar.
        setContentView(R.layout.activity_main)

        // cek apakah ada state sebelumnya
        if (savedInstanceState != null) {
            // ambil isinya menggunakan key
            val result = savedInstanceState.getString(STATE_RESULT)
            // tampilkan hasilnya
            tvResult.text = result
        }

        // inisiasinya disini
        edtLength = findViewById(R.id.edt_length)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        // ketika tombol Calculate ditekan...
        btnCalculate.setOnClickListener(this)
    }

    // metode ini dipanggil apabila Activity akan dihancurkan
    // oleh sebab itu state dari activity disimpan terlebih dahulu
    override fun onSaveInstanceState(outState: Bundle) {
        //
        super.onSaveInstanceState(outState)
        // disimpan ke dalam bundle pakai putString dengan key STATE_RESULT
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    // - maka akan melakukan hal dibawah
    override fun onClick(view: View?) {
        // jika object view yang diklik adalah button calculate
        if (view?.id == R.id.btn_calculate) {
            // mengambil length yang diinput pengguna menjadi string
            val inputLength = edtLength.text.toString().trim()
            // mengambil width yang diinput pengguna menjadi string
            val inputWidth = edtWidth.text.toString().trim()
            // mengambil height yang diinput pengguna menjadi string
            val inputHeight = edtHeight.text.toString().trim()

            // untuk mengecek apakah ada input yang null
            // jika ya, throw error
            var isEmptyField = false
            if (inputLength.isEmpty()) {
                isEmptyField = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyField = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyField = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }

            // selama tidak ada input yang null, hitung volume
            if (!isEmptyField) {
                // ubah input menjadi double lalu hitung volume balok
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                // tampilkan hasilnya ke layar dalam rupa text
                tvResult.text = volume.toString()
            }
        }
    }
}