# sandbox

## English for Tech Interviews

### Notes (for Me)
#### Mindset
- Concentrate on the present moment, and then, move on to the next action.
- To be confident and concise during interviews, all I can do is prepare.
- The primary goal is to complete coding within the given time by cooperating with interviewers.
- As long as I meet the requirements, its quality and test coverage make sense.

#### Time Table for Coding Interview
- Try to comprehend the question by asking the interview how the function is supposed to work in several circumstances.
- Set a 3-minute timer, and start trying to come up with my solution. If, after 3 minutes, I cannot come up with any solution other than a brute force approach, ask the interviewer for some clues.
- If a test fails, keep track of the stack by writing comments besides the code even though I cannot use any IDEs.

### English Phrases
#### Colloquial Expressions
- what we're gonna do is `to do something`
- the $n$-th step is `to do something`
- we're going to `do something`
- we need to `do something`
- Let's get to `something` (or `doing something`)

- Alright, now ...
- Sorry, that's a mistake. It should be ...

- Let me think for a moment.
- Let me confirm my understandings.
- If I'm wrong, please correct me.
- If an input array is empty, what is it supposed to return?

#### Introduction
- Before we just dive into the details and start coding, it's important to know how the `Foo` algorithm works.
- I'll go through exactly how the `Foo` algorithm works.

#### Example: Quicksort
##### Algorithm
- The whole `quicksort` algorithm is composed of three steps.
    1. Choose one element in your array as a pivot.
    1. (`partitioning`) Move all elements which are less than the pivot to the left of it, all elements which are greater than the pivot to the right of it.
    1. Recursively apply those operations to both the left subarray and the right subarray.

- Let me show you a simple example.
    - An input array is $[1, 4, 6, 3, 7, 8, 5]$.
    1. Choose the last element as the pivot. In this case, it will be $5$.
    1. Move all elements which are less than $5$ to the left of $5$, and all elements which are greater than $5$ to the right of 5. In this case, the outcome is $[1, 4, 3, (5), 7, 8, 6]$. At this point, the pivot is actually in its final spot in the array.
    1. Recursively apply those operations to both subarrays. In this case, those subarrays are $[1,4,3]$ and $[7,8,6]$.
    1. For the left subarray $[1,4,3]$, after those operations, the outcome is $[1,(3),4]$. Divide it into two subarryas at the pivot, and apply those operations to both subarrays. In this case, since each subarray is just one element, it's already sorted.

- We need to know how to implement `partitioning`.
    - Iterate though an array with two indices, `i` and `j`, so that all elements to the left of `i` are less than the pivot, and all elements to the right of `j` are greater than the pivot.
    1. Start the index `i` at the leftmost position, and start the `j` at the rightmost position.
    1. Start going through an array with the index `i` toward the right, until we find an element which is greater than the pivot.
    1. If we find an element which is greater than the pivot, stop there, leave the index `i` at that point, and move on to the index `j`. Start going through an array with the index `j` backward, until we find an element which is less than the pivot. If we find an element which is less than the pivot, stop there.
    1. At this point, the index `i` points to an element which is greater than the pivot, and the index `j` points to an element which is less than the pivot. Swap the elements at those indices.
    1. Move on to the index `i`, and repeat that process, until the index `i` and `j` point to exactly the same index.
    1. Once it happens, stop the process of moving the indices, and swap the pivot with the element the index `i` points to.

