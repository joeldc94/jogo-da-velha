package com.example.jogodavelha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var isPlayer1 = true
    var gameEnd = false
    private lateinit var posTopStart: ImageView
    private lateinit var posTopCenter: ImageView
    private lateinit var posTopEnd: ImageView
    private lateinit var posCenterStart: ImageView
    private lateinit var posCenter: ImageView
    private lateinit var posCenterEnd: ImageView
    private lateinit var posBottomStart: ImageView
    private lateinit var posBottomCenter: ImageView
    private lateinit var posBottomEnd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //referência aos componentes da view
        posTopStart = findViewById(R.id.posTopStart)
        posTopCenter = findViewById(R.id.posTopCenter)
        posTopEnd = findViewById(R.id.posTopEnd)
        posCenterStart = findViewById(R.id.posCenterStart)
        posCenter = findViewById(R.id.posCenter)
        posCenterEnd = findViewById(R.id.posCenterEnd)
        posBottomStart = findViewById(R.id.posBottomStart)
        posBottomCenter = findViewById(R.id.posBottomCenter)
        posBottomEnd = findViewById(R.id.posBottomEnd)

        val botaoReset: Button = findViewById(R.id.btnReset)
        botaoReset.setOnClickListener{
            reset(posTopStart)
            reset(posTopCenter)
            reset(posTopEnd)
            reset(posCenterStart)
            reset(posCenter)
            reset(posCenterEnd)
            reset(posBottomStart)
            reset(posBottomCenter)
            reset(posBottomEnd)
        }

        configureBox(posTopStart)
        configureBox(posTopCenter)
        configureBox(posTopEnd)
        configureBox(posCenterStart)
        configureBox(posCenter)
        configureBox(posCenterEnd)
        configureBox(posBottomStart)
        configureBox(posBottomCenter)
        configureBox(posBottomEnd)
    }

    private fun configureBox(box: ImageView){
        box.setOnClickListener{
            if(box.tag==null && !gameEnd) {
                if (isPlayer1) {
                    isPlayer1 = false
                    box.setImageResource(R.drawable.ic_baseline_circle_24)
                    box.tag = 1
                } else {
                    isPlayer1 = true
                    box.setImageResource(R.drawable.ic_baseline_clear_24)
                    box.tag = 2
                }
                if(playerWin(1)){
                    Toast.makeText(this@MainActivity, "Player 1 Venceu!!!", Toast.LENGTH_LONG).show()
                } else if(playerWin(2)){
                    Toast.makeText(this@MainActivity, "Player 2 Venceu!!!", Toast.LENGTH_LONG).show()
                }
            }//else
                //Toast.makeText(this@MainActivity, "Casa já escolhida! Escolha outra!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun playerWin(value: Int): Boolean{
        if((posTopStart.tag==value && posTopCenter.tag==value && posTopEnd.tag==value)||
            (posCenterStart.tag==value && posCenter.tag==value && posCenterEnd.tag==value)||
            (posBottomStart.tag==value && posBottomCenter.tag==value && posBottomEnd.tag==value)||
            (posTopStart.tag==value && posCenterStart.tag==value && posCenterEnd.tag==value)||
            (posTopCenter.tag==value && posCenter.tag==value && posCenterEnd.tag==value)||
            (posTopEnd.tag==value && posCenterEnd.tag==value && posCenterEnd.tag==value)||
            (posTopStart.tag==value && posCenter.tag==value && posBottomEnd.tag==value)||
            (posTopEnd.tag==value && posCenter.tag==value && posBottomStart.tag==value)){
            gameEnd = true
            return true
        }
            return false
    }
    private fun reset (box: ImageView){
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }
}