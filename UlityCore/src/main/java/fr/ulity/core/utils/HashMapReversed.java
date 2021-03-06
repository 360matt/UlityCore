package fr.ulity.core.utils;


import java.util.HashMap;
import java.util.Map;

public class HashMapReversed<K,V> extends HashMap<K, V> {

    Map<V,K> reverseMap = new HashMap<V,K>();

    @Override
    public V put(K key, V value) {
        reverseMap.put(value, key);
        return super.put(key, value);
    }

    public V remove(Object key) {
        V v = super.remove(key);
        reverseMap.remove(v, key);
        return v;
    }

    public K getKey(V value){
        return reverseMap.get(value);
    }

}