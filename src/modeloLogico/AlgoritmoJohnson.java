package modeloLogico;


import java.util.*;

import modeloEstructuraDatos.DiGraph;
import modeloEstructuraDatos.Edge;
import modeloEstructuraDatos.Vertex;

public class AlgoritmoJohnson {

	private Set<Vertex<Integer,String>> blockedSet;
	private Map<Vertex<Integer,String>, Set<Vertex<Integer,String>>> blockedMap;
	private Deque<Vertex<Integer,String>> stack;
	private List<List<Vertex<Integer,String>>> allCycles;
	private boolean maximo;

	public List<List<Vertex<Integer,String>>> simpleCyles(DiGraph<Integer,String> graph, Vertex<Integer,String> vertice) {
		//if(graph.numVertices()<2) return null;
		blockedSet = new HashSet<>();
		blockedMap = new HashMap<>();
		stack = new LinkedList<>();
		maximo = false;
		allCycles = new ArrayList<>();
		blockedMap.clear();
		findCyclesInSCG(vertice,vertice);
		return allCycles;
	}
	
	public List<List<Vertex<Integer,String>>> cycles(DiGraph<Integer,String> graph, Vertex<Integer,String> verticeInicio, Vertex<Integer,String> verticeFin) {
		//if(graph.numVertices()<2) return null;
		blockedSet = new HashSet<>();
		blockedMap = new HashMap<>();
		stack = new LinkedList<>();
		maximo = false;
		allCycles = new ArrayList<>();
		blockedMap.clear();
		findCyclesInSCG(verticeInicio,verticeFin);
		return allCycles;
	}

	private void unblock(Vertex<Integer,String> u) {
		blockedSet.remove(u);
		if(blockedMap.get(u) != null) {
			blockedMap.get(u).forEach( v -> {
				if(blockedSet.contains(v)) {
					unblock(v);
				}
			});
			blockedMap.remove(u);
		}
	}

	private boolean findCyclesInSCG(
			Vertex<Integer,String> startVertex,
			Vertex<Integer,String> currentVertex) {
		boolean foundCycle = false;
		stack.push(currentVertex);
		blockedSet.add(currentVertex);

		if(currentVertex!=null) {
		for (Edge<Integer,String> e : currentVertex.edges()) {
			if(maximo) break;
			Vertex<Integer,String> neighbor = e.getDest();
			if (neighbor == startVertex) {
				List<Vertex<Integer,String>> cycle = new ArrayList<>();
				stack.push(startVertex);
				cycle.addAll(stack);
				Collections.reverse(cycle);
				stack.pop();
				allCycles.add(cycle);
				foundCycle = true;
			} //solo revisa el vecino si no está bloqueado
			else if (!blockedSet.contains(neighbor)) {
				boolean gotCycle =
						findCyclesInSCG(startVertex, neighbor);
				foundCycle = foundCycle || gotCycle;
			}
		}
		}
		if (foundCycle) {
			unblock(currentVertex);
		} else {
			for (Edge<Integer,String> e : currentVertex.edges()) {
				Vertex<Integer,String> w = e.getDest();
				Set<Vertex<Integer,String>> bSet = getBSet(w);
				bSet.add(currentVertex);
			}
		}
		if(allCycles.size()>999999) maximo = true;
		//removemos el vertice del stack
		stack.pop();
		return foundCycle;
	}

	private Set<Vertex<Integer,String>> getBSet(Vertex<Integer,String> v) {
		return blockedMap.computeIfAbsent(v, (key) ->
		new HashSet<>() );
	}

}