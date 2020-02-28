package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import java.io.InputStream;

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

    private Instance cardInstance;
    private Context mContext;
    private Point mCardViewHolderSize;
    private Callback mCallback;




    public TinderCard(Context context, Instance instance, Point cardViewHolderSize, Callback callback) {
        mContext = context;
        cardInstance = instance;
        mCardViewHolderSize = cardViewHolderSize;
        mCallback = callback;


    }


    @Resolve
    public void onResolved() {

        rightText.setText(cardInstance.getPositiveText());
        leftText.setText(cardInstance.getNegativeText());
        place.setText(cardInstance.getTitle());

        //image loader for card.
        String image = cardInstance.getArt();
        Uri path = Uri.parse("android.resource://dependency.greendao.test.tinder.directional/drawable/"+image );


        Glide.with(mContext).load(path)
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
        if (direction.getDirection() == 1 || direction.getDirection()== 6 || direction.getDirection()== 7 ) {
            mCallback.onSwipeLeft();
            Log.d("GRAPE", "SwipeOutDirectional " + direction.getDirection());

        }else
        {
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
        Log.d("GRAPE", "SwipeInDirectional " + direction.getDirection());
        if (direction.getDirection() == 2) {
            mCallback.onSwipeDown();
            Log.d("GRAPE", "SwipeOutDirectional " + direction.getDirection());
        } else if (direction.getDirection() == 3 ||  direction.getDirection()== 4 || direction.getDirection()== 5 ) {
            mCallback.onSwipeRight();
            Log.d("GRAPE", "SwipeOutDirectional " + direction.getDirection());
        }

    }

    @SwipingDirection
    public void onSwipingDirection(SwipeDirection direction) {
        Log.d("DEBUG", "SwipingDirection " + direction.name());


    }
    @SwipingDirection
    public void onSwipeBottom(SwipeDirection direction){
        Log.d("DEBUG", "SwipeInDirectionalBottom " + direction.name());
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
        void onSwipeDown();
        void onSwipeRight();
        void onSwipeLeft();
    }
}
