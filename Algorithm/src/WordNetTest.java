import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by hannahzhang on 15/3/29.
 */
public class WordNetTest extends TestCase{
    private WordNet wordNet;
    @Before
    public void setUp(){
        //wordNet = new WordNet("input/synsets.txt","input/hypernyms.txt");
    }

    @Test
    public void testException(){
        try{
            new WordNet(null,null);
        }catch (IllegalArgumentException e){
            return;
        }
        Assert.that(false,"should not reach here");
    }

    @Test
    public void testIterable(){
        for(String str : wordNet.nouns()){
            Assert.that(wordNet.isNoun(str),str + " is not a noun, really?");
        }
    }

//    @Test
//    public void testIsNoun(){
//      //  Assert.that(wordNet.isNoun("中国人")==false,"中国人 is a noun, really?" );
//    }

    @Test
    public void TestDistanceException(){
        try{
            wordNet.distance("中国人","日本人");
        }catch (IllegalArgumentException e){
            return;
        }
    }

    @Test
    public void testDistance(){

    }

    @Test
    public void testSapException(){
        try{
            wordNet.distance("中国人","日本人");
        }catch (IllegalArgumentException e){
            return;
        }
    }

    @Test
    public void testSap(){

    }

    @Test
    public void testHypernyms3InvalidCycle(){
        try {
            wordNet = new WordNet("input/synsets3.txt", "input/hypernyms3InvalidCycle.txt");
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return;
        }

        Assert.that(false,"invalid with cycle");
    }

    @Test
    public void testHypernyms3InvalidTwoRoots(){
        try {
            wordNet = new WordNet("input/synsets3.txt", "input/hypernyms3InvalidTwoRoots.txt");
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return;
        }

        Assert.that(false,"invalid with too roots");
    }

    @Test
    public void testDigraph1(){
        wordNet = new WordNet("input/synsets11.txt","input/hypernyms-digraph1.txt");
        System.out.println(wordNet.distance("k","a"));
        Assert.that(wordNet.distance("d","f")==2,"is that wrong");
    }
    @Test
    public void testDigraph2(){
        wordNet = new WordNet("input/synsets6.txt","input/hypernyms-digraph2.txt");
        System.out.println(wordNet.distance("b","f"));
        Assert.that(wordNet.distance("b","f")==2,"is that wrong");
    }
    @Test
    public void testDigraph2_1(){
        wordNet = new WordNet("input/synsets6.txt","input/hypernyms-digraph2.txt");
        System.out.println(wordNet.distance("b", "f"));
        Assert.that(wordNet.distance("a","b")==1,"is that wrong");
    }

    @Test
    public void testSap_1(){
        wordNet = new WordNet("input/synsets.txt","input/hypernyms.txt");
        Assert.that(wordNet.distance("Somali","judicial_branch")==14,"is that wrong");
        System.out.println(wordNet.sap("Somali", "judicial_branch"));
    }
    @Test
    public void testSap_2(){
        wordNet = new WordNet("input/synsets.txt","input/hypernyms.txt");
        System.out.println(((Set<String>) wordNet.nouns()).size());
        System.out.println(wordNet.sap("tax_bracket", "black_humour"));
        System.out.println(wordNet.sap("zither","Belmont"));
        System.out.println(wordNet.sap("Biblical_Aramaic","Psephurus"));
    }

}
