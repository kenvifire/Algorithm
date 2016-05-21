import edu.princeton.cs.algs4.*;

/**
 * Created by hannahzhang on 15/3/29.
 */
import edu.princeton.cs.algs4.*;
public class SAP {
    private Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        this.digraph = new Digraph(G);
    }


    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int dist = Integer.MAX_VALUE;
        boolean hasPath = false;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int temp = bfsV.distTo(i) + bfsW.distTo(i);
                hasPath = true;
                if (dist > temp) {
                    dist = temp;
                }
            }
        }

        if (hasPath) {
            return dist;
        } else {
            return -1;
        }

    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int dist = Integer.MAX_VALUE;
        int common = -1;
        boolean hasPath = false;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int temp = bfsV.distTo(i) + bfsW.distTo(i);
                hasPath = true;
                if (dist > temp) {
                    dist = temp;
                    common = i;
                }
            }
        }

        if (hasPath) {
            return common;
        } else {
            return -1;
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int dist = Integer.MAX_VALUE;
        boolean hasPath = false;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int temp = bfsV.distTo(i) + bfsW.distTo(i);
                hasPath = true;
                if (dist > temp) {
                    dist = temp;
                }
            }
        }

        if (hasPath) {
            return dist;
        } else {
            return -1;
        }
    }


    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int dist = Integer.MAX_VALUE;
        int common = -1;
        boolean hasPath = false;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int temp = bfsV.distTo(i) + bfsW.distTo(i);
                hasPath = true;
                if (dist > temp) {
                    dist = temp;
                    common = i;
                }
            }
        }

        if (hasPath) {
            return common;
        } else {
            return -1;
        }

    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
