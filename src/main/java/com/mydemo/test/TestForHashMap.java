package com.mydemo.test;

import com.sun.media.sound.SF2LayerRegion;
import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

public class TestForHashMap {

    @Test
    public void test() {
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
            }
        }
    }

}
