package net.rperce.materialtomato;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.github.mrengineer13.snackbar.SnackBar;

public class ListCheckBox extends CheckBox {
    private static int rempos;
    private static String remtext;
    private static ArrayAdapter<String> adapter;
    private static View v;

    @SuppressWarnings("unchecked")
    private void doRemove() {
        RelativeLayout parent = (RelativeLayout)v.getParent();
        if (parent == null) return;
        ListableEditText text = (ListableEditText)parent.getChildAt(0);
        if (text == null) return;

        if (parent.getParent() == null) return;
        ListCheckBox.adapter = (ArrayAdapter<String>)((ListView) parent.getParent()).getAdapter();
        ListCheckBox.remtext = text.getText().toString();
        ListCheckBox.rempos = ListCheckBox.adapter.getPosition(remtext);
        adapter.remove(ListCheckBox.remtext);
        ((CheckBox)ListCheckBox.v).setChecked(false);

        new SnackBar.Builder((Activity)v.getContext())
                .withOnClickListener(new SnackBar.OnMessageClickListener() {
                    @Override
                    public void onMessageClick(Parcelable parcelable) {
                        readd();
                    }
                })
                .withMessage("Item marked done")
                .withActionMessage("Undo")
                .withDuration((short) 10000)
                .show();
    }
    public ListCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ListCheckBox.v = view;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doRemove();
                    }
                }, 500);
            }
        });
    }

    private void readd() {
        ListCheckBox.adapter.insert(ListCheckBox.remtext, ListCheckBox.rempos);
    }
}
