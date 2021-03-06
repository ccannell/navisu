package org.navisu.charts.worldwind.render;

import gov.nasa.worldwind.geom.Position;
import java.util.List;
import org.navisu.charts.worldwind.render.impl.Polygon;
import org.navisu.charts.worldwind.render.impl.PolygonFactoryImpl;

/**
 *
 * @author Thibault
 */
public interface PolygonFactory {
    
    Polygon newPolygon(String id, List<Position> locations, boolean existsInFileStore);
    Polygon newPolygon(String id, List<Position> locations);
    
    public static final PolygonFactory factory = new PolygonFactoryImpl();
}
