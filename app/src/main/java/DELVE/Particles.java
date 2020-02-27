package DELVE;

import android.graphics.Color;
import android.view.ViewGroup;

import com.github.jinatonic.confetti.CommonConfetti;

public class Particles {

    public static void emitParticles(ViewGroup container) {
        CommonConfetti.rainingConfetti(container, new int[]{Color.BLACK})
                .infinite();
    }
}