- Let me show you a simple example. An input array is $[1, 4, 6, 3, 7, 8, 5]$.
    1. Start `i` at the leftmost position, and start `j` at the rightmost position.
        ```
        [1, 4, 6, 3, 7, 8, 5]
         i                 j
        ```
    1. In this case, we will stop the index `i` pointing to $6$, and move on to the index `j`.
        ```
        [1, 4, 6, 3, 7, 8, 5]
               i           j
        ```
    1. In this case, we will stop the index `j` pointing to $3$.
        ```
        [1, 4, 6, 3, 7, 8, 5]
               i  j
        ```
    1. In this case, `i` points to $6$, which is less thant the pivot, $5$, and `j` points to $3$, which is greater than the pivot, $5$. Swap the numbers at those two indices. 
        ```
        [1, 4, 3, 6, 7, 8, 5]
               i  j
        ```
    1. Move on to `i`, and repeat the process, until `i` and `j` point to exactly the same index.
        ```
        [1, 4, 3, 6, 7, 8, 5]
                 i,j
        ```
    1. In this case, swap $5$ with $6$.
        ```
        [1, 4, 3, 6, 7, 8, 5]
                 i,j
        ```

##### Code
- Let's get to coding `partition` method.
    - Create a test method to make sure that `partition` algorithm works properly. 
        - At the *setup* step, declare an input that we're going to partition, and an expected outcome. 
        - At the *execute* step, call the method.
        - At the *verify* step, make sure that the outcome equals the expected one.

        ```
        @Test
        void partition() {
            // setup
            int[] list = new int[]{1, 4, 6, 3, 7, 8, 5};
            int[] partitionedList = new int[]{1, 4, 3, 5, 7, 8, 6};
            // execute
            QuickSort.partition(list);
            // verify
            assertTrue(Arrays.equals(partitionedList, list));
        }
        ```

    - Implement `partition` method. 
        - `partition` takes in one parameter.
        - Declare two indices, `i` and `j`. Start `i` at the leftmost position, and start `j` at the rightmost position.
        - Assign `pivot` the last element's value. (Assign the last element's value to `pivot`.)
        - Create a loop that will move `i` toward the right. 
        - Iterate through `list` with `i` until we find an element which is greater than the pivot. If `list` at `i` is greater than `pivot`, stop there, leave `i` at that point, and move on to `j`.  
        - Create a loop that will move `j` backward.
        - If `list` at `j` is less than `pivot`, stop moving `i` and `j`, and swap elements the two indices point to.

        ```
        static void partition(int[] list) {
            int lastIndex = list.length - 1;
            int pivot = list[lastIndex ];

            int i = 0;
            int j = lastIndex;

            while (i < j) {
                if (list[i] < pivot) {
                    i++;
                    continue;
                }
                while (i < j) {
                    if (list[j] >= pivot) {
                        j--;
                        continue;
                    }
                    swap(list, i, j);
                    i++;
                    break;
                }
            }
            swap(list, i, lastIndex);
        }
        ```

    - Implement `swap` method, and its test method.
        - Declare a temporary variable `temp` to hold the value of `list` at `i`.
        - Swap the values two indices point to.

        ```
        static void swap(int[] list, int i, int j) {
            int temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
        ```

- Let's get to changing the signature of `partition` so that we can recursively apply `partition` algorithm to subarrays divided by the pivot.
    - These parameters indicate the range that we're going to sort.
    - Make it return the index of the pivot.

    ```
    static int partition(int[] list, int start, int end) {
        int pivot = list[end];

        int i = start;
        int j = end;

        while (i < j) {
            if (list[i] < pivot) {
                i++;
                continue;
            }
            while (i < j) {
                if (list[j] >= pivot) {
                    j--;
                    continue;
                }
                swap(list, i, j);
                i++;
                break;
            }
        }
        swap(list, i, end);
        return i;
    }
    ```

- Let's get to creating `sort` method.
    - As long as `start` is less than `end`, partition and apply those operations to both the left subarray and the right subarray recursively.
    - Pass in $0$ and the last index.

    ```
    public static void sort(int[] list) {
        sort(list, 0, list.length - 1);
    }

    static void sort(int[] list, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(list, 0, end);
            sort(list, start, partitionIndex - 1);
            sort(list, partitionIndex + 1, end);
        }
    }
    ```

