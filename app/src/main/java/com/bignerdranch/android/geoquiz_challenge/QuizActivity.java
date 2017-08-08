package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Displays a True/False question for user to answer, and gives feedback on whether user was correct.
 * <p>
 * Project taken from <cite>Android Programming: The Big Nerd Ranch Guide</cite>, 2nd edition,
 * by Bill Phillips, Chris Stewart, Brian Hardy and Kristin Marsicano.
 *
 * @author  Derek Tran
 * @version 1.0
 * @since   2017-06-29
 */

// AppCompatActivity is a subclass of Activity that provides compatibility support for older
// versions of Android.
public class QuizActivity extends AppCompatActivity
{

    // Widgets
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    // Array of Question objects
    private Question[] mQuestionBank = new Question[]
    {
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true),
    };

    // Index of mQuestionBank used to track question displayed in app
    private int mCurrentIndex = 0;

    // onCreate called when an instance of the activity subclass is created.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(int layoutResID) inflates a layout and puts it on screen.
        // Each widget in layout file is instantiated as defined by its attributes.
        // Layout to inflate specified by passing layout's resource ID. (resource is piece of app that's not code)
        setContentView(R.layout.activity_quiz);

        // Initially set question to display
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        // findViewById(int id) accepts a resource ID of widget and returns a View object
        // Need to cast returned View to Button before assigning to Button member variables
        mTrueButton = (Button) findViewById(R.id.true_button);
        // Listener is object that responds to event; implements listener interface for event.
        // Set listener to inform when mTrueButton is pressed.
        // setOnClickListener(OnClickListener) takes listener as argument, in particular object implementing onClickListener
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            // Listener implemented as anonymous inner class; everything within is passed into setOnClickListener(OnClickListener)
            // Listener interface requires implementing onClick(View), but no rules on how to implement it (could be empty)
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                checkAnswer(false);
                /*
                Toast.makeText(QuizActivity.this,   // can't simply pass "this", at this point in code it refers to View.OnClickListener
                        R.string.correct_toast,
                        Toast.LENGTH_SHORT).show();
                        */
            }
        });

        // "Next" Button action
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Go to next question in array (index loops back to 0 once it reaches end of array)
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Sets the question text view to the question at mCurrentIndex of mQuestionBank array.
     */
    private void updateQuestion()
    {
        int question = mQuestionBank[mCurrentIndex].getTextResId(); // getter to get question string
        mQuestionTextView.setText(question);
    }

    /**
     * Checks whether user's answer matches correct answer to question, and displays feedback.
     * @param userPressedTrue User's answer to the question, indicated by Button user pressed.
     */
    private void checkAnswer(boolean userPressedTrue)
    {
        // Correct answer
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        // Toast feedback confirming whether user inputted correct answer
        int messageResId = (userPressedTrue == answerIsTrue) ?
                R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this,                    // Context typically an instance of Activity (Activity is subclass of Context)
                messageResId,                   // resource ID of string toast should display; Toast class needs Context to find and use ID
                Toast.LENGTH_SHORT).show();     // Toast constant specifying how long toast should be visible; Toast.show() gets it on screen

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
