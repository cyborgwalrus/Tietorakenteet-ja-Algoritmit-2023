package oy.interact.tira.student.graph;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import oy.interact.tira.student.ArrayQueue;
import oy.interact.tira.student.graph.Edge.EdgeType;
import oy.interact.tira.student.graph.Visit.Type;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

import java.util.Comparator;

/**
 * Implementation of the graph data structure and associated algorithms.
 * <p>
 * This abstract class implements the graph data structure and various
 * algorithms like breadth-first search, depth-first search and the Dijkstra
 * path finding algorithm.
 * <p>
 * The class needs your attention, dear student. Implement the methods
 * marked todo in the comments, based on what you have learned about
 * graphs.
 * <p>
 * The Graph as a generic (template) class can use any data types conforming to
 * the Comparable interface.
 * <p>
 * This implementation uses edge lists to store the graph vertices
 * and edges.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public class Graph<T> {

   /**
    * The edge list of the grap. Select and instantiate
    * a suitable type of Map, depending on application needs.
    */
   private Map<Vertex<T>, List<Edge<T>>> edgeList = null;

   /**
    * Constructor instantiates a suitable Map data structure
    * depending on the application requirements.
    */
   public Graph() {
      this.edgeList = new Hashtable<Vertex<T>, List<Edge<T>>>();
   }

   /**
    * Creates a vertex holding the dataItem (node in a vertex) in the graph.
    * Use this method always to add vertices to the graph.
    *
    * Create the vertex object with the data item, then create an empty
    * list of edges, and put the vertex and the empty list to the Map.
    *
    * The newly created vertex must have an empty list of edges.
    * 
    * @param element The data item to put in the vertex of the graph.
    * @return Returns the created vertex, placed in the graph's edge list.
    */
   public Vertex<T> createVertexFor(T element) {
      Vertex<T> newVertex = new Vertex<T>(element);
      edgeList.put(newVertex, new ArrayList<Edge<T>>());

      return newVertex;
   }

   /**
    * Get all the vertices of the graph in a Set.
    * 
    * @return A Set with all the vertices of the graph.
    */
   public Set<Vertex<T>> getVertices() {
      return edgeList.keySet();
   }

   /**
    * Adds an edge to the graph. Note that the vertices MUST have been created
    * earlier by calling {@code createVertexFor(T)} and are already in the graph.
    * 
    * @param type        The type of the edge, either directed or undirected.
    * @param source      The source vertex of the edge.
    * @param destination The destination vertex of the edge.
    * @param weight      The weight of the edge.
    */
   public void addEdge(Edge.EdgeType type, Vertex<T> source, Vertex<T> destination, double weight) {
      // Input checking
      if (type == null || source == null || destination == null || weight < 0.0)
         return;

      // Create new edge
      Edge<T> newEdge = new Edge<>(source, destination, weight);

      // Add the new edge from source to destination
      getEdges(source).add(newEdge);

      // If the edge type is undirected (not one way),
      // add a reversed version of new edge
      if (type == Edge.EdgeType.UNDIRECTED)
         getEdges(destination).add(newEdge.reversed());
   }

   /**
    * Adds a directed edge to the graph. Note that the vertices must have been
    * created
    * earlier by calling {@code createVertexFor(T)}.
    * 
    * @param source      The source vertex of the edge.
    * @param destination The destination vertex of the edge.
    * @param weight      The weight of the edge.
    */
   public void addDirectedEdge(Vertex<T> source, Vertex<T> destination, double weight) {
      // Input checking
      if (source == null || destination == null || weight < 0.0)
         return;

      addEdge(Edge.EdgeType.DIRECTED, source, destination, weight);
   }

   /**
    * Gets the edges of the specified vertex. The vertex must be
    * already in the graph.
    * 
    * @param source The vertex edges of which we wish to get.
    * @return Returns the edges of the vertex or null if no edges from the source.
    */
   public List<Edge<T>> getEdges(Vertex<T> source) {
      return edgeList.get(source);
   }

   /**
    * Gets a vertex for the specified node (contents) in a vertex, if found.
    * If the vertex with the node value is not found, returns null.
    * Use `equals` to search for the element from the vertices.
    *
    * @param element The value of T that is in some Vertex in the graph.
    * @return The vertex containing the node, or null if no vertex contains the
    *         element.
    */
   public Vertex<T> getVertexFor(T element) {
      // Input checking
      if (element == null)
         return null;

      // Create a new vertex using element and look for it in the edgeList.
      // If found, return it, else return null
      Vertex<T> targetVertex = new Vertex<T>(element);
      if (edgeList.containsKey(targetVertex))
         return targetVertex;

      return null;
   }

   /**
    * If target is null, search is done for the whole graph. Otherwise,
    * search MUST be stopped when the target vertex is found.
    *
    * @param from   The vertex where the search is started from.
    * @param target An optional ending vertex, null if not given.
    * @return Returns all the visited vertices traversed while doing BFS, in order
    *         they were found, or an empty list.
    */
   public List<Vertex<T>> breadthFirstSearch(Vertex<T> from, Vertex<T> target) {
      // Queue for vertices that need to be visited
      ArrayQueue<Vertex<T>> queue = new ArrayQueue<Vertex<T>>();
      // Set for visited vertices
      Set<Vertex<T>> found = new HashSet<>();
      // List for visited vertices in visit order
      List<Vertex<T>> visitList = new ArrayList<>();
      // Hashtable for total distance to a given vertex
      Map<Vertex<T>, Integer> distance = new Hashtable<>();
      distance.put(from, 0);

      queue.enqueue(from);
      found.add(from);

      while (queue.isEmpty() == false) {
         // Visit a vertex from the queue
         Vertex<T> node = queue.dequeue();
         visitList.add(node);

         // If target vertex was given and found, end the search
         if (target != null && node.equals(target))
            break;

         // Go through all of it's edges
         for (Edge<T> edge : getEdges(node)) {
            Vertex<T> nextNode = edge.getDestination();
            // If the edge leads to an unvisited vertex,
            // add the vertex to queue and found, calculate distance
            if (found.contains(nextNode) == false) {
               queue.enqueue(nextNode);
               found.add(nextNode);
               distance.put(nextNode, distance.get(node) + 1);
            }
         }
      }

      // All vertices visited, returning visitList
      return visitList;
   }

   /**
    * Does depth first search (DFS) of the graph starting from a vertex.
    * <p>
    * If target is null, search is done for the whole graph. Otherwise,
    * search MUST be stopped when the target vertex is found.
    * <p>
    *
    * @param from   The vertex where the search is started from.
    * @param target An optional ending vertex, null if not given.
    * @return Returns all the visited vertices traversed while doing DFS.
    */
   public List<Vertex<T>> depthFirstSearch(Vertex<T> from, Vertex<T> target) {
      // Stack for vertices that need to be visited
      Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
      stack.push(from);
      // Set for visited vertices
      Set<Vertex<T>> visited = new HashSet<>();
      visited.add(from);
      // List for visited vertices in visit order
      List<Vertex<T>> visitList = new ArrayList<>();
      visitList.add(from);
      // Hashtable for total distance to a given vertex
      Map<Vertex<T>, Integer> distance = new Hashtable<>();
      distance.put(from, 0);

      outerLoop: // Label is needed to continue the outer loop from the inner for-loop
      while (stack.isEmpty() == false) {
         // Peek at the topmost vertex in the stack
         Vertex<T> node = stack.peek();
         // Visit all of it's edges
         for (Edge<T> edge : getEdges(node)) {
            Vertex<T> nextNode = edge.getDestination();
            // If the edge leads to an unvisited vertex,
            // add the vertex to queue and found, calculate distance
            // and visit it
            if (visited.contains(nextNode) == false) {
               stack.push(nextNode);
               visited.add(nextNode);
               visitList.add(nextNode);
               distance.put(nextNode, distance.get(node) + 1);

               // If target vertex was given and found, end the search
               if (target != null && nextNode.equals(target))
                  return visitList;

               continue outerLoop;
            }
         }
         // All edges visited, go back to previous vertex
         stack.pop();
      }

      // All vertices visited, returning visitList
      return visitList;
   }

   /**
    * Returns a non-empty list if the graph is disconnected. A disconnected graph
    * is a
    * graph that has separate areas without any connecting edges between them.
    * 
    * If the graph is disconnected, the list contains all the elements _not_
    * visited,
    * doing a breadth first search from the vertex provided as the parameter.
    * If the parameter is null, starts from the first vertice of the graph.
    * 
    * @Param toStartFrom Vertex to start investigating from. If null, start from
    *        the first vertex.
    * @return Returns non-empty list if the graph is disconnected, otherwise list
    *         is empty.
    */
   public List<T> disconnectedVertices(Vertex<T> toStartFrom) {
      // If toStartFrom was null, replace it with the first vertex in the graph
      if (toStartFrom == null) {
         toStartFrom = getVertices().stream().findFirst().get();
      }

      // If the starting vertex has no edges, return it
      if (getEdges(toStartFrom).isEmpty()) {
         List<T> notVisitedList = new ArrayList<>();
         notVisitedList.add(toStartFrom.getElement());
         return notVisitedList;
      }
      // Add all vertices to a set
      Set<Vertex<T>> notVisited = new HashSet<>(getVertices());
      Set<Vertex<T>> visited = new HashSet<>();

      // BFS the graph and add visited vertices to a set
      visited.addAll(breadthFirstSearch(toStartFrom, null));
      // Remove all visited vertices from notVisited
      notVisited.removeAll(visited);

      // Convert the set of vertices into a list of elements, then return it
      List<T> notVisitedList = new ArrayList<>();
      for (Vertex<T> vertex : notVisited)
         notVisitedList.add(vertex.getElement());
      return notVisitedList;
   }

   /**
    * Returns true if the graph is disconnected. That means, the graph
    * has areas that can not be reached from the starting vertex.
    *
    * @param toStartFrom The vertex to start the analysis from. Can be null, then
    *                    starts from first vertex.
    * @return True if the graph is disconnected.
    */
   public boolean isDisconnected(Vertex<T> toStartFrom) {
      // If disconnectedVertices returns an empty list,
      // all vertices are connected and isDisconnected returns false
      return !disconnectedVertices(toStartFrom).isEmpty();
   }

   /**
    * Checks if the graph has cycles.
    * 
    * If the graph is directed, provide true as the parameter, false for
    * undirected graphs.
    * 
    * <p>
    * NB: For this course project it is enough that this method works for
    * connected graphs. It does not need to work on disconnected graphs when
    * starting
    * from the given vertex.
    *
    * @param isDirected If true graph is directed.
    * @param fromVertex Start looking from this vertex. If null, starts from first
    *                   vertex in adjacency list.
    * @return Returns true if the graph has cycles.
    */
   public boolean hasCycles(EdgeType edgeType, Vertex<T> fromVertex) {
      // If fromVertex is null, replace it with the first vertex in the graph
      if (fromVertex == null)
         fromVertex = getVertices().stream().findFirst().get();
      // If the starting vertex has no edges, return false
      if (getEdges(fromVertex).isEmpty())
         return false;

      if (isDisconnected(fromVertex))
         return false;

      // Stack for vertices that need to be visited
      Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
      // Set for visited vertices
      Set<Vertex<T>> visited = new HashSet<>();

      stack.push(fromVertex);
      visited.add(fromVertex);

      outerLoop: // Label is needed to continue the outer loop from the inner for-loop
      while (stack.isEmpty() == false) {
         // Peek at the topmost vertex in the stack
         Vertex<T> node = stack.peek();
         // Visit all of it's edges
         for (Edge<T> edge : getEdges(node)) {
            Vertex<T> nextNode = edge.getDestination();

            if (visited.contains(nextNode)) {
               // Previously visited vertex found

               // Cycle detected if the graph type is directed.
               if (edgeType == EdgeType.DIRECTED)
                  return true;
               // If the graph is undirected, going back to the source
               // vertex from the destination doesn't count as a cycle
               else if (!node.equals(nextNode))
                  return true;
            }

            // If the edge leads to an unvisited vertex,
            // add the vertex to queue and found and visit it
            if (visited.contains(nextNode) == false) {

               stack.push(nextNode);
               visited.add(nextNode);

               continue outerLoop;
            }
         }
         // All edges visited, go back to previous vertex
         stack.pop();
      }
      // No cycles found
      return false;

   }

   // Dijkstra starts here.

   /**
    * The result of the Dijkstra's search.
    */
   public class DijkstraResult<E> {
      public boolean pathFound = false;
      public List<E> path;
      public int steps = 0;
      public double totalWeigth = 0.0;

      @Override
      public String toString() {
         StringBuilder builder = new StringBuilder();
         builder.append(String.format("Dikstra result:\n- Path found: %s%n", (pathFound ? "yes" : "no")));
         if (pathFound) {
            builder.append(String.format("- steps: %d%n", steps));
            builder.append(String.format("- total edge weights: %.2f%n", totalWeigth));
            if (null != path) {
               builder.append(String.format("- path: %s", path.toString()));
            } else {
               builder.append("Path not found\n");
            }
         }
         return builder.toString();
      }
   }

   /**
    * Finds the shortest path from start to end using Dijkstra's algorithm.
    * 
    * The return value contains information about the result.
    * 
    * @param start The vertex to start from.
    * @param end   The vertex to search the shortest path to.
    * @return An object containing information about the result of the search.
    */
   public DijkstraResult<T> shortestPathDijkstra(Vertex<T> start, Vertex<T> end) {
      DijkstraResult<T> result = new DijkstraResult<>();
      result.pathFound = false;
      result.path = null;
      result.steps = 0;
      result.totalWeigth = 0.0;

      Map<Vertex<T>, Visit<T>> pathsFromStart = shortestPathsFrom(start);
      List<Edge<T>> shortestPath = shortestPathsTo(end, pathsFromStart);

      // If shortestPath is empty, path wasn't found
      if(shortestPath.isEmpty() == false){
         // Path was found, adding it to result
         result.pathFound = true;
         result.path = new ArrayList<>();
         result.steps = shortestPath.size();
         
         // Add the first vertex from the shortest path into result.path
         result.path.add(shortestPath.get(0).getSource().getElement());
         // Loop through the edges in the path,
         // adding all the destination vertices along the way into the path list,
         // and calculating total weight of the edges
         for(Edge<T> edge: shortestPath){
            result.path.add(edge.getDestination().getElement());
            result.totalWeigth += edge.getWeigth();
         }
      }
      return result;
   }

   /**
    * Finds a route to a destination using paths already constructed.
    * Before calling this method, cal {@link shortestPathsFrom} to construct
    * the paths from the staring vertex of Dijkstra algorithm.
    *
    * A helper method for implementing the Dijkstra algorithm.
    * 
    * @param toDestination The destination vertex to find the route to.
    * @param paths         The paths to search the destination.
    * @return Returns the vertices forming the route to the destination.
    */
   private List<Edge<T>> route(Vertex<T> toDestination, Map<Vertex<T>, Visit<T>> paths) {
      // List of edges for holding the path to destination vertex
      List<Edge<T>> path = new ArrayList<>();

       if(paths.size() == 0)
         return path;
   
      // Start at the destination vertex
      Vertex<T> vertex = toDestination;
      // Step back a visit from the destination
      Visit<T> visit = paths.get(vertex);

      // Step through visits until start visit is reached
      while(visit.type != Visit.Type.START){
         // Add the edge we just traversed to the path
         path.add(0, visit.edge);
         // Step to the next visit
         vertex = visit.edge.getSource();
         visit = paths.get(vertex);
      }
      // All visits traversed, returning path
      return path;
   }

   /**
    * Calculates the total distance of visits from start to destination
    * @param toDestination The destination vertex.
    * @param viaPath Dictionary of visits from start to destination
    * @return Returns sum of edge weights travelled from start to destination.
    */
   private double distance(Vertex<T> toDestination, Map<Vertex<T>, Visit<T>> viaPath) {
      double totalDistance = 0.0;

      // Get the route from start to destination using the path in viaPath
      List<Edge<T>> path = route(toDestination, viaPath);
      // Loop through the edges in the path and sum their weights
      for(Edge<T> edge: path)
         totalDistance += edge.getWeigth();
      
      return totalDistance;
   }

   /*
    * Comparator for comparing path distances
    */
   class DistanceComparator implements Comparator<Vertex<T>>{
      private Graph<T> graph;
      private Map<Vertex<T>, Visit<T>> paths;

      public DistanceComparator(Graph<T> graph, Map<Vertex<T>, Visit<T>> paths){
         this.graph = graph;
         this.paths = paths;
      }

      @Override
      public int compare(Vertex<T> left, Vertex<T> right){
         return (int)(graph.distance(left, paths) - graph.distance(right, paths));
      }
   }

   /**
    * Finds the shortest paths in the graph from the starting vertex.
    *
    * In doing Dijkstra, first call this method, then call {@link route}
    * with the paths collected using this method, to get the shortest path to the
    * destination.
    *
    * @param start The starting vertex for the path searching.
    * @return Returns the visits from the starting vertex.
    * @see oy.tol.tira.graph.Graph#route(Vertex, Map)
    */
   private Map<Vertex<T>, Visit<T>> shortestPathsFrom(Vertex<T> start) {
      // Map for visits from vertices
      Map<Vertex<T>, Visit<T>> paths = new HashMap<>();
      // Visit for holding the starting visit
      Visit<T> visit = new Visit<>();
      visit.type = Visit.Type.START;
      // Add starting path from starting vertex to paths
      paths.put(start, visit);
      // Priority Queue for holding verticies, sorted by distance
      PriorityQueue<Vertex<T>> priorityQueue = new PriorityQueue<>(new DistanceComparator(this, paths));
      priorityQueue.add(start);

      // Loop through the priority queue until it is empty
      while(priorityQueue.isEmpty() == false){
         // Get top vertex from the priority queue
         Vertex<T> vertex = priorityQueue.remove();
         // Loop through its edges
         for(Edge<T> edge: getEdges(vertex)){
            double weight = edge.getWeigth();
            // If the edge leads to an unvisited vertex OR
            // going through it leads to a shorter path,
            // add it to paths and its destination to priority queue
            if(paths.containsKey(edge.getDestination()) == false ||
               distance(vertex, paths) + weight < distance(edge.getDestination(), paths)){
                  paths.put(edge.getDestination(),new Visit<T>(Type.EDGE, edge));
                  priorityQueue.add(edge.getDestination());
               }
         }

      }
      // All paths found, returning
      return paths;
   }

   /**
    * Finds the shortest path to the destination vertex.
    * @param destination destination vertex
    * @param paths Map of the shortest paths from starting vertex
    * @return Returns the shortest path to destination as a list of edges
    */
   private List<Edge<T>> shortestPathsTo(Vertex<T> destination, Map<Vertex<T>, Visit<T>> paths){
      return route(destination, paths);
   }

   // OPTIONAL task in the course!
   /**
    * Do breadth-first-search on the grap and export vertices and edges to a dot
    * file
    *
    * Note that if the graph is disconnected, you must check if some vertices
    * were not visited and continue the BFS until _all_ vertices have been visited.
    * Otherwise, part of the graph is missing from the output file.
    *
    * The simplest way to do this is to first start from the given vertex, do
    * the BFS saving data to dot file. Then, after this loop, get the disconnected
    * vertices starting from
    * the starting vertex by calling disconnectedVertices(from). If there are not
    * visited vertices,
    * then pick one of the non visited vertices to be the new starting vertex
    * (from).
    * Repeat this until all vertices have been visited. Basically you need an outer
    * loop repeating
    * the BFS in the inner loop, and the outer loop stops when all vertices have
    * been visited.
    *
    * @param from           Start the BFS from this vertex.
    * @param outputFileName Write the dot to this text file.
    * @throws IOException If something goes wrong with file operations.
    */
   public void toDotBFS(Vertex<T> from, String outputFileName) throws IOException {
      // TODO: Student, implement this if you want to (optional task).
   }

   // STUDENTS: Uncomment the code below and use it as a sample on how
   // to interate over vertices and edges in one situation.
   // If you use some other name for your edge list than edgeList, then
   // rename that in the code below! Otherwise you will have compiler errors.
   /**
    * Provides a string representation of the graph, printing out the vertices and
    * edges.
    * <p>
    * Quite useful if you need to debug issues with algorithms. You can see is the
    * graph
    * what it is supposed to be like.
    * <p>
    * Simple graph as a string would look like this:<br/>
    * 
    * <pre>
    * Created simple undirected graph where integers are vertice values:
    * [1] -> [ 2 ]
    * [2] -> [ 1, 3, 4, 5 ]
    * [3] -> [ 2, 4, 5 ]
    * [4] -> [ 2, 3, 5 ]
    * [5] -> [ 2, 3, 4 ]
    * </pre>
    * 
    * @return The graph as a string.
    */
   @Override
   public String toString() {

      StringBuilder output = new StringBuilder();
      for (Map.Entry<Vertex<T>, List<Edge<T>>> entry : edgeList.entrySet()) {
         output.append("[");
         output.append(entry.getKey().toString());
         output.append("] -> [ ");
         int counter = 0;
         int count = entry.getValue().size();
         for (Edge<T> edge : entry.getValue()) {
            output.append(edge.getDestination().toString());
            if (counter < count - 1) {
               output.append(", ");
            }
            counter++;
         }
         output.append(" ]\n");
      }
      return output.toString();
   }
}
