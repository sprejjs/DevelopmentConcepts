package com.spreys.a5developmentconcepts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio
 *
 * @author vspreys
 *         Date: 20/07/16
 *         Project: 5DevelopmentConcepts
 *         Contact by: vlad@spreys.com
 */
public class ConceptFrameFragment extends Fragment {

    public enum ProgrammingLanguage {
        Java,
        Swift
    }

    public enum Concept {
        CONDITIONAL_STATEMENT,
        SWITCH_STATEMENT,
        FOR_LOOP,
        WHILE_LOOP,
        ENUM
    }

    private Concept mConcept;
    private ProgrammingLanguage mProgrammingLanguage = ProgrammingLanguage.Java;

    @BindView(R.id.concept_frame_title)
    TextView txtTitle;

    @BindView(R.id.concept_frame_explanation)
    TextView txtExplanation;

    @BindView(R.id.concept_frame_example)
    ImageView imgExample;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.concept_frame, container, false);
        ButterKnife.bind(this, view);
        setContent();

        return view;
    }

    public void setConcept(Concept concept) {
        mConcept = concept;
    }
    public void setProgrammingLanguage(ProgrammingLanguage language) {
        mProgrammingLanguage = language;
        setContent();
    }

    private void setContent() {
        if (isAdded()) {
            switch (mConcept) {
                case CONDITIONAL_STATEMENT:
                    txtTitle.setText(getString(R.string.title_conditional_statement));
                    txtExplanation.setText(getString(R.string.explanation_conditional_statement));
                    imgExample.setImageResource(mProgrammingLanguage == ProgrammingLanguage.Java ? R.drawable.conditional_statement_java : R.drawable.conditional_statement_swift);
                    break;
                case SWITCH_STATEMENT:
                    txtTitle.setText(R.string.title_switch_statement);
                    txtExplanation.setText(R.string.explanation_switch_statement);
                    imgExample.setImageResource(mProgrammingLanguage == ProgrammingLanguage.Java ? R.drawable.switch_statement_java : R.drawable.switch_statement_swift);
                    break;
                case FOR_LOOP:
                    txtTitle.setText(R.string.title_for_loop);
                    txtExplanation.setText(R.string.explanation_for_loop);
                    imgExample.setImageResource(mProgrammingLanguage == ProgrammingLanguage.Java ? R.drawable.for_loop_java : R.drawable.for_loop_swift);
                    break;
                case WHILE_LOOP:
                    txtTitle.setText(R.string.title_while_loop);
                    txtExplanation.setText(R.string.explanation_while_loop);
                    imgExample.setImageResource(mProgrammingLanguage == ProgrammingLanguage.Java ? R.drawable.while_loop_java : R.drawable.while_loop_swift);
                    break;
                case ENUM:
                    txtTitle.setText(R.string.title_enum);
                    txtExplanation.setText(R.string.explanation_enum);
                    imgExample.setImageResource(mProgrammingLanguage == ProgrammingLanguage.Java ? R.drawable.enum_java : R.drawable.enum_swift);
                    break;
            }
        }
    }
}
