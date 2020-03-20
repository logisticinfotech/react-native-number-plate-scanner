/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.NumberPlateScanner.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.NumberPlateScanner.base.camera.GraphicOverlay;
import com.google.android.gms.vision.text.TextBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
 * overlay view.
 */
public class OcrGraphic extends GraphicOverlay.Graphic {

    private static final int TEXT_COLOR = Color.WHITE;
    private static final String TAG = "OcrGraphic";

    private static Paint sRectPaint;
    private static Paint sTextPaint;
    private final TextBlock mText;
    public String text;
    Context context;
    Intent myIntent;

    OcrGraphic(GraphicOverlay overlay, TextBlock text) {
        super(overlay);

        mText = text;

        if (sRectPaint == null) {
            sRectPaint = new Paint();
            sRectPaint.setColor(TEXT_COLOR);
            sRectPaint.setStyle(Paint.Style.STROKE);
            sRectPaint.setStrokeWidth(4.0f);
        }

        if (sTextPaint == null) {
            sTextPaint = new Paint();
            sTextPaint.setColor(TEXT_COLOR);
            sTextPaint.setTextSize(30.0f);
        }
        // Redraw the overlay, as this graphic has been added.
        postInvalidate();
    }

    public void saveContext(Context con){
        context = con;
    }
    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        if (mText == null) {
            return;
        }

        // Draws the bounding box around the TextBlock.


        //validation setting

        String REGEX = "^[A-Z]{2}[0-9]{1,2}(?:[A-Z])?(?:[A-Z]*)?[0-9]{4}$"; //regular expression
        Pattern number; //a pattern of compiled regex
        Matcher matcher; //helps in matching the regex
        text = mText.getValue();
        Log.d(TAG, "draw: "+text);
        //fixing
        Matcher m = Pattern.compile("[-][0-9]{2}[-]|[-]|[\n]").matcher(text);
        text = m.replaceAll(" ");
//        m = Pattern.compile("ICT|Islamabad|Sindh|Punjab|PK|Govt of Sindh|Ict-Islamabad|ICT-Islamabad").matcher(text);
//        text = m.replaceAll("");

        //final touch
//        text = Pattern.compile("\\s[0-9]{2}\\s").matcher(text).replaceAll(" ");
//        text = text.replaceAll("( +)", " ").trim();

        //number detection
        text = text.replaceAll(" ","");
        text = text.replaceAll("-","");
        number = Pattern.compile(REGEX);
        matcher = number.matcher(text);

        //draw box on screen
        RectF rect = new RectF(mText.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, sRectPaint);
        if (matcher.matches()) { //print if valid
            canvas.drawText(text, rect.centerX(), rect.bottom, sTextPaint); //draw on screen
            sRectPaint.setColor(Color.GREEN);
            sTextPaint.setColor(Color.GREEN);

        }else {
            sRectPaint.setColor(Color.WHITE);

        }
//        postInvalidate();
    }
}
