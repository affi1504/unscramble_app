package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord: String

    val score: Int get() = _score
    val currentWordCount: Int get() = _currentWordCount
    val currentScrambledWord: String get() = _currentScrambledWord

    init {
        getNextWord()
    }

    private fun getNextWord(){
    currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        while (String(tempWord).equals(currentWord,false)){
            tempWord.shuffle()
        }
        if(wordList.contains(currentWord)){
            getNextWord()
        }
        else{
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordList.add(currentWord)
        }
    }

    fun nextWord(): Boolean{
        return if (_currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else false
    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String):Boolean {
        return if(playerWord.equals(currentWord,true)){
            increaseScore()
            true
        }
        else false
    }

    fun reintitalizeData(){
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }


}