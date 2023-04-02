package com.mullachproject.projectquizapp;

import android.view.View;

public interface AppCompactButton {
    void setText(String option1);

    void setOnClickListener(View.OnClickListener onClickListener);

    Object getText();

    void setTextColor(int white);

    void setBackgroundResource(int roundWhiteBg);
}