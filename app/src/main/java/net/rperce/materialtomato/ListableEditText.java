package net.rperce.materialtomato;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ListableEditText extends EditText {
    public ListableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            this.setCursorVisible(true);
            if (this.getCurrentTextColor() == getResources().getColor(R.color.textColorPrimaryLight)) {
                this.setTextColor(getResources().getColor(R.color.textColorPrimarySecondary));
                this.setText("");
            }
        }
        if (!focused) {
            this.setCursorVisible(false);
            if (this.getCurrentTextColor() == getResources().getColor(R.color.textColorPrimarySecondary) && this.getText().length() == 0) {
                this.setTextColor(getResources().getColor(R.color.textColorPrimaryLight));
                this.setText("New Item");
            }
        }
    }

    @Override
    public void onEditorAction(int actionCode) {
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            this.clearFocus();

            InputMethodManager imm; // split instantiation because long line
            imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.clearFocus();
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
