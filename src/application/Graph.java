package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

public class Graph  {
	private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
	private Vector<Vertex> allVertex = new Vector<Vertex>();
	
	//DFS vars.
	private Map<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
	private Queue<Vertex> q = new LinkedList<Vertex>();
	private Vector<Vertex> DFSPath = new Vector<Vertex>(); //stores a list of the DFS path.
	
	// default constructor. 
	public Graph() {
		Vertex v = new Vertex();
		vertexMap.put(v.data, v);
		allVertex.add(v);
	}
	
	// getter methods for private elements.
	public Vertex getVertex(String key) { 
		if (vertexMap.containsKey(key)) {
			return vertexMap.get(key);
		}
		else {
			return null;
		}
	}
	public Vector<Vertex> getAllVertex() { return allVertex; }
	
	// insert vertex into graph under parent vertex.
	public void InsertVertex(Vertex parent, String data) {
		if (parent.data.equals(data) || parent.neighbors.containsKey(data)) return;
		
		Vertex v = null;
		// only add new vertices if they don't already exist.
		if (!vertexMap.containsKey(data)) {
			v = new Vertex(data);
			allVertex.add(v);
			vertexMap.put(data, v);
		}
		else {
			v = vertexMap.get(data);
		}
		
		parent.neighbors.put(data, v);
		return;
	}
	
	// DFS search constructs the DFS path.
	private boolean DFS(Vertex start, Vertex end) {

		visited.put(start, true);
		if (start == end) {
			DFSPath.add(start);
			return true;
		}

		// add all neighbors to queue.
		for (Map.Entry<String, Vertex> entry : start.neighbors.entrySet()) {
			if (!visited.containsKey(entry.getValue())) {
				//q.add(entry.getValue());
				if (DFS(entry.getValue(), end)) {
					DFSPath.add(start);
					return true;
				}
			}
		}
		return false;
	}
	
	public Vector<Vertex> getDFSPath(Vertex s, Vertex e) {
		DFSPath.clear();
		visited.clear();
		
		DFS(s, e);
		return DFSPath;
	}

}
