



package org.upm;


import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;
import java.util.ArrayList;

public class Ant implements Steppable
{
  public static GraphNode startNode = null;

  private ArrayList<PathEdge> _fCurrentPath = new ArrayList<PathEdge>();
  private ArrayList<GraphNode> _fNodesVisited = new ArrayList<GraphNode>();
  private double _fPathLength = 0;
  private boolean _fDone = false;
  private GraphNode _fNowNode = null;

  public Ant()
  {
    reset();
  }

  public static void setStartNode(GraphNode gn)
  {
    startNode = gn;
  }

  public boolean isDone()
  {
    return _fDone;
  }
  public void setDone(boolean b)
  {
    _fDone = b;
  }
 
  public double getPathLength()
  {
    return _fPathLength;
  }

  public boolean pathContainsEdge(PathEdge pe)
  { 
    return _fCurrentPath.contains(pe);
  }

  public void reset()
  {
    setDone(false);
    _fCurrentPath.clear();
    _fNodesVisited.clear();
    _fPathLength = 0;
    _fNowNode = startNode;
    _fNodesVisited.add(startNode);
  }

  private GraphNode getEndNode(PathEdge pe)
  {
    if (pe.getFrom()==_fNowNode)
      return (GraphNode)(pe.getTo());
    else
      return (GraphNode)(pe.getFrom());
  }

  private double calcProbHelper(double pheromone, double length,
			       	double historyCoefficient,
				double heuristicCoefficient)
  {
    return Math.pow(pheromone,historyCoefficient)*
           Math.pow(1/length,heuristicCoefficient);
  }

  public void step(SimState state)
  {
    // check to see if we have gotten to the end
    if (_fNowNode.isEnd())
    {
      setDone(true);
      ((ACOSim)state).registerCompletion();
      return;
    }
    else
    {
      Bag nowEdges = new Bag();
      ((ACOSim)state).network.getEdges(_fNowNode,nowEdges);
      ArrayList<PathEdge> okEdges = new ArrayList<PathEdge>();

      // do not consider edges which lead to nodes which we have already
      // visited.
      for (Object o:nowEdges)
	if (!_fNodesVisited.contains(getEndNode((PathEdge)o)))
	  okEdges.add((PathEdge)o);

      if (okEdges.size()==0)
      {
	if (((ACOSim)state).DEBUG)
	  System.out.println("Stuck no where to go, bailing.");
	// bail, we are stuck with no place to go
	reset();
	return;
      }

//      if (((ACOSim)state).DEBUG)
//      {
//        System.out.print("OK Edges: ");
//	for (PathEdge pe:okEdges)
//	  System.out.print(pe.getName()+" ");
//        System.out.println();
//      }

      double totalPheromone = 0;
      for (PathEdge pe:okEdges)
	totalPheromone += calcProbHelper(pe.getPheromone(), pe.getLength(),
					((ACOSim)state).getHistoryCoefficient(),
					((ACOSim)state).getHeuristicCoefficient());

      double chance = ((ACOSim)state).random.nextDouble()*totalPheromone;
//      if (((ACOSim)state).DEBUG)
//	System.out.println("Chance "+chance);

      double lastBorder=0;
      double nextBorder=0;
      for (PathEdge pe:okEdges)
      {
	lastBorder = nextBorder;
	nextBorder += calcProbHelper(pe.getPheromone(), pe.getLength(),
				     ((ACOSim)state).getHistoryCoefficient(),
				     ((ACOSim)state).getHeuristicCoefficient());
//	if (((ACOSim)state).DEBUG)
//	  System.out.println("Borders Last: "+lastBorder+
//			     " Next:"+nextBorder);
	if (chance >= lastBorder && chance <= nextBorder)
	{
	  // bingo we know where to go
//	  if (((ACOSim)state).DEBUG)
//	  {
//            System.out.println("Bingo, I have selected "+pe.getName());
//	  }

	  _fCurrentPath.add(pe);
          _fPathLength += pe.getLength();
	  GraphNode nextNode = getEndNode(pe);
	  _fNowNode = nextNode;
	  _fNodesVisited.add(nextNode);

//	  if (((ACOSim)state).DEBUG)
//	  {
//            dumpStatus();
//	  }
	  return;
	}
      }
      System.out.print("Error??");
    }
  }

  public void dumpStatus()
  {
//    System.out.print("Current path:\n  ");
//    for (PathEdge pe:_fCurrentPath)
//      System.out.print(pe.getName() + " ");
//    System.out.println();
    System.out.print("Nodes visited:\n  ");
    for (GraphNode gn:_fNodesVisited)
      System.out.print(gn.getID() + " ");
    System.out.println();
//    System.out.println("Path length: "+_fPathLength);
//    System.out.println("Done: "+_fDone);
//    System.out.println("Now Node: "+_fNowNode.getID());
  }

}

