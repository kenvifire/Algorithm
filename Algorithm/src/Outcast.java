import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hannahzhang on 15/3/29.
 */
public class Outcast {
    private WordNet wordNet;

    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    public String outcast(String[] nouns) {   // given an array of WordNet nouns, return an outcast
        String outcast = null;
        int distance = 0;


        for (int i = 0; i < nouns.length; i++) {
            int temp = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i != j) {
                    temp += wordNet.distance(nouns[i], nouns[j]);
                    //System.out.println(String.format("distance from %s to %s is %d",nouns[i],nouns[j],wordNet.distance(nouns[i],nouns[j])));
                }
            }

            if (temp > distance) {
                distance = temp;
                outcast = nouns[i];
            }
        }
        return outcast;

    }

    public static void main(String[] args) { // see test client below
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}
