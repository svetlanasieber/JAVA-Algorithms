# Bellman-Ford Algorithm

The Bellman-Ford algorithm is a graph search algorithm that calculates the shortest paths from a single source vertex to all other vertices in a weighted graph. Unlike Dijkstra's algorithm, which cannot handle negative weight edges, the Bellman-Ford algorithm accommodates such edges effectively.

## Key Features of the Bellman-Ford Algorithm

### Handling Negative Weights
The Bellman-Ford algorithm can process graphs with negative weight edges and detect negative weight cycles. If a negative weight cycle is detected, it signifies that no shortest path calculation is feasible as the path cost can decrease indefinitely by continually cycling.

### Dynamic Programming Approach
The algorithm leverages a dynamic programming technique to progressively improve an estimate of the shortest path length from the source to each vertex. It achieves this by iterating through each edge of the graph and relaxing the edge if a shorter path is discovered.

## Algorithm Steps

1. **Initialization**
   - Initialize the distances to all vertices as infinite, except the source vertex which is set to zero.
   
2. **Relaxation**
   - For each edge `(u, v)` with weight `w`, update the distance to `v` to `distance[u] + w` if this new path is shorter.
   
3. **Negative Cycle Detection**
   - After relaxing all edges `n-1` times (where `n` is the number of vertices), perform one more iteration to check for any further distance decreases. If any exist, it indicates the presence of a negative weight cycle.

## Complexity

The Bellman-Ford algorithm runs in \(O(VE)\) time, where \(V\) is the number of vertices and \(E\) is the number of edges. This is generally less efficient for graphs with a large number of edges compared to Dijkstra’s algorithm, which can achieve better time complexity in graphs without negative weights.

## Use Cases

- **Routing Protocols:** Employed in protocols like BGP (Border Gateway Protocol) to ensure robustness against potentially negative paths that might represent cost, congestion, or other metrics.
- **Financial Applications:** Useful for detecting arbitrage opportunities in currency exchange, where a negative cycle would represent a potential profit.

The Bellman-Ford algorithm is an essential concept in computer science and operations research, offering a robust method for handling complex graph problems.
