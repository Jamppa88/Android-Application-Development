package fi.jamk.k8958.golfcourses;

import android.content.Context;

/**
 * Created by K8958 on 9.10.2017.
 */

public class Place {
    public String name;
    public String imageName;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
    }
}
