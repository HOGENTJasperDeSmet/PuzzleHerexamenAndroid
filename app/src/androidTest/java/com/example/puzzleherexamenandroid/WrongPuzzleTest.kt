package com.example.puzzleherexamenandroid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test


@LargeTest
class WrongPuzzleTest {

    /*
    * Test: fout oplossen van een puzle
    * */

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun failPuzzle() {

        val buttonPlay = onView(
            allOf(
                withId(R.id.continueGame), withText(R.string.play)
                ,isDisplayed()
            )
        )
        buttonPlay.perform(click())


        Thread.sleep(1000)

        val buttonPuzzle = onView(
            allOf(
                withId(R.id.puzzleNumber), withText("001")
                ,isDisplayed()
            )
        )
        buttonPuzzle.perform(click())

        val buttonDialogue = onView(
            allOf(
                withId(R.id.dialogueText)
                ,isDisplayed()
            )
        )
        buttonDialogue.perform(click())


        val drawView = onView(
            withId(R.id.drawingView)
        )
        drawView.perform(swipeDown())

        val buttonSubmit = onView(
            withId(R.id.submit)
        )
        buttonSubmit.perform(click())

        val solvedTitle = onView(
            allOf(
                withId(R.id.solvedTitle), withText(R.string.wrong)
                ,isDisplayed()
            )
        )


    }

}