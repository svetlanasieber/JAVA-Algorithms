import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
 
public class BoruvkasEcho
{
    private Bag<Edge> mst = new Bag<Edge>();  
    private double weight;                    
 
    public BoruvkasMST(EdgeWeightedGraph G)
    {
        UF uf = new UF(G.V());
        for (int t = 1; t < G.V() && mst.size() < G.V() - 1; t = t + t)
        {

            Edge[] closest = new Edge[G.V()];
            for (Edge e : G.Edge())
            {
                int v = e.either(), w = e.other(v);
                int i = uf.find(v), j = uf.find(w);
                if (i == j)
                    continue; 
                if (closest[i] == null || less(e, closest[i]))
                    closest[i] = e;
                if (closest[j] == null || less(e, closest[j]))
                    closest[j] = e;
            }
            for (int i = 0; i < G.V(); i++)
            {
                Edge e = closest[i];
                if (e != null)
                {
                    int v = e.either(), w = e.other(v);
                    if (!uf.connected(v, w))
                    {
                        mst.add(e);
                        weight += e.weight();
                        uf.union(v, w);
                    }
                }
            }
        }

        assert check(G);
    }
 
    public Iterable<Edge> Edge()
    {
        return mst;
    }
 
    public double weight()
    {
        return weight;
    }

    private static boolean less(Edge e, Edge f)
    {
        return e.weight() < f.weight();
    }
    private boolean check(EdgeWeightedGraph G)
    {

        double totalWeight = 0.0;
        for (Edge e : Edge())
        {
            totalWeight += e.weight();
        }
        double EPSILON = 1E-12;
        if (Math.abs(totalWeight - weight()) > EPSILON)
        {
            System.err.printf(
                    "Weight of Edge does not equal weight(): %f vs. %f\n",
                    totalWeight, weight());
            return false;
        }
        UF uf = new UF(G.V());
        for (Edge e : Edge())
        {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w))
            {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }
    
        for (Edge e : G.Edge())
        {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w))
            {
                System.err.println("Not a spanning forest");
                return false;
            }
        }
     
        for (Edge e : Edge())
        {
            // all Edge in MST except e
            uf = new UF(G.V());
            for (Edge f : mst)
            {
                int x = f.either(), y = f.other(x);
                if (f != e)
                    uf.union(x, y);
            }
            for (Edge f : G.Edge())
            {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y))
                {
                    if (f.weight() < e.weight())
                    {
                        System.err.println("Edge " + f
                                + " violates cut optimality conditions");
                        return false;
                    }
                }
            }
        }
        return true;
    }
 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of verties: ");
        EdgeWeightedGraph G = new EdgeWeightedGraph(sc.nextInt());
        BoruvkasMST mst = new BoruvkasMST(G);
        System.out.println("MST: ");
        for (Edge e : mst.Edge())
        {
            System.out.println(e);
        }
        System.out.printf("Total weight of MST: %.5f\n", mst.weight());
        sc.close();
    }
}
 
class BagOfItems<Item> implements Iterable<Item>
{
    private int N;              
    private Node<Item> first;   
 
    private static class Node<Item>
    {
        private Item item;
        private Node<Item> next;
    }
 
    public BagOfItems()
    {
        first = null;
        N = 0;
    }
 
    public boolean isEmpty()
    {
        return first == null;
    }
 
    public int size()
    {
        return N;
    }
 
    public void add(Item item)
    {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
 
    public Iterator<Item> iterator()
    {
        return new ListIterator<Item>(first);
    }
 
 
    @SuppressWarnings("hiding")
    private class ListIterator<Item> implements Iterator<Item>
    {
        private Node<Item> current;
 
        public ListIterator(Node<Item> first)
        {
            current = first;
        }
 
        public boolean hasNext()
        {
            return current != null;
        }
 
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
 
        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
 
class EdgeWeightedGraph
{
    private final int V;
    private final int E;
    private Bag<Edge>[] adj;
 
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V)
    {
        Scanner sc = new Scanner(System.in);
        if (V < 0)
        {
            sc.close();
            throw new IllegalArgumentException(
                    "Number of vertices must be nonnegative");
        }
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
        {
            adj[v] = new Bag<Edge>();
        }
        System.out.println("Enter the number of Edge: ");
        E = sc.nextInt();
        if (E < 0)
        {
            sc.close();
            throw new IllegalArgumentException(
                    "Number of Edge must be nonnegative");
        }
        System.out.println("Enter the Edge: <from> <to>");
        for (int i = 0; i < E; i++)
        {
            int v = sc.nextInt();
            int w = sc.nextInt();
            double weight = Math.round(100 * Math.random()) / 100.0;
            System.out.println(weight);
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
        sc.close();
    }
 
    public int V()
    {
        return V;
    }
 
    public int E()
    {
        return E;
    }
 
    public void addEdge(Edge e)
    {
        int v = e.either();
        int w = e.other(v);
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v
                    + " is not between 0 and " + (V - 1));
        if (w < 0 || w >= V)
            throw new IndexOutOfBoundsException("vertex " + w
                    + " is not between 0 and " + (V - 1));
        adj[v].add(e);
        adj[w].add(e);
    }
 
    public Iterable<Edge> adj(int v)
    {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v
                    + " is not between 0 and " + (V - 1));
        return adj[v];
    }
 
    public Iterable<Edge> Edge()
    {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++)
        {
            int selfLoops = 0;
            for (Edge e : adj(v))
            {
                if (e.other(v) > v)
                {
                    list.add(e);
                }
                else if (e.other(v) == v)
                {
                    if (selfLoops % 2 == 0)
                        list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }
 
    public String toString()
    {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++)
        {
            s.append(v + ": ");
            for (Edge e : adj[v])
            {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
 
class Edge implements Comparable<Edge>
{
    private final int v;
    private final int w;
    private final double weight;
 
    public Edge(int v, int w, double weight)
    {
        if (v < 0)
            throw new IndexOutOfBoundsException(
                    "Vertex name must be a nonnegative integer");
        if (w < 0)
            throw new IndexOutOfBoundsException(
                    "Vertex name must be a nonnegative integer");
        if (Double.isNaN(weight))
            throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
 
    public double weight()
    {
        return weight;
    }
 
    public int either()
    {
        return v;
    }
 
    public int other(int vertex)
    {
        if (vertex == v)
            return w;
        else if (vertex == w)
            return v;
        else
            throw new IllegalArgumentException("Illegal endpoint");
    }
 
    public int compareTo(Edge that)
    {
        if (this.weight() < that.weight())
            return -1;
        else if (this.weight() > that.weight())
            return +1;
        else
            return 0;
    }
 
    public String toString()
    {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
 
class UF
{
    private int[] id;    
    private byte[] rank; 
                        
    private int count;   
 
    public UF(int N)
    {
        if (N < 0)
            throw new IllegalArgumentException();
        count = N;
        id = new int[N];
        rank = new byte[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            rank[i] = 0;
        }
    }
 
    public int find(int p)
    {
        if (p < 0 || p >= id.length)
            throw new IndexOutOfBoundsException();
        while (p != id[p])
        {
            id[p] = id[id[p]];   
            p = id[p];
        }
        return p;
    }
 
    public int count()
    {
        return count;
    }
 
    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }
 
    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;
       
        if (rank[i] < rank[j])
            id[i] = j;
        else if (rank[i] > rank[j])
            id[j] = i;
        else
        {
            id[j] = i;
            rank[i]++;
        }
        count--;
    }
}