##### Time Complexity
- I'll go thorough the time complexity.
    - In the best or expected cases, the height of the recursion tree will be $\log n$ (or $\log_2{n}$), because it implies how many steps it takes to decrease the number from $n$ to $1$ by halving.
    - Additionally, in any recursion level, we're going to iterate through almost $n$ elements in total.
    - Consequently, in the best or expected cases, the time complexity is $O(n\log n)$.

- Let's get to the worst case.
    - In the worst case, the height of the recursion tree will be $n$, because pivots that are chosen in any recursion level are the maximum or minimum in subarrays.
    - In other words, in the worst case, an input array is already sorted, or all the elements are identical.
    - Consequently, in the worst case, the time complexity is $O(n^2)$.

#### Example: Depth First Search with Adjacency Matrix
##### Algorithm
- DFS algorithm is composed of tree steps:
    - choose a node
    - choose an adjacent neighbor
        - if we've already visited the current node, backtrack to the previous node and choose another unvisited node
        - if the current node doesn't have any unvisited adjacent neighbor, backtrack to the last node with unvisited adjacent neighbor
        - otherwise, repeat those operations

##### Code
- We're going to use a graph that utilizes an adjacency matrix.
- We're going to create `DepthFirstSearch` method.
    - We want it to take in one parameter, root node.
    - To keep track of the nodes that we've already visited, what we're gonna do is create an array of booleans.
    - We will call a helper function. Let's call it `dFSHelper`, and we want it to take in the parameters `src` and `visited`.
    ```
    List<Node> nodes;
    int[][] matrix;
    
    void DepthFirstSearch(int src){
        boolean[] visited = new boolean[matrix.length];
        dfsHelper(src, visited);
    }
    ```

- In this implementation, we're going to use recursion.
    - We're going to check if the current node is visited or not.
    - If we've already visited this node, we're going to return.
    ```
    void dFSHelper(int src, boolean[] visited){
        if(visited[src]) return;
    }
    ```
    - Otherwise, we will mark this node as visited.
    - Next, we need to find any adjacent neighbors. Since we're using an adjacency matrix, we need to iterate over its columns.
    - In each iteration, check if an element is $1$, which means that's an adjacent neighbor.
    - Then, we will invoke `dFSHelper` again recursively.
    ```
    void dFSHelper(int src, boolean[] visited){
        if(visited[src]) return;

        visited[src] = true;
        for(int dst=0; dst<matrix[src].length; i++){
            if(matrix[src][dst] == 1) dFSHelper(dst, visited);
        }
    }
    ```

#### Data Structures and Algorithm
- Hash Table
    - add the key-value pair with the key `key` and the value `value` to `hashTable`
    - lookup in our hash table if it contains the key `key`

- Loop
    - iterate through `array` until ...
    - iterate through `array` as long as ...
    - in each iteration, ...
    - move on to the next iteration

- Graph, Tree
    - `TBD`

#### Test
- Phrases
    - a test passes (fails)
    - write a test scenario to cover the edge case
    - debug to identify and fix defects
    - want it to take `x` and `y`
    - want it to return `z`

- Sentences
    - We're verifying that `foo` should return `bar`. So, let's call our test method `fooShouldReturnBar`.
    - Because the code isn't doing the right thing, our test should fail.
    - Let's go ahead and run (rerun) our test, and make sure it passes.
    - If we pass in `foo`, it should return `bar`.

#### Tips
- When declaring variable, I should choose carefully its data type in terms of how it's supposed to be used. If we store something which must be unique, we should choose a hash table because it takes only linear time complexity to lookup if it contains duplicates.

#### Appendix
- Arithmetic
    - $x+y$, The sum of $x$ and $y$.
    - $xy$, The product of $x$ and $y$.
    - $3x$, Three times $x$.
    - $3(x+y)$, Three times the sum of $x$ and $y$.
    - $x+3$, $x$ plus three.
    - $x-3$, $x$ minus three, $x$ subtract three.
    - $3x^2$, Three times $x$ squared.
    - $\frac{1}{3}x$, One-third of $x$, A third of $x$. 
    - $2^n$, Two to the power of $n$.
    - $\log_2 n$, Log base 2 of $n$.

