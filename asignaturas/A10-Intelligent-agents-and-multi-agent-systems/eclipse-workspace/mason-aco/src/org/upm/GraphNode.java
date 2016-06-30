package org.upm;


/*
  Original work:
    Copyright 2006 by Sean Luke and George Mason University Licensed
    under the Academic Free License version 3.0 See the file "LICENSE"
    for more information
  Derived work:
    Nik Swoboda, Universidad Politecnica de Madrid
*/

import sim.engine.*;
import sim.util.Double2D;
import sim.portrayal.*;
import java.awt.geom.*;
import java.awt.*;

public class GraphNode extends SimplePortrayal2D implements Steppable
{
  private String id;
  private Font nodeFont = new Font("SansSerif", Font.PLAIN, 12);
  private boolean isStart = false;
  private boolean isEnd = false;

  public GraphNode( String id )
  {
    this.id = id;
  }

  public String getID() 
  { 
    return id; 
  }
  public void setID( final String id ) 
  { 
    this.id = id; 
  }

  public void setStart()
  { 
    isStart=true;
  }
  public boolean isStart()
  { 
    return isStart;
  }
  public void setEnd()
  {
    isEnd=true;
  }
  public boolean isEnd()
  {
    return isEnd;
  }

  public void step( final SimState state )
  {
    // at the moment the nodes do nothing.
  }

  public final void draw(Object object, Graphics2D graphics, DrawInfo2D info)
  {
    double diamx = info.draw.width*ACOSim.NODE_DIAMETER;
    double diamy = info.draw.height*ACOSim.NODE_DIAMETER;

    if (isStart)
      graphics.setColor(Color.green);
    else if (isEnd)
      graphics.setColor(Color.red);
    else 
      graphics.setColor(Color.black);
    graphics.fillOval((int)(info.draw.x-diamx/2),
		      (int)(info.draw.y-diamy/2),
		      (int)(diamx),(int)(diamy));
    graphics.setFont(nodeFont.deriveFont(nodeFont.getSize2D()*
					 (float)info.draw.width));
    graphics.setColor(Color.blue);
    graphics.drawString(id, (int)(info.draw.x-diamx/2), 
			(int)(info.draw.y-diamy/2) );
  }
}
