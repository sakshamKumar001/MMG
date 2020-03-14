package saksham.example.memorymatchgame

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import saksham.example.memorymatchgame.R.drawable.*
import android.widget.Toast
import android.os.Handler;


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

       lateinit var mp: MediaPlayer
        mp= MediaPlayer.create(this,R.raw.meow)
        val pictures: MutableList<Int> =
            mutableListOf(pic1, pic2, pic3, pic4, pic5, pic6, pic1, pic2, pic3, pic4, pic5, pic6)
        val victorymessages : MutableList<String> =
            mutableListOf("You win! Hit refresh or start the app again to play again.","Congrats! Hit refresh or start the app again to play again.","You caught all the pussy! Hit refresh or restart the app to play again.")
        val wrongmatchmessages : MutableList<String> =
            mutableListOf("Oops","Try again","That's the wrong pussy","Cat got your tongue?","You can do better","Paws and try again","Meee-Ow")

        val buttons: Array<Button> = arrayOf(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button10,
            button11,
            button12
        )
        val cardBack = question_mark
        var clicked = 0
        var lastClicked = -1
        var victory=0
        val handler = Handler()
        pictures.shuffle()
        victorymessages.shuffle()
        wrongmatchmessages.shuffle()
        for (i: Int in 0..11)
        {
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener{
                if (buttons[i].text == "cardBack" && clicked<2) {
                    buttons[i].setBackgroundResource(pictures[i])
                    buttons[i].setText(pictures[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                }
                if (clicked == 2 && buttons[i].text== buttons[lastClicked].text)
                {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        victory++
                        if(victory==6)
                        {
                            Toast.makeText(applicationContext,victorymessages[(0..2).random()],Toast.LENGTH_SHORT).show()
                        }
                        clicked = 0
                    }
                        else if (clicked==2 && buttons[i].text!=buttons[lastClicked].text)
                {
                    mp.start()
                    Toast.makeText(applicationContext,wrongmatchmessages[(0..6).random()],Toast.LENGTH_SHORT).show()
                    buttons[i].setBackgroundResource(pictures[i])
                    //Thread.sleep(1500)
                    handler.postDelayed({
                     buttons[lastClicked].setBackgroundResource(cardBack)
                        buttons[lastClicked].text="cardBack"
                        buttons[i].setBackgroundResource(cardBack)
                        buttons[i].text="cardBack"

                    }, 1000)


                    clicked=0
                }
                }
            }

        }
    }


