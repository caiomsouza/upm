/*
  Original work: 
    Copyright 2006 by Sean Luke and George Mason University Licensed
    under the Academic Free License version 3.0 See the file "LICENSE"
    for more information
  Derived work:
    Nik Swoboda, Universidad Politecnica de Madrid
*/

package org.upm;

import sim.engine.SimState;
import sim.display.Controller;
import sim.display.GUIState;
import sim.display.Display2D;
import javax.swing.JFrame;
import java.awt.Color;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.network.NetworkPortrayal2D;
import sim.portrayal.network.SimpleEdgePortrayal2D;
import sim.portrayal.network.SpatialNetwork2D;

public class ACOSimWithUI extends GUIState
{
  public Display2D display;
  public JFrame displayFrame;

  NetworkPortrayal2D edgePortrayal = new NetworkPortrayal2D();
  ContinuousPortrayal2D nodePortrayal = new ContinuousPortrayal2D();

  public ACOSimWithUI() 
  { 
    super(new ACOSim(System.currentTimeMillis())); 
  }

  public ACOSimWithUI(SimState state) 
  { 
    super(state); 
  }

  public Object getSimulationInspectedObject()
  {
    return state;
  }

  public void start()
  {
    super.start();
    setupPortrayals();
  }

  public void load(SimState state)
  {
    super.load(state);
    setupPortrayals();
  }

  public void setupPortrayals()
  {
    // tell the portrayals what to portray and how to portray them
    edgePortrayal.setField(new SpatialNetwork2D(((ACOSim)state).environment, 
						((ACOSim)state).network));

    SimpleEdgePortrayal2D p = new SimpleEdgePortrayal2D(Color.lightGray, 
							Color.lightGray, 
							Color.black);
    p.setShape(SimpleEdgePortrayal2D.SHAPE_LINE);
    edgePortrayal.setPortrayalForAll(p);
    nodePortrayal.setField(((ACOSim)state).environment);

    // reschedule the displayer
    display.reset();
    display.setBackdrop(Color.white);

    // redraw the display
    display.repaint();
  }

  public void init(final Controller c)
  {
    super.init(c);

    display = new Display2D(800,600,this);
    displayFrame = display.createFrame();
    c.registerFrame(displayFrame); 
    displayFrame.setVisible(true);

    display.setBackdrop(Color.white);

    display.attach(edgePortrayal, "Edges");
    display.attach(nodePortrayal, "Nodes");
  }

  public void quit()
  {
    super.quit();

    if (displayFrame!=null) 
      displayFrame.dispose();
    displayFrame = null;
    display = null;
  }

  public static void main(String[] args)
  {
    new ACOSimWithUI().createController();
  }

}
