import java.util.*;

public class WordNet {
    private List<Synset> synsetList;
    private List<SynWord> wordList;
    private Set<String> nouns;
    private SAP sap;


    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        In synsetIn = new In(synsets);
        synsetList = new ArrayList<Synset>();
        wordList = new ArrayList<SynWord>();
        nouns = new HashSet<String>();
        //read words
        String line;
        int count = 0;
        while ((line = synsetIn.readLine()) != null) {
            String[] params = line.split(",");
            Synset synset = new Synset(line);
            synsetList.add(synset);
            List<String> words = Arrays.asList(synset.words.split(" "));
            nouns.addAll(words);
            for (String word : words) {
                SynWord synWord = new SynWord(synset.id, word);
                wordList.add(synWord);
            }
            count++;
        }
        Collections.sort(wordList);

        //read relationship
        Digraph rootDAG = new Digraph(count);
        In hypernymsIn = new In(hypernyms);
        while ((line = hypernymsIn.readLine()) != null) {
            String[] params = line.split(",");
            int v = Integer.valueOf(params[0]);
            for (int i = 1; i < params.length; i++) {
                int w = Integer.valueOf(params[i]);
                rootDAG.addEdge(v, w);
            }
        }
        //check cycle
        DirectedCycle cycle = new DirectedCycle(rootDAG);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException();
        }

        //check root
        int rootCount = 0;
        for (int i = 0; i < synsetList.size(); i++) {
            if (rootDAG.outdegree(i) == 0) {
                rootCount++;
                if (rootCount != 1) {
                    throw new IllegalArgumentException();
                }
            }
        }
        this.sap = new SAP(rootDAG);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return getSetId(word, wordList) >= 0;

    }

    private int getSetId(String key, List<SynWord> wordList) {
        int lo = 0;
        int hi = wordList.size() - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key.compareTo(wordList.get(mid).word) < 0) hi = mid - 1;
            else if (key.compareTo(wordList.get(mid).word) > 0) lo = mid + 1;
            else return wordList.get(mid).setId;
        }
        return -1;
    }

    private List<Integer> getAllSetIds(String key, List<SynWord> wordList) {
        int lo = 0, mid = 0;
        int hi = wordList.size() - 1;

        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            mid = lo + (hi - lo) / 2;
            if (key.compareTo(wordList.get(mid).word) < 0) hi = mid - 1;
            else if (key.compareTo(wordList.get(mid).word) > 0) lo = mid + 1;
            else break;
        }
        if (lo > hi) {
            return Collections.emptyList();
        }
        //search for all keys;
        List<Integer> allSetId = new ArrayList<Integer>();
        allSetId.add(wordList.get(mid).setId);
        int index = mid - 1;
        while (index > 0 && wordList.get(index).word.compareTo(key) == 0) {
            allSetId.add(wordList.get(index).setId);
            index--;
        }
        index = mid + 1;
        while (index < wordList.size() && wordList.get(index).word.compareTo(key) == 0) {
            allSetId.add(wordList.get(index).setId);
            index++;
        }

        return allSetId;

    }


    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        List<Integer> setIdAList = getAllSetIds(nounA, wordList);
        List<Integer> setIdBList = getAllSetIds(nounB, wordList);
        if (setIdAList.size() == 0 || setIdBList.size() == 0) {
            throw new IllegalArgumentException();
        }

        return sap.length(setIdAList, setIdBList);

    }


    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        List<Integer> setIdAList = getAllSetIds(nounA, wordList);
        List<Integer> setIdBList = getAllSetIds(nounB, wordList);
        if (setIdAList.size() == 0 || setIdBList.size() == 0) {
            throw new IllegalArgumentException();
        }
        int common = sap.ancestor(setIdAList, setIdBList);
        return synsetList.get(common).words;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        //read wordnet

    }

    private class Synset {
        int id;
        String words;
        // String gloss;

        public Synset(String line) {
            int index = line.indexOf(',');
            String idStr = line.substring(0, index);
            this.id = Integer.valueOf(idStr);
            int end = line.indexOf(',', index + 1);
            words = line.substring(index + 1, end);
            //   this.gloss = line.substring(end+1,line.length()-1);
        }
    }

    private class SynWord implements Comparable<SynWord> {
        int setId;
        String word;

        public SynWord(int setId, String word) {
            this.setId = setId;
            this.word = word;
        }

        @Override
        public int compareTo(SynWord synWord) {
            return this.word.compareTo(synWord.word);
        }
    }
}