# sandbox

## Coding Interview

### Mindset
- Concentrate on the present moment, and then, move on to the next action.
- To be confident and concise during interviews, all I can do is prepare.
- The primary goal is to complete coding within the given time by cooperating with interviewers.
- As long as I meet the requirements, its quality and test coverage make sense.

### Lessons
- If, after 3 minutes, I cannot come up with any solution other than a brute force approach, ask an interviewer for some clues.
- Explain a solution in the following steps:
    - Write it down.
    - Show that it works with an example.
- If a test fails, keep track of the stack by writing comments besides the code even though I cannot use any IDEs.
- When declaring variable, I should choose carefully its data type in terms of how it's supposed to be used. If we store something which must be unique, we should choose a hash table because it takes only linear time complexity to lookup if it contains duplicates.


### Framework for Coding Interview
1. Determine
    - _Let me read the question._
    - _Let me confirm my understandings. If I'm wrong, please correct me. If an input is like this, the output is supposed to be like this, correct?_
    - _Let me think for a moment to come up with my solution._
1. Explain
    - _Before we just dive into the details, I'll go through exactly how my solution works._
1. Implement
    - _Let's get to implementation._

### Interview Demo
#### Case 1: Quick Sort
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

- I'll go thorough the time complexity.
    - In the best or expected cases, the height of the recursion tree will be $\log n$ (or $\log_2{n}$), because it implies how many steps it takes to decrease the number from $n$ to $1$ by halving.
    - Additionally, in any recursion level, we're going to iterate through almost $n$ elements in total.
    - Consequently, in the best or expected cases, the time complexity is $O(n\log n)$.

- Let's get to the worst case.
    - In the worst case, the height of the recursion tree will be $n$, because pivots that are chosen in any recursion level are the maximum or minimum in subarrays.
    - In other words, in the worst case, an input array is already sorted, or all the elements are identical.
    - Consequently, in the worst case, the time complexity is $O(n^2)$.

#### Case 2: Depth First Search with Adjacency Matrix
- DFS algorithm is composed of tree steps:
    - choose a node
    - choose an adjacent neighbor
        - if we've already visited the current node, backtrack to the previous node and choose another unvisited node
        - if the current node doesn't have any unvisited adjacent neighbor, backtrack to the last node with unvisited adjacent neighbor
        - otherwise, repeat those operations

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

### Vocabulary
#### Tech and Mathematical Concepts

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
    - `vertical`, `horizontal`, `diagonal` and `anti-diagonal`
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

#### Data Structures and Algorithms
- Hash Table
    - add the key-value pair with the key `key` and the value `value` to `hashTable`
    - lookup in our hash table if it contains the key `key`
- Loop
    - iterate through `array` until ...
    - iterate through `array` as long as ...
    - in each iteration, ...
    - move on to the next iteration
    
#### Testing
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

- Before we just dive into the details and start coding, it's important to know how the `Foo` algorithm works.
- I'll go through exactly how the `Foo` algorithm works.


---
## System Design Interview

### Overview of Architecture
![overview_architecture](https://github.com/ryoma-t/sandbox/assets/11306977/fbaf0f56-5d03-48f9-ac47-9a00933c1e7e)

### Functional Requirements
- **CRUD**: _Can data be deleted or updated?_

### Non-Functional Requirements
- Security
    - **Encription**: _To what extent should sensitive data be encrypted?_
    - **Web Security**: _What kind of security risks such as DDoS or other threats advocated by OWASP should we take precautions intensively?_
- Auditability
    - **Logging and monitoring**
- Capacity
    - **Business Volume**: _What is the traffic valume?_
    - **Scalability**: _How quickly can we anticipate the growth of the number of users?_
- Performance
    - **Acceptable response time**
- Availability
    - **Hours of operation**
    - **Locations of operation**
- Reliability
    - **Recovery Objectives**: _What are the desired recovery time objectives (RTO) and recovery point objectives (RPO) for different failure scenarios?_
    - **Backup Frequencies**: _How frequently should backups be performed?_
- Integrity
    - **Data Consistency**: _How strictly should we guarantee the data consistency?_
    - **Data Type**: _What kind of data are we going to store? The choice of data store will depend on the type of data we intend to store._

### Framework for System Design Interview
1. Propose the agenda to interviewers:
    - 10 min: speficy requrements
    - 10 min: design high-level blueprint
    - 15 min: design deep dive for each feature
    - 10 min: wrap up and discussion
1. Specify the requirements, and scope the features we're going to work on.
    - Understand the equestion.
    - Specify the requirements.
    - Scope the features.
1. Design high-level blueprint.
    - **Specify the API endpoints; HTTP method, path, parameters**.
    - As for each feature, draw a diagram with key components. Divide the feature into smaller sub features if needed.
    - Reach an agreement with the interviewr on the design.
1. Design deep dive.
    - **Specify the data model.**
    - Investigate the most important features or components, that we've identified and prioritized with the interviewer, in detail.
1. Wrap up and discussion.

---
## Supplementary Tech Contents
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
- Coding Interview:
    - https://www.youtube.com/watch?v=h8eyY7dIiN4&t=992s
    - https://www.youtube.com/watch?v=vZm0lHciFsQ&t=8s
    - https://www.youtube.com/watch?v=by93qH4ACxo
- System Design Interview:
    - https://bytebytego.com/courses/system-design-interview
    - https://dalbanger.wordpress.com/2014/01/08/a-basic-non-functional-requirements-checklist/
