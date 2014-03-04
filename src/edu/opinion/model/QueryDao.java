/**
 * 
 */
package edu.opinion.model;

import java.util.List;


/**
 * @author hsr
 *
 */
public class QueryDao {

	public static int getHashcode(List<String> list){
		String s_needToCov = "";
		for (Object obj : list) {
			if (obj != null) {
				s_needToCov += obj.toString();
			}
		}
		int covHasCode = s_needToCov.hashCode();
		return covHasCode;
	}
}
