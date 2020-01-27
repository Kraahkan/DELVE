package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeTouch;
import com.mindorks.placeholderview.annotations.swipe.SwipeView;
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection;

import org.w3c.dom.Text;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by janisharali on 19/08/16.
 */
@Layout(R.layout.tinder_card_view)
public class TinderCard {

    @View(R.id.profileImageView)
    ImageView profileImageView;

    @View(R.id.cardTextView)
    TextView place;

    @View(R.id.leftTextView)
    TextView leftText;

    @View(R.id.rightTextView)
    TextView rightText;

    @SwipeView
    android.view.View mSwipeView;

    private Instance instance1;
    private Context mContext;
    private Point mCardViewHolderSize;
    private Callback mCallback;




    public TinderCard(Context context, Instance instance, Point cardViewHolderSize, Callback callback) {
        mContext = context;
        instance1 = instance;
        mCardViewHolderSize = cardViewHolderSize;
        mCallback = callback;


    }


    @Resolve
    public void onResolved() {

        rightText.setText(instance1.getPositiveText());
        leftText.setText(instance1.getNegativeText());
        place.setText(instance1.getTitle());
        Glide.with(mContext).load(R.mipmap.ic_launcher)
                .bitmapTransform(new RoundedCornersTransformation(mContext, Utils.dpToPx(7), 0,
                        RoundedCornersTransformation.CornerType.TOP))
                .into(profileImageView);
        mSwipeView.setAlpha(1);

    }

    @Click(R.id.profileImageView)
    public void onClick() {
        Log.d("EVENT", "profileImageView click");

    }

    @SwipeOutDirectional
    public void onSwipeOutDirectional(SwipeDirection direction) {
        Log.d("GRAPE", "SwipeOutDirectional " + direction.getDirection());
        Log.d("GRAPE", "SwipeOutDirectional " + SwipeDirection.BOTTOM.getDirection());
        if (direction.getDirection() == SwipeDirection.TOP.getDirection()) {
            Toast.makeText(mContext, "SUPER LIKE! Show any dialog here.", Toast.LENGTH_SHORT).show();
            mCallback.onSwipeUp();
        }
    }

    @SwipeCancelState
    public void onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState");
        mSwipeView.setAlpha(1);
    }

    @SwipeInDirectional
    public void onSwipeInDirectional(SwipeDirection direction) {
        Log.d("DEBUG", "SwipeInDirectional " + direction.name());
    }

    @SwipingDirection
    public void onSwipingDirection(SwipeDirection direction) {
        Log.d("fries", "SwipingDirection " + direction.name());
        if(direction.name()=="BOTTOM")
        {
            Log.d("bottomFries", "SwipingDirection " + direction.name());

            Toast.makeText(mContext, "SUPER LIKE! Show any dialog here.", Toast.LENGTH_SHORT).show();

        }
    }
    @SwipingDirection
    public void onSwipeBottom(SwipeDirection direction){
        Log.d("DEBUG", "SwipeInDirectional " + direction.name());
    }


    @SwipeTouch
    public void onSwipeTouch(float xStart, float yStart, float xCurrent, float yCurrent) {

        float cardHolderDiagonalLength =
                (float) Math.sqrt(Math.pow(mCardViewHolderSize.x, 2) + (Math.pow(mCardViewHolderSize.y, 2)));
        float distance = (float) Math.sqrt(Math.pow(xCurrent - xStart, 2) + (Math.pow(yCurrent - yStart, 2)));

        float alpha = 1 - distance / cardHolderDiagonalLength;

        Log.d("DEBUG","onSwipeTouch "
                + " xStart : " + xStart
                + " yStart : " + yStart
                + " xCurrent : " + xCurrent
                + " yCurrent : " + yCurrent
                + " distance : " + distance
                + " TotalLength : " + cardHolderDiagonalLength
                + " alpha : " + alpha
        );

        (mSwipeView).setAlpha(alpha);
    }

    interface Callback {
        void onSwipeUp();
    }
}
