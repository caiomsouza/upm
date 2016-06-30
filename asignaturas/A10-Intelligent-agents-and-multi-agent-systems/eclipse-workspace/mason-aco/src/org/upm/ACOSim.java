/*
  Original work:
    Copyright 2006 by Sean Luke and George Mason University Licensed
    under the Academic Free License version 3.0 See the file "LICENSE"
    for more information
  Derived work:
    Nik Swoboda, Universidad Politecnica de Madrid
*/

package org.upm;

import sim.field.continuous.*;
import sim.field.network.*;
import sim.engine.*;
import sim.util.*;
import java.util.ArrayList;

public class ACOSim extends SimState
{
  public Continuous2D environment = null;
  public Network network = null;
  private ArrayList<Ant> _fAnts = null;
  private ArrayList<PathEdge> _fEdges = null;
  public final static boolean DEBUG = true;

  public static final double XMIN = 0;
  public static final double XMAX = 800;
  public static final double COLS = 9;
  public static final double YMIN = 0;
  public static final double YMAX = 600;
  public static final double ROWS = 8;
  public static final double NODE_DIAMETER = 8;

  private int _fNumAnts = 18;
  private double _fHistoryCoefficient = 1;
  private double _fHeuristicCoefficient = 2.5;
  private double _fDecayFactor = 0.5;
  private double _fPheromoneChangeFactor = 1;
  private double _fInitialPheromone = 1.0;
  private boolean _fMaxMin = false;
  private double _fPheromoneMax = 10;
  private double _fPheromoneMin = 1.7;

  private GraphNode _fStartNode = null;
  private GraphNode _fEndNode = null;

  public int getNumAnts()
  {
    return _fNumAnts;
  }
  public void setNumAnts(int na)
  {
    _fNumAnts = na;
  }

  public double getHistoryCoefficient()
  {
    return _fHistoryCoefficient;
  }
  public void setHistoryCoefficient(double d)
  {
    _fHistoryCoefficient = d;
  }

  public double getHeuristicCoefficient()
  {
    return _fHeuristicCoefficient;
  }
  public void setHeuristicCoefficient(double d)
  {
    _fHeuristicCoefficient = d;
  }

  public double getDecayFactor()
  {
    return _fDecayFactor;
  }
  public void setDecayFactor(double d)
  {
    _fDecayFactor = d;
  }

  public double getPheromoneChangeFactor()
  {
    return _fPheromoneChangeFactor;
  }
  public void setPheromoneChangeFactor(double d)
  {
    _fPheromoneChangeFactor = d;
  }

  public double getInitialPheromone()
  {
    return _fInitialPheromone;
  }
  public void setInitialPheromone(double d)
  {
    _fInitialPheromone = d;
  }

  public boolean getMaxMin()
  {
    return _fMaxMin;
  }
  public void setMaxMin(boolean b)
  {
    _fMaxMin = b;
  }

  public double getPheromoneMax()
  {
    return _fPheromoneMax;
  }
  public void setPheromoneMax(double d)
  {
    _fPheromoneMax = d;
  }

  public double getPheromoneMin()
  {
    return _fPheromoneMin;
  }
  public void setPheromoneMin(double d)
  {
    _fPheromoneMin = d;
  }

  public ACOSim(long seed)
  {
    super(seed);
  }

  boolean acceptableNodePosition(final GraphNode node, 
				 final Double2D location)
  {
    if( location.x < NODE_DIAMETER/2 || 
	location.x > (XMAX-XMIN)-NODE_DIAMETER/2 ||
	location.y < NODE_DIAMETER/2 || 
	location.y > (YMAX-YMIN)-NODE_DIAMETER/2 )
      return false;
    return true;
  }

  private GraphNode makeNode(String name, int x, int y)
  {
    GraphNode node = new GraphNode(name);
    if (x > 0 && x <= COLS && y > 0 && y <= COLS)
      environment.setObjectLocation(node,
				    new Double2D((XMAX-XMIN)/(COLS+1)*x,
						 (YMAX-YMIN)/(ROWS+1)*y));
    else 
      environment.setObjectLocation(node, 
		    new Double2D(random.nextDouble()*(XMAX-
						      XMIN-
						      NODE_DIAMETER)+
				 XMIN+NODE_DIAMETER/2,
				 random.nextDouble()*(YMAX-
						      YMIN-
						      NODE_DIAMETER)+
				 YMIN+NODE_DIAMETER/2));
    network.addNode(node);
    // schedule.scheduleRepeating(node);
    return node;
  }

  private void makeEdge(GraphNode n1, GraphNode n2, String info)
  {
    PathEdge newEdge=new PathEdge(n1,n2,info,getInitialPheromone());
    network.addEdge(newEdge);
    _fEdges.add(newEdge);
  }

  private void setStart(GraphNode gn)
  {
    _fStartNode=gn;
    gn.setStart();
    Ant.setStartNode(gn);
  }
  private void setEnd(GraphNode gn)
  {
    _fEndNode=gn;
    gn.setEnd();
  }

