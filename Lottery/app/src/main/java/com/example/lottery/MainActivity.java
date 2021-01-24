package com.example.lottery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton ejpButton;

    /**
     * Recursively collect all views with the given tag to the given list
     */
    private static void getViewsByTag(ViewGroup root, String tag,
                                      ArrayList<View> views) {
        final int childCount = root.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);

            if (child instanceof ViewGroup) {
                getViewsByTag((ViewGroup) child, tag, views);
            }
            else {
                final Object tagObj = child.getTag();

                if (tagObj != null && tagObj.equals(tag)
                        && !views.contains(child)) {
                    views.add(child);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.lotteryRadioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            View root = findViewById(R.id.activity_main);
            int index = (checkedId == R.id.ejpButton) ? 1 : 2;
            ArrayList<View> otherBallTextViews = new ArrayList<>();
            ArrayList<View> otherDelimTextViews = new ArrayList<>();

            getViewsByTag((ViewGroup) root, (index == 1 ? "L" : "EJP"),
                    otherBallTextViews);
            getViewsByTag((ViewGroup) root, (index == 1 ? "PLUS" : "STAR"),
                    otherDelimTextViews);

            int nob = otherBallTextViews.size();
            int nod = otherDelimTextViews.size();

            // Buh-bye, other ball and delimiter text views
            for (int i = 0; i < nob; i++) {
                TextView tv = (TextView) otherBallTextViews.get(i);

                tv.setVisibility(View.GONE);
            }
            for (int i = 0; i < nod; i++) {
                TextView tv = (TextView) otherDelimTextViews.get(i);

                tv.setVisibility(View.GONE);
            }
        });

        ejpButton = findViewById(R.id.ejpButton);

        Button drawButton = findViewById(R.id.drawButton);

        drawButton.setOnClickListener(v -> {
            View root = findViewById(R.id.activity_main);
            int selectedID = radioGroup.getCheckedRadioButtonId();
            int index = (selectedID == ejpButton.getId()) ? 1 : 2;

            // Draw them balls
            Balls b = new Balls();
            LinkedList<Integer> balls = b.draw(index);

            // Our ball and delimiter text views
            ArrayList<View> theseBallTextViews = new ArrayList<>();
            ArrayList<View> theseDelimTextViews = new ArrayList<>();

            getViewsByTag((ViewGroup) root, (index == 1 ? "EJP" : "L"),
                    theseBallTextViews);
            getViewsByTag((ViewGroup) root, (index == 1 ? "STAR" : "PLUS"),
                    theseDelimTextViews);

            int ntb = theseBallTextViews.size();
            int ntd = theseDelimTextViews.size();

            // Set the values and the visibility of our balls
            for (int i = 0; i < ntb; i++) {
                TextView tv = (TextView) theseBallTextViews.get(i);

                tv.setVisibility(View.VISIBLE);
                tv.setText(balls.get(i).toString());
            }

            // Make our delimiters visible, too
            for (int i = 0; i < ntd; i++) {
                TextView tv = (TextView) theseDelimTextViews.get(i);

                tv.setVisibility(View.VISIBLE);
            }
        });
    }
}
