package ueb2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class Kasiski {
//    final static String CIPHER = "XJATBYBVKBFONLBVUWWUXBXWGJAIUWRHBTWGNJXWWWWPICOVBSTVTASKAMVLJPGIMDJYFCOVJKB"
//	    + "MTWXPMCBLNVGJBKNLKBBMKKXUTWNAXQOALLGRBZWLGQNTJYXQDZILKQOXTYFIUAPCHZIWWYLKIW"
//	    + "SKXVUJJUWLBKXHGEFFIBGOFFRLAZVFITXPSNJYMMJDYLTVXWSKNVHWSZBVEVJYVWNHZAXZXAWKL"
//	    + "WNAYOXCUWMHXCGALUBKILRLAZBDXYXKIFJYLWOVJYGIMKFULKIDZZLOFJFLMNVWWKTAJFYLKVFL"
//	    + "BHAZHWSVFUFFKYTOFFWBGLVEIPXDFJSLMHVFLZIQFDJUNVCWXAKMJLGHKAFAYCBMMWSQTPSWSPF"
//	    + "JFJJPVPEWWPGNPJRHMQLWNUXPFJFBLZBYJUWMSGQSXLFJGHVPFDTYLBVVNLGOBFLPGBFJSLMJBK"
//	    + "NLKBFKDZMMNWNZMMJFXWXHJSQPLQFJYLKQOXTYFIUAPZMCEAJUZIOYILKAJWFBYMJFJHGAQJZJA"
//	    + "AWGQSXJFJZMLBBWYPZSFAYIXAPFILKAJFILGNPDLLGLFFGLKMJUMLGDPJGLKMJLJALWGLBHKMFF"
//	    + "YDBKLDZUZQOKGLLWOVJYXIVUMMNMSNJYMMJDYLTVXWSKNVHWSHNNCSZHWUJFNZMZBLNVGCOVGLM"
//	    + "ZJWGCHVSWHOGMSFJASMOMSAXZCWXVGLFJJYUMSMJJDAJUMABOVFLCHVTAHOXZIWNALITHJRMMOL"
//	    + "WVMHEWWZIMAAFSBAJWWBGOTLJOMLFFFILWMNJUMQOFJUNVESGZHTWWSAXVFASIKMJLJZUMTUMHX"
//	    + "NUALBGOTXJSWWGXJUWMOFEBFMJFJUTZCWNAXBIWZAXMJFJCBMMRFOELFJNUYWSEFABSFJNUGMOM"
//	    + "SKBVGGWTTBJCJYBVHWSHNLJWXLFAQWEPTTJKNLKCOYXIXZFAHOSCNSSKXZFFJYPMSTJUWQFKYBW"
//	    + "QFJJUWMONJYMQFXYLDMOFYUBATWNTUMSWNJALFJXVYBXSWLXVUONJDTVFLBGLTASKLWNAYMNMSS"
//	    + "QSXJFJJPVPFVJYLWGLBHKMFFYDBKLDZUZMJFXLMHCSW";

    final static String CIPHER = "AABEDDEAAEDBAEDDE";
	
    public static LinkedList<String> groupBy(String s, int n) {
	LinkedList<String> l = new LinkedList<String>();
	char[] b = new char[n];
	char[] c = s.toCharArray();
	try{
	for (int i = 0; i < c.length; i++) {
	    for (int j = 0; j < n; j++, i++) {
		b[j] = c[i];
	    }
	    l.add(new String(b));
	}} catch (IndexOutOfBoundsException e) {
	}
	return l;
    }

    public static Multimap<String, Integer> find(LinkedList<String> s) {
	Multimap<String, Integer> m = HashMultimap.create();
	int pos = 0;
	while(!s.isEmpty())
	{
	    String t = s.poll();
	    int nextpos = 1;
	    for (String u : s) 
	    {
		if(t.equals(u))
		{
		    m.put(u,nextpos);
		    break;
		}
		nextpos++;
	    }
	    pos++;
	}
	return m;
    }

    public static void main(String[] args) {
	for (int i = 0; i < (CIPHER.length()/2)-1; i++) 
	{
	    Multimap<String, Integer> m = find(groupBy(CIPHER, i));
	    out(m);
	}
    }

    private static void out(Multimap<String, Integer> m) {
	for (Entry<String, Collection<Integer>> e : m.asMap().entrySet()) 
	{
	    System.out.print(e.getKey()+"\t\t\t:\t\t");
	    for (Integer i : e.getValue()) {
		System.out.print(i+" ");
	    }
	    System.out.println();
	}
    }

}
