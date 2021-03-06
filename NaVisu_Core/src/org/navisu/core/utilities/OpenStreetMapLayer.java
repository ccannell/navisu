/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.core.utilities;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.mercator.BasicMercatorTiledImageLayer;
import gov.nasa.worldwind.layers.mercator.MercatorSector;
import gov.nasa.worldwind.util.LevelSet;
import gov.nasa.worldwind.util.Tile;
import gov.nasa.worldwind.util.TileUrlBuilder;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenStreetMapLayer extends BasicMercatorTiledImageLayer {

    public OpenStreetMapLayer() {
        super(makeLevels());
        this.setForceLevelZeroLoads(true);
        this.setRetainLevelZeroTiles(true);
        this.setEnabled(false);
    }

    private static LevelSet makeLevels() {
        AVList params = new AVListImpl();

        params.setValue(AVKey.TILE_WIDTH, 256);
        params.setValue(AVKey.TILE_HEIGHT, 256);
        params.setValue(AVKey.DATA_CACHE_NAME, "OpenStreetMap");
        params.setValue(AVKey.SERVICE, "http://a.tile.openstreetmap.org/");
        params.setValue(AVKey.DATASET_NAME, "OpenStreetMap");
        params.setValue(AVKey.FORMAT_SUFFIX, ".png");
        params.setValue(AVKey.NUM_LEVELS, 16);
        params.setValue(AVKey.NUM_EMPTY_LEVELS, 0);
        params.setValue(AVKey.LEVEL_ZERO_TILE_DELTA, new LatLon(Angle.fromDegrees(22.5d), Angle.fromDegrees(45d)));
        params.setValue(AVKey.SECTOR, new MercatorSector(-1.0, 1.0, Angle.NEG180, Angle.POS180));
        params.setValue(AVKey.TILE_URL_BUILDER, new URLBuilder());

        return new LevelSet(params);
    }

    private static class URLBuilder implements TileUrlBuilder {

        @Override
        public URL getURL(Tile tile, String imageFormat) throws MalformedURLException {
            int level = tile.getLevelNumber() + 3;
            int column = tile.getColumn();
            int row = (1 << (tile.getLevelNumber()) + 3) - 1 - tile.getRow();
            return new URL(tile.getLevel().getService() + level + "/" + column + "/" + row + ".png");
        }
    }

    @Override
    public String toString() {
        return "OpenStreetMap";
    }
}