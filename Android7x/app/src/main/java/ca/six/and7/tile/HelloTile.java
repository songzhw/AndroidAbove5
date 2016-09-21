package ca.six.and7.tile;

import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import ca.six.and7.R;

/**
 * Created by songzhw on 2016-09-20
 */

public class HelloTile extends TileService {
    private final int STATE_OFF = 0;
    private final int STATE_ON = 1;
    private int toggleState = STATE_ON;

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
        Tile tile = getQsTile();
        Icon icon;
        if (toggleState == STATE_ON) {
            toggleState = STATE_OFF;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.ic_lock_lock);
            tile.setState(Tile.STATE_INACTIVE);
            tile.setLabel("Hmmm");
        } else {
            toggleState = STATE_ON;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.ic_lock_open);
            tile.setState(Tile.STATE_ACTIVE);
            tile.setLabel("Hello");
        }

        tile.setIcon(icon);
        tile.updateTile();
        // 无论你传的什么颜色的Icon,STATE_ACTIVE都会把Icon渲染成白色, STATE_INACTIVE都会把Icon渲染成灰色。
        // but you can still click the tile whose state is STATE_INACTIVE
        // (It's like wifi and close wifi, but you can still click the tile)
    }
}
