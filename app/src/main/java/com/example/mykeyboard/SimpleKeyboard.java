package com.example.mykeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class SimpleKeyboard extends InputMethodService 
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        keyboard = new Keyboard(this, R.xml.keyboard_layout);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        
        switch(primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case 32: // Space
                ic.commitText(" ", 1);
                break;
            default:
                char code = (char) primaryCode;
                ic.commitText(String.valueOf(code), 1);
        }
    }

    @Override
    public void onPress(int primaryCode) {
        // Key press feedback (optional)
    }

    @Override
    public void onRelease(int primaryCode) {
        // Key release (optional)
    }

    @Override
    public void onText(CharSequence text) {
        // For direct text input
    }

    @Override
    public void swipeLeft() {
        // Swipe actions (optional)
    }

    @Override
    public void swipeRight() {
        // Swipe actions (optional)
    }

    @Override
    public void swipeDown() {
        // Swipe actions (optional)
    }

    @Override
    public void swipeUp() {
        // Swipe actions (optional)
    }
        }