- Symbols
    - `()`, parentheses (singular: parenthesis)
    - `[]`, brackets
    - `{}`, braces

- Mathematical concepts
    - `odd`, `even`
    - `ascendent`, `descendent`
    - `ancestors`, `descendants`
    - `integer division`, `remainder`: when you divide five by two, the integer division is two, and the remainder is one.
    - `fractional part` or `decimal part`
    - `leading zeros`: leading zeros are those that occur in the leftmost digit positions of numbers.
    - `carry`: a carry is a digit that is transferred from one column of digits to another column of more significant digits.
    - `ones digit`, `tens digit`, `hundreds digit`, etc.
    - `palindromic`: a sequence of character reads the same forwards and backwards.
    - `truncate`: to remove a fractional (decimal) part.

- Other related concepts
    - brute force
    - infinite loop
    - vertex, vertices (plural)

---
## Tech Matters
### Graph Traversal
Graph traversal is an algorighm to **visit every node** of a graph.
Depth First Search, one of traversal algorithms follows the following steps:

1. Choose a node.
1. Choose its adjacent node.
    1. If we've already visited it, backtrack to the previous node and choose another adjacent neighbor.
    1. If it doesn't have any unvisited adjacent neighbor, backtrack to the last node with unvisited nodes.
    1. Otherwise, repeat those operations.

Concrete implementations vary with respect to its graph representation and data structures we're going to adopt.
| DFS or BFS | Graph Representation | Data Structure | Code Sample | Commnet |
|------------|------------|------------|------------|------------|
| DFS | Adjacency Matrix | Recursion | [implementation](https://github.com/ryoma-t/sandbox/blob/main/src/main/java/com/ryoma2pick/sandbox/dsa/graph/depth_first_search/DFSAdjacencyMatrix.java#L22) |  |
| DFS | Adjacency Matrix | Iteration with Stack | [implementation](https://github.com/ryoma-t/sandbox/blob/main/src/main/java/com/ryoma2pick/sandbox/dsa/graph/depth_first_search/DFSAdjacencyMatrix.java#L59) |  |
| DFS | Adjacency List | Recursion | [implementation](https://github.com/ryoma-t/sandbox/blob/main/src/main/java/com/ryoma2pick/sandbox/dsa/graph/depth_first_search/DFSAdjacencyList.java#L19) |  |
| DFS | Adjacency List | Iteration with Stack | [implementation](https://github.com/ryoma-t/sandbox/blob/main/src/main/java/com/ryoma2pick/sandbox/dsa/graph/depth_first_search/DFSAdjacencyList.java#L35) |  |
| DFS | Tree Node | Recursion | [implementation](https://github.com/ryoma-t/sandbox/blob/main/src/main/java/com/ryoma2pick/sandbox/dsa/graph/depth_first_search/DFSTree.java#L9) | This method can be used only for connected and acyclic graphs i.e. trees. |
| DFS | Tree Node | Iteration with Stack | [implementation](https://github.com/ryoma-t/sandbox/blob/main/src/main/java/com/ryoma2pick/sandbox/dsa/graph/depth_first_search/DFSTree.java#L21) | This method can be used only for connected and acyclic graphs i.e. trees. |
| BFS | Adjacency Matrix | Iteration with Queue | `TBD` |  |
| BFS | Adjacency List | Iteration with Queue | `TBD` |  |
| BFS | Tree Node | Iteration with Queue | `TBD` | This method can be used only for connected and acyclic graphs i.e. trees. |

---
## Reference
- The videos from which I've learned English phrases:
    - https://www.youtube.com/watch?v=h8eyY7dIiN4&t=992s
    - https://www.youtube.com/watch?v=vZm0lHciFsQ&t=8s
    - https://www.youtube.com/watch?v=by93qH4ACxo
