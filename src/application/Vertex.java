package application;

import java.util.*;

public class Vertex {
	public String data; // data contained inside each node. also used as key identifier for simplicity.
	public int distance; // distance from the start vertex.
	public Map<String, Vertex> neighbors;// holds each connection to this vertex.
	
	// constructors.
	public Vertex() {
		data = "a";
		distance = 0;
		neighbors = new HashMap<String, Vertex>();
	}
	public Vertex(String _data) {
		this.data = _data;
		neighbors = new HashMap<String, Vertex>();
	}
	
}
