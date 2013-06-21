/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.texts;

import org.navisu.nmea.devices.controller.services.VBWService;
import org.navisu.nmea.model.NMEA;
import org.navisu.nmea.model.VBW;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = VBWService.class)
public class NMEAVBWTextView
        implements VBWService {

    private InputOutput io = null;

    public NMEAVBWTextView() {
        io = IOProvider.getDefault().getIO("VBW", false);
    }

    @Override
    public <T extends NMEA> void update(T data) {
        io.getOut().println((VBW)data);
    }
}