  public void registerCompletion()
  {
    // check to see if all the ants are finished
    for (Ant anAnt:_fAnts)
      if (!anAnt.isDone())
	return;

    if (DEBUG)
    {
      System.out.println("End of Round Status:");
      for (Ant anAnt:_fAnts)
      {
	anAnt.dumpStatus();
	System.out.println("----");
      }
    }

    if (!getMaxMin()) // AS pheromone update
    {
      for (PathEdge anEdge:_fEdges)
      {
	// update pheromone of each edge
	double deltaPheromone = 0;
	for (Ant anAnt:_fAnts)
	  if (anAnt.pathContainsEdge(anEdge))
	    deltaPheromone += (_fPheromoneChangeFactor/
			       anAnt.getPathLength());

	double newPheromone = (1-_fDecayFactor)*anEdge.getPheromone()+
	  deltaPheromone;
//      if (DEBUG)
//      {
//	System.out.println("Edge "+anEdge.getName()+
//			   " old:"+anEdge.getPheromone()+
//			   " new:"+newPheromone);
//      }
	anEdge.setPheromone(newPheromone);
      }
    }
    else // MaxMin pheromone update
    {
      Ant theBestAnt = _fAnts.get(0);

      // check to see if all the ants are finished
      for (Ant anAnt:_fAnts)
	if (theBestAnt.getPathLength() > anAnt.getPathLength())
	  theBestAnt = anAnt;

      for (PathEdge anEdge:_fEdges)
      {
	// update pheromone of each edge
	double deltaPheromone = 0;

	if (theBestAnt.pathContainsEdge(anEdge))
	  deltaPheromone += (1/theBestAnt.getPathLength());

	double newPheromone = (1-_fDecayFactor)*anEdge.getPheromone()+
	  deltaPheromone;
//      if (DEBUG)
//      {
//	System.out.println("Edge "+anEdge.getName()+
//			   " old:"+anEdge.getPheromone()+
//			   " new:"+newPheromone);
//      }

	// apply the max and min to the new pheromone level
	if (newPheromone > getPheromoneMax())
	  newPheromone = getPheromoneMax();
	else if (newPheromone < getPheromoneMin())
	  newPheromone = getPheromoneMin();

	anEdge.setPheromone(newPheromone);
      }
    }

    for (Ant anAnt:_fAnts)
      anAnt.reset();
  }

  public void start()
  {
    super.start();  // clear out the schedule

    environment = new Continuous2D(16.0, (XMAX-XMIN), (YMAX-YMIN) );
    network = new Network();
    _fAnts = new ArrayList<Ant>();
    _fEdges = new ArrayList<PathEdge>();

    // The "Extended Double Bridge" Network proposed by Marco Dorigo and
    // Thomas Str:utzle in Ant Colony Optimization (MIT Press, 2004)
    GraphNode nodes[] = new GraphNode[19];
    nodes[0] = makeNode("0",5,1);
    nodes[1] = makeNode("1",4,2);
    nodes[2] = makeNode("2",6,2);
    nodes[3] = makeNode("3",3,3);
    nodes[4] = makeNode("4",7,3);
    nodes[5] = makeNode("5",2,4);
    nodes[6] = makeNode("6",8,4);
    nodes[7] = makeNode("7",1,5);
    nodes[8] = makeNode("8",3,5);
    nodes[9] = makeNode("9",5,4);
    nodes[10] = makeNode("10",7,5);
    nodes[11] = makeNode("11",9,5);
    nodes[12] = makeNode("12",2,6);
    nodes[13] = makeNode("13",4,6);
    nodes[14] = makeNode("14",6,6);
    nodes[15] = makeNode("15",8,6);
    nodes[16] = makeNode("16",3,7);
    nodes[17] = makeNode("17",7,7);
    nodes[18] = makeNode("18",5,8);
    
    // Donde se define de donde a donde.
//    setStart(nodes[2]);
//    setEnd(nodes[10]);

    setStart(nodes[12]);
    setEnd(nodes[14]);

    
    // Donde se define de donde a donde.
//    setStart(nodes[7]);
//    setEnd(nodes[11]);

    
    makeEdge(nodes[0],nodes[1],"0-1");
    makeEdge(nodes[0],nodes[2],"0-2");
    makeEdge(nodes[1],nodes[3],"1-3");
    makeEdge(nodes[2],nodes[4],"2-4");
    makeEdge(nodes[3],nodes[5],"3-5");
    makeEdge(nodes[4],nodes[6],"4-6");
    makeEdge(nodes[5],nodes[7],"5-7");
    makeEdge(nodes[9],nodes[8],"9-8");
    makeEdge(nodes[9],nodes[10],"9-10");
    makeEdge(nodes[6],nodes[11],"6-11");
    makeEdge(nodes[7],nodes[12],"7-12");
    makeEdge(nodes[8],nodes[12],"8-12");
    makeEdge(nodes[8],nodes[13],"8-13");
    makeEdge(nodes[8],nodes[10],"8-10");
    makeEdge(nodes[10],nodes[14],"10-14");
    makeEdge(nodes[10],nodes[15],"10-15");
    makeEdge(nodes[11],nodes[15],"11-15");
    makeEdge(nodes[12],nodes[16],"12-16");
    makeEdge(nodes[13],nodes[16],"13-16");
    makeEdge(nodes[13],nodes[14],"13-14");
    makeEdge(nodes[14],nodes[17],"14-17");
    makeEdge(nodes[15],nodes[17],"15-17");
    makeEdge(nodes[16],nodes[18],"16-18");
    makeEdge(nodes[16],nodes[17],"16-17");
    makeEdge(nodes[16],nodes[18],"16-18");
    makeEdge(nodes[17],nodes[18],"17-18");

    
    
    // make the ants
    for (int i=0;i<_fNumAnts;i++)
    {
      Ant newAnt = new Ant();
      _fAnts.add(newAnt);
      schedule.scheduleRepeating(newAnt);
    }
  }

  public static void main(String[] args)
  {
    doLoop(ACOSim.class, args);
    System.exit(0);
  }    
}
