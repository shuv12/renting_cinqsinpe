package app.com.example.android.renting;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

/**
 * Created by shuvam on 03-08-2016.
 */
public class CheckableImageView extends ImageView implements Checkable {
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    private boolean mChecked = false;

    public CheckableImageView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public boolean isChecked(){
        return this.mChecked;
    }

    public void setChecked(boolean b){
        if (b != this.mChecked){
            this.mChecked = b;
            refreshDrawableState();
        }
    }

    public void toggle(){
        setChecked(!this.mChecked);
    }

    public int[] onCreateDrawableState(int extraSpace){
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()){
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);

        }
        return drawableState;
    }
    public boolean performClick(){
        toggle();
        return super.performClick();
    }
}

