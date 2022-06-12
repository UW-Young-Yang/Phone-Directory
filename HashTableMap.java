// --== CS400 File Header Information ==--
// Name: Young Yang
// Email: xyang532@wisc.edu
// Team: IC
// Role: Data Wranglers
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private LinkedList<KeyType>[] keyListTable;
  private LinkedList<ValueType>[] valueListTable;
  private int size = 0;
  private int listTableSize = 0;
  private int on = 1;
  private int capacity;

  @SuppressWarnings("unchecked")
	public HashTableMap(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("The capacity of HashTableMap must bigger than ZERO!");
    }
    this.capacity = capacity;
    keyListTable = new LinkedList[capacity];
    valueListTable = new LinkedList[capacity];
  }

  @SuppressWarnings("unchecked")
  public HashTableMap() { // with default capacity = 10
    this.capacity = 10;
    keyListTable = new LinkedList[this.capacity];
    valueListTable = new LinkedList[this.capacity];
  }

  @SuppressWarnings("unchecked")
  private void helper() {
    LinkedList<KeyType>[] copyOfKeyTable = (LinkedList<KeyType>[]) keyListTable.clone();
    LinkedList<ValueType>[] copyOfValueTable = (LinkedList<ValueType>[]) valueListTable.clone();

    keyListTable = new LinkedList[this.capacity * 2]; // null
    valueListTable = new LinkedList[this.capacity * 2]; // null
    this.capacity *= 2;   // this.capacity -> double size

    for (int i = 0; i < this.capacity/2; i++) {   // this.capacity -> original size
      if (copyOfValueTable[i] != null) {
        for (int j = 0; j < copyOfKeyTable[i].size(); j++) {
          put(copyOfKeyTable[i].get(j), copyOfValueTable[i].get(j));
        }
      }
    }
    
    this.on = 1;
  }

  @Override
  public boolean put(KeyType key, ValueType value) {
    if (containsKey(key)) return false;

    int index = Math.abs(key.hashCode()) % this.capacity;

    // record list table size
    if (keyListTable[index] == null) {
      keyListTable[index] = new LinkedList<KeyType>();
      valueListTable[index] = new LinkedList<ValueType>();
      this.listTableSize++;
    }

    // put operation
    keyListTable[index].add(key);
    valueListTable[index].add(value);
    this.size++;

    // load operation
    if (this.size >= this.capacity * 0.8 && this.on == 1) {
      this.on = 0;
      helper();
    }

    return true;
  }

  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int index = Math.abs(key.hashCode()) % this.capacity;

    if (!containsKey(key)) throw new NoSuchElementException();

    return valueListTable[index].get(keyListTable[index].indexOf(key));
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean containsKey(KeyType key) {
    if(this.size == 0) return false;

    for (LinkedList<KeyType> keyList : keyListTable) {
      if (keyList != null) {
        if (keyList.indexOf(key) != -1) return true;
      }
    }
		return false;
  }

  @Override
  public ValueType remove(KeyType key) {
    if (!containsKey(key)) return null;

    int index = Math.abs(key.hashCode()) % this.capacity;    // the index of linked list in the array
    int keyIndex = keyListTable[index].indexOf(key);  // the index of key in the linked list
    ValueType removedValue = get(key);

    keyListTable[index].remove(keyIndex);
    valueListTable[index].remove(keyIndex);

		return removedValue;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void clear() {
    keyListTable = new LinkedList[this.capacity];
    valueListTable = new LinkedList[this.capacity];
    this.size = 0;
    this.listTableSize = 0;
  }

}
