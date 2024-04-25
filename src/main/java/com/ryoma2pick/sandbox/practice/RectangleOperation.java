package com.ryoma2pick.sandbox.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RectangleOperation {

    public static void main(String[] args) {
        RectangleOperation sol = new RectangleOperation();
        int h = 3;
        int w = 5;
        String[] queries = new String[]{"v 1 2", "x 2 2", "v 1 2", "> 2 1", "x 2 3", "> 2 1", "< 2 0"};
        // expected: [[2, 2], [-1, -1], [2, 3], [2, 4], [-1, -1]]
        sol.solution(h, w, queries).stream().forEach(e -> {
            System.out.println(e[0] + ", " + e[1]);
        });
    }

    public List<int[]> solution(int h, int w, String[] queries) {
        char[][] grid = new char[h][w];
        Stream.of(grid).forEach(e -> Arrays.fill(e, 'w'));
        List<int[]> list = new ArrayList<>();
        for (String query : queries) {

            String[] arr = query.split(" ");
            char operation = arr[0].charAt(0);
            int i = Integer.valueOf(arr[1]);
            int j = Integer.valueOf(arr[2]);

            if (operation == 'x') {
                if (i < 0 || j < 0 || i >= h || j >= w) continue;
                grid[i][j] = 'b';
                continue;
            }
            if (operation == '>') {
                helperRight(i, j + 1, h, w, grid, list);
                continue;
            }
            if (operation == '<') {
                helperLeft(i, j - 1, h, w, grid, list);
                continue;
            }
            if (operation == 'v') {
                helperDown(i + 1, j, h, w, grid, list);
                continue;
            }
            if (operation == '^') {
                helperUp(i - 1, j, h, w, grid, list);
                continue;
            }
        }
        return list;
    }

    private void helperRight(int i, int j, int h, int w, char[][] grid, List<int[]> list) {
        if (i < 0 || j < 0 || i >= h || j >= w) {
            list.add(new int[]{-1, -1});
            return;
        }
        if (grid[i][j] == 'w') {
            list.add(new int[]{i, j});
            return;
        }
        helperRight(i, j + 1, h, w, grid, list);
    }

    private void helperLeft(int i, int j, int h, int w, char[][] grid, List<int[]> list) {
        if (i < 0 || j < 0 || i >= h || j >= w) {
            list.add(new int[]{-1, -1});
            return;
        }
        if (grid[i][j] == 'w') {
            list.add(new int[]{i, j});
            return;
        }
        helperLeft(i, j - 1, h, w, grid, list);
    }

    private void helperDown(int i, int j, int h, int w, char[][] grid, List<int[]> list) {
        if (i < 0 || j < 0 || i >= h || j >= w) {
            list.add(new int[]{-1, -1});
            return;
        }
        if (grid[i][j] == 'w') {
            list.add(new int[]{i, j});
            return;
        }
        helperDown(i + 1, j, h, w, grid, list);
    }

    private void helperUp(int i, int j, int h, int w, char[][] grid, List<int[]> list) {
        if (i < 0 || j < 0 || i >= h || j >= w) {
            list.add(new int[]{-1, -1});
            return;
        }
        if (grid[i][j] == 'w') {
            list.add(new int[]{i, j});
            return;
        }
        helperUp(i - 1, j, h, w, grid, list);
    }


/*
Consider a rectangular h × w board with all cells initially white. You are to process several queries of the following types:

"x a b" - color the white cell (a, b) (0-based coordinates, the first one is a row index, and the second one is a column index) black;
"> a b" - find the leftmost white cell to the right of the white cell (a, b);
"< a b" - find the rightmost white cell to the left of the white cell (a, b);
"v a b" - the same, but the search should be done downwards;
"^ a b" - the same, but the search should be done upwards;
For each query, except the ones of the first type, find the answer.

Example

For h = 3, w = 5, and
queries = ["v 1 2", "x 2 2", "v 1 2", "> 2 1", "x 2 3", "> 2 1", "< 2 0"],
the output should be
solution(h, w, queries) = [[2, 2], [-1, -1], [2, 3], [2, 4], [-1, -1]].


["v 1 2", "x 2 2", "v 1 2", "> 2 1", "x 2 3", "> 2 1", "< 2 0"]
[[2, 2],          [-1, -1],  [2, 3],          [2, 4], [-1, -1]]

For each query except the ones of the first type,
store the answer's coordinates in the array.
If the desired cell doesn't exist, store [-1, -1] instead.
The answers should be stored in the same order as the queries.

    0 1 2 3 4
 0  w w w w w
 1  w w w w w
 2  w w b b w

---
declare
    grid: h*w matrix of char; initial values are 'w'
    list that we're gonna return
iterate through queries with q
    arr = q.split(" ")
    operation arr[0]
    i = arr[0]
    j = arr[1]

    if operation == 'x', assign grid(i,j) = 'b'

    if operation == '>'
        invoke the function helperRight(k=i+1,l=j,grid,list)
            if (k,l) is out of the range of grid,
                add (-1,-1) to list
                return
            if gird(k,l) == 'w'
                add (k,l) to list
            otherwise, invoke helperRight(k+1,l,grid,list)

    if operation == '<', invoke the function helperRight(...)
    if operation == 'v', invoke the function helperDown(...)
    if operation == '^', invoke the function helperUp(...)

---
time complexity: O(n*m)
    n: size of queries
    m: max(w,h)
space complexity: O(h*w)


 */

}
