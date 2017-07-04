package com.bignerdranch.android.geoquiz;

/**
 * Represents a question with an answer (true or false).
 *
 * @author  Derek Tran
 * @version 1.0
 * @since   2017-06-30
 */

public class Question
{
    /**
     * Resource ID of string resource for the question.
     */
    private int mTextResId;
    private boolean mAnswerTrue;

    public int getTextResId()
    {
        return mTextResId;
    }

    public void setTextResId(int textResId)
    {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue()
    {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
