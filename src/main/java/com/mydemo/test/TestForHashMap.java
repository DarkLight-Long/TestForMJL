package com.mydemo.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestForHashMap {

    /**
     * name: hash环；
     */
    @Test
    public void testForHashRing() {
        String[] serverIps = new String[] {"101.231.123.11","11.1.112.234","123.112.11.123","232.12.11.22"};
        SortedMap<Integer, String> hashServerMap = new TreeMap<>();
        for (String ip : serverIps) {
            hashServerMap.put(Math.abs(ip.hashCode()), ip);
        }

        String[] clientIps = new String[] {"101.23.234.33","11.1.112.2","123.112.11.12","23.121.11.22"};
        for (String ip : clientIps) {
            SortedMap<Integer, String> severMap = hashServerMap.tailMap(Math.abs(ip.hashCode()));
            if (severMap.isEmpty()) {
                Integer firstkey = hashServerMap.firstKey();
                System.out.println(firstkey);
            }
        }
    }

    @Test
    public void testHashMap() {
        Map<String, String> map = new HashMap();
        map.put("A", "A");
        // 同步的map， 某种程度上说相当于hashtable
        Map<String, String> strMap = Collections.synchronizedMap(new HashMap<>());
    }

}
