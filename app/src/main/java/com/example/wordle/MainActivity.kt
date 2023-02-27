package com.example.wordle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var guessNum=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val reset=findViewById<Button>(R.id.resetButton)
        reset.isEnabled=false
        val submitbutton = findViewById<Button>(R.id.guessbutton)
        val finalWord=findViewById<TextView>(R.id.SecretWord)
        finalWord.text=wordToGuess
        //finalWord.visibility = View.VISIBLE
        Toast.makeText(this, wordToGuess, Toast.LENGTH_LONG).show()
        fun checkGuess(guess: String) : String {
            var result = ""

            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    result += "O"
                }
                else if (guess[i] in wordToGuess) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            return result
        }

        fun closeKeyboard() {
            val view = this.currentFocus
            if(view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
        val userInput=findViewById<EditText>(R.id.et_simple)
        submitbutton.isEnabled=false
        userInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isTextValid = s?.length == 4
                submitbutton.isEnabled = isTextValid
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })

        submitbutton.setOnClickListener {
            if(guessNum==1){
                val group1 = findViewById<ConstraintLayout>(R.id.guess1attr)
                val guess1 = findViewById<TextView>(R.id.Guess1)
                val guess1Hint = findViewById<TextView>(R.id.Guess1Check)
                guess1.text = userInput.text.toString()

                val compareEntry= (guess1.text as String).uppercase()
                val guess1result = checkGuess(compareEntry)
                guess1Hint.text = guess1result
                group1.visibility=View.VISIBLE
                guessNum++
                closeKeyboard()
                if(guess1result=="OOOO"){
                    Toast.makeText(this, "You guessed the word", Toast.LENGTH_LONG).show()
                    finalWord.visibility = View.VISIBLE
                    finalWord.text = wordToGuess
                }
                else{
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show()
                }
            }
            else if(guessNum==2){
                val group2 = findViewById<ConstraintLayout>(R.id.guess2attr)
                val guess2 = findViewById<TextView>(R.id.Guess2)
                val guess2Hint = findViewById<TextView>(R.id.Guess2Check)
                guess2.text = userInput.text.toString()

                val compareEntry= (guess2.text as String).uppercase()
                val guess2result = checkGuess(compareEntry)
                guess2Hint.text = guess2result
                group2.visibility=View.VISIBLE
                guessNum++
                closeKeyboard()
                if(guess2result=="OOOO"){
                    Toast.makeText(this, "You guessed the word", Toast.LENGTH_LONG).show()
                    finalWord.visibility = View.VISIBLE
                    finalWord.text = wordToGuess
                }
                else{
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show()
                }
            }
            else if(guessNum==3){
                val group3 = findViewById<ConstraintLayout>(R.id.guess3attr)
                val guess3 = findViewById<TextView>(R.id.Guess3)
                val guess3Hint = findViewById<TextView>(R.id.Guess3Check)
                guess3.text = userInput.text.toString()

                val compareEntry= (guess3.text as String).uppercase()
                val guess3result = checkGuess(compareEntry)
                guess3Hint.text = guess3result
                group3.visibility=View.VISIBLE
                guessNum++
                closeKeyboard()
                if(guess3result=="OOOO"){
                    Toast.makeText(this, "You guessed the word", Toast.LENGTH_LONG).show()
                    finalWord.visibility = View.VISIBLE
                    finalWord.text = wordToGuess
                }
                else{
                    if(guessNum==4) {
                        Toast.makeText(this, "Incorrect. Number of tries has been reached", Toast.LENGTH_LONG).show()
                        finalWord.visibility = View.VISIBLE
                        finalWord.text = wordToGuess
                        submitbutton.isEnabled=false
                        reset.isEnabled=true
                    }
                }
            }
        }
        reset.setOnClickListener(){
            guessNum=1
            recreate()
        }
    }

}