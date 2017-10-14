/*
* Author: Joshua Getega, jgetega@gmail.com.
* Methods to be added to the ArrayIntList Class at https://practiceit.cs.washington.edu/problems/bjp3/chapter15/ArrayIntList.java.
* Add to ArrayIntList to compile.
* Methods are solutions to coding exercises in Ch 15 of "Building Java Programs" by Stuart Reges and Marty Stepp
*/


/*
* Takes an integer n as a parameter and increases a list of integers by a factor of n by 
* replacing each integer in the original list with n copies of that integer.
*/
public void stretch(int n) {
    if (n > 0) {
        ensureCapacity(this.size * n);
        int finalSize = this.size * n;

        for (int i = 0; i < finalSize; i+=n) {
            for (int j = 1; j < n; j++) {
                this.add(i + j, elementData[i]);
            }
        }
    } else {
        this.size = 0;
    }
    
}

/*
* Doubles the size of a list of integers by appending the mirror image of the original 
* sequence to the end of the list. The mirror image is the same sequence of values in 
* reverse order.
*/
public void mirror() {
    ensureCapacity(this.size * 2);
    for (int i = 1; i <= this.size; i++) {
        elementData[this.size + i - 1] = elementData[this.size - i];
    }
    this.size = this.size * 2;
}


/*
* Lists all inversions in a list of integers.
* An inversion is defined as a pair of numbers where the first appears before the second 
* in the list, but the first is greater than the second.
*/
public void printInversions() {
    for (int i = 0; i < this.size - 1; i++) {
        for (int j = i + 1; j < this.size; j++) {
            if (this.elementData[i] > this.elementData[j]) {
                System.out.println("(" + elementData[i] + ", " + elementData[j] + ")");
            }
        }
    }
}

/*
* Takes an integer value as a parameter and that removes all occurrences of the given value 
* from the list. Assumes that a method called remove exists that takes an index as a 
* parameter and removes the value at the given index.
*/
public void removeAll(int n) {
    for (int i = 0; i < this.size; i++) {
        if (this.elementData[i] == n) {
            this.remove(i);
            i--;
        }
    }
}

/*
* Takes an integer n as a parameter and removes the first n values from a list of integers
* Assumes that the parameter value passed is between 0 and the size of the list, inclusive.
*/
public void removeFront(int n) {
    int i = 0;
    while (i < this.size() - n) {
        this.elementData[i] = this.elementData[i + n];
        i++;
    }
    this.size = this.size() - n;
}

/*
* Returns the length of the longest sorted sequence within a list of integers.
* Returns 0 if list is empty.
*/
public int longestSortedSequence() {
    if (this.size() > 0) {
        int longestStreak = 1;
        int streak = 1;
        
        for (int i = 0; i < this.size() - 1; i++) {
            streak = 1;
            while (i < this.size() - 1 && this.elementData[i + 1] >= this.elementData[i]) {
                streak++;
                i++;
            }
            if (streak > longestStreak) {
                longestStreak = streak;
            }
        }
        return longestStreak;
    } else {
        return 0;
    }
}

/*
* Returns whether or not a list of integers is pairwise sorted (true if it is, false 
* otherwise). A list is considered pairwise sorted if each successive pair of numbers 
* is in sorted (non-decreasing) order.
*/
public Boolean isPairwiseSorted() {
    
    for (int i = 0; i < this.size() - 1; i+=2) {
        if (this.elementData[i] > this.elementData[i + 1]) {
            return false;
        }
    }
    
    return true;
}

/*
* Returns a new ArrayIntList that contains a running total of the original list.
* If the original list is empty, the result is an empty list. 
* The new list has the same capacity as the original.
*/
public ArrayIntList runningTotal() {
    ArrayIntList runningTotalArray = new ArrayIntList(DEFAULT_CAPACITY);
    
    if (size > 0) {
        runningTotalArray.add(elementData[0]);

        for (int i = 1; i < this.size(); i++) {
            runningTotalArray.add(elementData[i] + runningTotalArray.elementData[i - 1]);
        }
    }
    
    return runningTotalArray;
}