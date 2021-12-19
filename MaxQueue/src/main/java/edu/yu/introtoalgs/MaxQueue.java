package edu.yu.introtoalgs;

/** Enhances the Queue enqueue() and dequeue() API with a O(1) max()
 * method and O(1) size().  The dequeue() method is amortized O(1), the enqueue
 * is amortized O(1).  The implementation is O(n) in space.
 *
 * @author Avraham Leff
 */

import java.util.NoSuchElementException;

public class MaxQueue {

  private class SOrgClDLL{//SelfOrganizingClearableDoublyLinkedList or what I'm calling the Pac-Man D.S.
    SOrgClDLL previous;
    SOrgClDLL next;
    LinkedNodes self;
    SOrgClDLL(SOrgClDLL previous, SOrgClDLL next, LinkedNodes self){
      this.previous = previous;
      this.next = next;
      this.self = self;
    }
  }
  private class LinkedNodes{
    int data;
    LinkedNodes next;

    LinkedNodes(int data, LinkedNodes next){
      this.data = data;
      this.next = next;
    }

  }

  private int size;
  private LinkedNodes first;
  private LinkedNodes last;

  private SOrgClDLL maximum;
  private SOrgClDLL minimum;

  /** No-argument constructor: students may not add any other constructor for
   * this class
   */
  public MaxQueue() {
    // students may insert whatever code they please, but the code may not
    // throw an exception
    this.size = 0;
    this.first = null;
    this.last = null;
    this.maximum = null;
    this.minimum = null;
  }

  /** Insert the element with FIFO semantics
   *
   * @param x the element to be inserted.
   */
  public void enqueue(int x) {
    LinkedNodes newNode;//construct the node to insert in queue
    SOrgClDLL dllNewNode;//construct element to place in DLL
    if( this.last == null ){//when queue is empty, so first entry
      newNode = new LinkedNodes(x, null);
      this.first = newNode;
      this.last = newNode;
      dllNewNode = new SOrgClDLL(null, null, newNode);
      this.maximum = dllNewNode;
      this.minimum = dllNewNode;
    }else{//this.last is not null and that means we have a last element, this isn't the first entry
      newNode = new LinkedNodes(x, null);
      this.last.next = newNode;
      this.last = newNode;
      dllNewNode = new SOrgClDLL(null, null, newNode);
      //since this queue has atleast 1 value we do our maximum dll logic now
      if( dllNewNode.self.data >= this.maximum.self.data ){
        this.maximum = dllNewNode;
        this.minimum = dllNewNode;//since this is destroying the previous max this is now the new max and min
      }else if( dllNewNode.self.data < this.minimum.self.data ){//newNode is smaller than maximum so check against minimum
        //if smaller than minimum have the dll link relation between the two and then make minimum be newNode
        this.minimum.next = dllNewNode;
        dllNewNode.previous = this.minimum;
        this.minimum = dllNewNode;
      }else if( dllNewNode.self.data >= this.minimum.self.data ){//if the new node is bigger than the minimum have it eat the old min and become new min
        dllNewNode.previous = this.minimum.previous;//dll's previous is also min's previous
        this.minimum.previous.next = dllNewNode;//min's previous's next changes from min to dll
        //now dll's prev is min's prev and min's prev's next is dll so the only refrence to min is the instance variable; lets change that
        this.minimum = dllNewNode;
        //continue this pacman eating until dll is smaller then a previous
        while(dllNewNode.self.data >= dllNewNode.previous.self.data){//while the new Node is bigger than the previous or equal consume the previous
          dllNewNode.previous = dllNewNode.previous.previous;//change newNode's prev to prev's prev
          dllNewNode.previous.next = dllNewNode;//newNode's new prev's new next is now newNode
          if( dllNewNode.previous == this.maximum ){//if the prev happens to be maximum we can stop in addition to stopping when whle header says since we already check agaisnt max
            break;
          }
        }
      }
    }
    this.size++;
  }

  /** Dequeue an element with FIFO semantics.
   *
   * @return the element that satisfies the FIFO semantics if the queue is not
   * empty.
   * @throws NoSuchElementException if the queue is empty
   */
  public int dequeue() {
    if(this.size == 0){
      throw new NoSuchElementException();
    }
    LinkedNodes dq = this.first;//get the first element, the one to be dequeued
    this.first = this.first.next;//derefrence it by having first point to what is after what we are dequeuing. if this was the only element then next is null
    if(this.size == 1){//if the size is one that means this was the only elemnt in the queue so the other pointers must point to null
      this.last = null;
      this.maximum = null;
      this.minimum = null;
    }else if( dq.data == this.maximum.self.data && dq == this.maximum.self ){//but what if i queued 10 then 10 the second 10 is maximum not the first. will need to have DLL objects store a refrence to LL objects and compare the objects
      //if we are dequeuing the maximum change maximum to next
      this.maximum = this.maximum.next;
      //then change new maximum's prev to null
      this.maximum.previous = null;
    }
    this.size--;
    int returnedInt = dq.data;
    dq = null;
    return returnedInt;
  }

  /** Returns the number of elements in the queue
   *
   * @return number of elements in the queue
   */
  public int size() {
    return this.size;
  }


  /** Returns the element with the maximum value
   * 
   * @return the element with the maximum value
   * @throws NoSuchElementException if the queue is empty
   */
  public int max() {
    if(this.size == 0){
      throw new NoSuchElementException();
    }
    return this.maximum.self.data;
  }

  @Override
  public String toString() {
    String s = "";
    if(this.size == 0){
      return "Empty";
    }
    LinkedNodes current = this.first;
    s += "Queue: ";
    while(current != null){
      s += current.data + ", ";
      if( current.next == null ){
        break;
      }else{
        current = current.next;
      }
    }
    s += "\n";
    SOrgClDLL mCurrent = this.maximum;
    s += "Maxes: ";
    while(mCurrent != null){
      s += mCurrent.self.data + ", ";
      if( mCurrent.next == null ){
        break;
      }else{
        mCurrent = mCurrent.next;
      }
    }
    s += "\n";
    return s;
  }
  
} // MaxQueue

