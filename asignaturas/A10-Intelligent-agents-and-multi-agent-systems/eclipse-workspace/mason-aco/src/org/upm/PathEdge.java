package org.upm;

/*
  Original work:
    Copyright 2006 by Sean Luke and George Mason University Licensed
    under the Academic Free License version 3.0 See the file "LICENSE"
    for more information
  Derived work:
    Nik Swoboda, Universidad Politecnica de Madrid
*/

import sim.field.network.Edge;

public class PathEdge extends Edge 
{
  double pheromone = 0;
  double edgeLength = 1.0;
  String name = null;

  public PathEdge(GraphNode gn1, GraphNode gn2, String info, double p)
  {
    super(gn1,gn2,""+1.0);
    setPheromone(p);
    name = info;
  }

  public double getPheromone()
  {
    return pheromone;
  }
  public void setPheromone(double d)
  {
    pheromone = d;
    setInfo(""+String.format("%.3f",pheromone));
  }
  public double getLength()
  {
    return edgeLength;
  }
  public String getName()
  {
    return name;
  }
}
