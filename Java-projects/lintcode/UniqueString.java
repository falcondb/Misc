import java.util.*;

public class UniqueString {
	
	
	public  static void main(String[] args){
		ArrayList<String>  ss = new ArrayList<String>();
		
		ss.add("adbd");
		ss.add("abbb");
		ss.add("ad");
		ss.add("addd");
		ss.add("aaa");
		
		Set<String> re = new UniqueString().getUnique(ss);
		Iterator<String> it = re.iterator();
		while(it.hasNext()){
			
			System.out.println(it.next());
		}
	}

	public Set<String> getUnique(ArrayList<String> ss) {

		if (ss == null)
			return null;
		Set<String> res = new HashSet<String>();

		boolean[] tags;
		for (String s : ss) {
			tags = new boolean[26];
			for (int p = 0; p < s.length(); ++p) {
				if (!tags[s.charAt(p) - 'a'])
					tags[s.charAt(p) - 'a'] = true;
			}

			StringBuffer ts = new StringBuffer();
			for (int i = 0; i < tags.length; ++i)
				if (tags[i])
					ts.append((char)('a' + i));

			res.add(ts.toString());
		}

		return res;
	}
}
