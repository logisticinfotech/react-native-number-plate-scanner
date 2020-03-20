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
import android.util.Log;
import android.util.SparseArray;

import com.NumberPlateScanner.base.camera.GraphicOverlay;
import com.NumberPlateScanner.base.listner.OnNumberPlateDetact;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A very simple Processor which gets detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private static final String TAG = "OcrDetectorProcessor";
    private GraphicOverlay<OcrGraphic> mGraphicOverlay;
    private Context context;
    private OnNumberPlateDetact onNumberPlateDetact;

    OcrDetectorProcessor(GraphicOverlay<com.NumberPlateScanner.base.OcrGraphic> ocrGraphicOverlay) {
        mGraphicOverlay = ocrGraphicOverlay;
    }

    public OcrDetectorProcessor(GraphicOverlay<com.NumberPlateScanner.base.OcrGraphic> mGraphicOverlay, OnNumberPlateDetact onNumberPlateDetact) {
        this.mGraphicOverlay = mGraphicOverlay;
        this.onNumberPlateDetact = onNumberPlateDetact;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        if (!com.NumberPlateScanner.base.ScannerActivity.scan)
            return;
        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.getValue());
            }
//            OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
            //mGraphicOverlay.saveContext(context);
//            graphic.saveContext(context);
//            mGraphicOverlay.add(graphic);

            checkText(item);
        }
    }

    private void checkText(TextBlock mText) {

        String REGEX = "^[A-Z]{2}[0-9]{1,2}(?:[A-Z])?(?:[A-Z]*)?[0-9]{4}$"; //regular expression
        Pattern number; //a pattern of compiled regex
        Matcher matcher; //helps in matching the regex
        String text = mText.getValue();
        Log.d(TAG, "draw: " + text);

        //fixing
        Matcher m = Pattern.compile("[-][0-9]{2}[-]|[-]|[\n]").matcher(text);
        text = m.replaceAll(" ");

        //number detection
        text = text.replaceAll(" ", "");
        text = text.replaceAll("-", "");
        number = Pattern.compile(REGEX);
        matcher = number.matcher(text);

        //draw box on screen

        if (matcher.matches()) { //print if valid
            onNumberPlateDetact.onDetect(text);
        } else {

        }
    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        mGraphicOverlay.clear();
    }

    public void saveContext(Context con) {
        context = con;
    }
}
