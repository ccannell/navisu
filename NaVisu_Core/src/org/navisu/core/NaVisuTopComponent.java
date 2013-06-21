/**
 * This file is part of NaVisu.
 *
 * NaVisu is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * NaVisu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * NaVisu. If not, see <http://www.gnu.org/licenses/>.
 */
package org.navisu.core;

import org.navisu.core.impl.WorldWindManager;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.TerrainProfileLayer;
import gov.nasa.worldwind.util.StatusBar;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.logging.Logger;
import org.navisu.core.utilities.OpenStreetMapLayer;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.enib.navisu.core//NaVisu//EN", autostore = false)
@TopComponent.Description(
        preferredID = "NaVisuTopComponent",
        iconBase = "img/earth.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.enib.navisu.core.NaVisuTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_NaVisuAction", preferredID = "NaVisuTopComponent")
@Messages({
    "CTL_NaVisuAction=NaVisu",
    "CTL_NaVisuTopComponent=NaVisu",
    "HINT_NaVisuTopComponent=This is a NaVisu window"
})
public final class NaVisuTopComponent
        extends TopComponent {

    // TODO only for debug
    protected final Position BREST = Position.fromDegrees(48.390834, -4.485556, 39000);
    /**
     * The Logger
     */
    protected static final Logger LOG = Logger.getLogger(NaVisuTopComponent.class.getSimpleName());
    /**
     * The WorldWind Manager
     */
    protected WorldWindManagerServices wwm = Lookup.getDefault().lookup(WorldWindManagerServices.class);
    /**
     * The WorldWindow
     *
     * @see WorldWindManager#getWorldWindow()
     */
    protected WorldWindow wwd;
    /**
     * The terrain profile layer
     */
    protected TerrainProfileLayer terrainProfileLayer;

    public NaVisuTopComponent() {

        this.initComponents();
        this.setName(/*Bundle.CTL_NaVisuTopComponent()*/"NaVisu");
        this.setToolTipText(/*Bundle.HINT_NaVisuTopComponent()*/"The main NaVisu Window");

        this.wwd = this.wwm.getWorldWindow();

        this.initWWJ();
        this.initUI();

    }

    private void initWWJ() {
        wwm.setOffline(false);
        wwm.insertBeforeCompass(new OpenStreetMapLayer());
    }

    private void initUI() {

        this.setLayout(new BorderLayout());
        this.add((Component) wwd, BorderLayout.CENTER);

        StatusBar statusBar = new StatusBar();
        statusBar.setEventSource(wwd);
        this.add(statusBar, BorderLayout.SOUTH);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // see the NaVisuLifecycleManager for the saveSessionState
        this.wwm.restoreSessionState();
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "0.1");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
}