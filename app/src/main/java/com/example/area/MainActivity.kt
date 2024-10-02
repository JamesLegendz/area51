package com.example.area

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var shapeRadioGroup: RadioGroup
    private lateinit var shapeImage: ImageView
    private lateinit var formulaText: TextView
    private lateinit var firstInput: EditText
    private lateinit var secondInput: EditText
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // การเชื่อมต่อ UI กับโค้ด
        shapeRadioGroup = findViewById(R.id.shapeRadioGroup)
        shapeImage = findViewById(R.id.shapeImage)
        formulaText = findViewById(R.id.formulaText)
        firstInput = findViewById(R.id.firstInput)
        secondInput = findViewById(R.id.secondInput)
        calculateButton = findViewById(R.id.calculateButton)

        // ตรวจจับการเปลี่ยนแปลงของ RadioGroup
        shapeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.triangleRadioButton -> {
                    shapeImage.setImageResource(R.drawable.triangle)
                    shapeImage.visibility = View.VISIBLE
                    formulaText.text = "สูตร: 0.5 × ฐาน × สูง"
                    formulaText.visibility = View.VISIBLE
                    secondInput.visibility = View.VISIBLE
                    firstInput.hint = "กรอกฐาน"
                    secondInput.hint = "กรอกสูง"
                }

                R.id.circleRadioButton -> {
                    shapeImage.setImageResource(R.drawable.circle)
                    shapeImage.visibility = View.VISIBLE
                    formulaText.text = "สูตร: π × รัศมี²"
                    formulaText.visibility = View.VISIBLE
                    secondInput.visibility = View.GONE
                    firstInput.hint = "กรอกรัศมี"
                }

                R.id.rectangleRadioButton -> {
                    shapeImage.setImageResource(R.drawable.rectangle)
                    shapeImage.visibility = View.VISIBLE
                    formulaText.text = "สูตร: กว้าง × ยาว"
                    formulaText.visibility = View.VISIBLE
                    secondInput.visibility = View.VISIBLE
                    firstInput.hint = "กรอกกว้าง"
                    secondInput.hint = "กรอกยาว"
                }
            }
        }

        // การคำนวณเมื่อกดปุ่มคำนวณ
        calculateButton.setOnClickListener {
            calculateArea()
        }
    }

    // ฟังก์ชันคำนวณพื้นที่
    private fun calculateArea() {
        val shapeType = shapeRadioGroup.checkedRadioButtonId

        val input1 = firstInput.text.toString().toDoubleOrNull()
        val input2 = secondInput.text.toString().toDoubleOrNull()

        if (input1 == null || (shapeType != R.id.circleRadioButton && input2 == null)) {
            showMessage("กรุณากรอกตัวเลขที่ถูกต้อง")
            return
        }

        val area: Double = when (shapeType) {
            R.id.triangleRadioButton -> 0.5 * input1!! * input2!!
            R.id.circleRadioButton -> Math.PI * input1!! * input1
            R.id.rectangleRadioButton -> input1!! * input2!!
            else -> 0.0
        }

        showMessage("พื้นที่: %.2f".format(area)) // แสดงพื้นที่ใน AlertDialog
    }

    // ฟังก์ชันสำหรับแสดง AlertDialog
    private fun showMessage(message: String) {
        val builder = AlertDialog.Builder(this)
        val dialogView =
            layoutInflater.inflate(R.layout.dialog_custom, null) // ใช้เลย์เอาต์ที่กำหนด
        val dialogMessage: TextView = dialogView.findViewById(R.id.dialogMessage) // ค้นหา TextView

        dialogMessage.text = message // กำหนดข้อความที่จะแสดง

        builder.setView(dialogView) // กำหนดเลย์เอาต์
            .setPositiveButton("ตกลง") { dialog, _ -> dialog.dismiss() } // ปุ่มตกลง
            .create()
            .show() // แสดง AlertDialog
        
    }
}
