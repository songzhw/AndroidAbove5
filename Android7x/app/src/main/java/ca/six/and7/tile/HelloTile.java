package ca.six.and7.tile;

import android.service.quicksettings.TileService;
import android.widget.Toast;

/**
 * Created by songzhw on 2016-09-20
 */

public class HelloTile extends TileService {

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        System.out.println("szw onTileAdded()");
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        System.out.println("szw onTileRemoved()");
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        System.out.println("szw onStartListening()");
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        System.out.println("szw onStopListening()");
    }

    @Override
    public void onClick() {
        super.onClick();
        System.out.println("szw onClick()");
        Toast.makeText(this, "Clicked Me!", Toast.LENGTH_SHORT).show();
    }
}
