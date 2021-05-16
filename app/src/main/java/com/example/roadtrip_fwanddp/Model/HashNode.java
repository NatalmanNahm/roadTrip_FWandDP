package com.example.roadtrip_fwanddp.Model;

// A node of chains
public class HashNode<K, V> {
    public K key;
    public V value;
    public final int hashCode;

    // Reference to next node
    public HashNode<K, V> next;

    // Constructor
    public HashNode(K key, V value, int hashCode)
    {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
