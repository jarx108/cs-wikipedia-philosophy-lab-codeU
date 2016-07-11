package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;
import java.util.HashSet;

public class WikiPhilosophy {
	
	final static WikiFetcher wf = new WikiFetcher();
	
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * 
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 * 
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
        // some example code to get you started

		String url = "https://en.wikipedia.org/wiki/";
		String page = "java_(programming_language)";
		HashSet<String> set = new HashSet<String>();
		int count = 20;
		while(page.toLowerCase()!="philosophy") {
			count--;
			System.out.println(count);
			if(count==0)break;

			Elements paragraphs = wf.fetchWikipedia(url + page);

			Element firstPara = paragraphs.get(0);

			Iterable<Node> iter = new WikiNodeIterable(firstPara);
			for (Node node : iter) {
				if (node instanceof TextNode) {
					String s = node.toString();
					//System.out.println(s);
					if(s.matches("^[a-z].*")&&!s.toLowerCase().equals(page.toLowerCase())){
						System.out.println("----"+s);
						if(!set.add(s)){
							//throw new UnsupportedOperationException("");
						}

						page = s;
						set.add(s);
						break;
					}
				}
			}
		}
        // the following throws an exception so the test fails

        // until you update the code
        String msg = "Complete this lab by adding your code and removing this statement.";
        //throw new UnsupportedOperationException(msg);
	}
}
