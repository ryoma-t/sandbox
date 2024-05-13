package com.ryoma2pick.sandbox.practice;

import java.util.Arrays;
import java.util.List;


public class FillShapesInMatrix {

    public static void main(String[] args) {
        /**
         *     0 1 2
         *     - - -
         * 0 | 0 0 1
         * 1 | 1 1 1
         */
        int[][] shapeA = new int[2][3];
        shapeA[0][2] = 1;
        shapeA[1][0] = 1;
        shapeA[1][1] = 1;
        shapeA[1][2] = 1;

        /**
         *     0 1 2
         *     - - -
         * 0 | 0 1 0
         * 1 | 1 1 1
         */
        int[][] shapeB = new int[2][3];
        shapeB[0][1] = 1;
        shapeB[1][0] = 1;
        shapeB[1][1] = 1;
        shapeB[1][2] = 1;
        /**
         *     0 1 2
         *     - - -
         * 0 | 0 0 1
         * 1 | 1 1 1
         * 2 | 0 1 1
         */
        int[][] shapeC = new int[3][3];
        shapeC[0][2] = 1;
        shapeC[1][0] = 1;
        shapeC[1][1] = 1;
        shapeC[1][2] = 1;
        shapeC[2][1] = 1;
        shapeC[2][2] = 1;

        FillShapesInMatrix fillShapesInMatrix = new FillShapesInMatrix();
        int m = 4;
        int n = 6;
        List<int[][]> shapes = Arrays.asList(shapeA, shapeB, shapeC);
        int[][] result = fillShapesInMatrix.sol(m, n, shapes);
        System.out.println(result);
        /**
         *     0 1 2 3 4 5
         *     - - - - - -
         * 0 | 0 1 0 0 0 1
         * 1 | 1 1 1 1 1 1
         * 2 | 0 0 1 0 1 1
         * 3 | 1 1 1 0 0 0
         */
    }

    /**
     * To implement a solution for placing multiple given shapes onto an m x n matrix without overlap
     *
     * ---
     * declare
     *      l: length of shapes
     *      current = 0
     *      matrix: m*n matrix
     *
     * invoke the function helper(matrix,current,l,shapes)
     *      if current == l, return matrix
     *
     *      shpae = shpaes[l]
     *      attempt to place shape in matrix
     *      iterate through i and j (i = 0, 1, ..., m, j = 0, 1, ..., n)
     *          if (i,j) == 1, move on to the next (i,j)
     *          if attempt fails, that is, if the function valid(i,j,shape,matrix) returns false, move on to the next (i,j)
     *          place shape onto matrix
     *          invoke helper(matrix,current+1,l,shapes)
     *          redo the placement and move on to the next iteration
     *
     * valid(i,j,shape,matrix)
     *      iterate through shpae with (k,l)
     *          if k < 0, k >= m, l < 0, or l >= n, return false
     *          if shape[k,l] == 0, continue
     *          if matrix[i+k,j+l] == 1, return false
     *      return true
     *
     */
    private int[][] sol(int m, int n, List<int[][]> shapes) {
        int l = shapes.size();
        int current = 0;
        int[][] matrix = new int[m][n];
        helper(matrix, current, l, shapes);
        return matrix;
    }

    private boolean helper(int[][] matrix, int current, int l, List<int[][]> shapes) {
        if(current == l) return true;
        int[][] shape = shapes.get(current);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 1) continue;
                if(!valid(i, j, shape, matrix)) continue;
                place(i, j, shape, matrix);
                if(helper(matrix, current + 1, l, shapes)) return true;
                redo(i, j, shape, matrix);
            }
        }
        return false;
    }

    private void redo(int i, int j, int[][] shape, int[][] matrix) {
        for(int k = 0; k < shape.length; k++){
            for(int l = 0; l < shape[0].length; l++){
                if(shape[k][l] == 0) continue;
                matrix[i + k][j + l] = 0;
            }
        }
    }

    private void place(int i, int j, int[][] shape, int[][] matrix) {
        for(int k = 0; k < shape.length; k++){
            for(int l = 0; l < shape[0].length; l++){
                if(shape[k][l] == 0) continue;
                matrix[i + k][j + l] = 1;
            }
        }
    }

    private boolean valid(int i, int j, int[][] shape, int[][] matrix) {
        for(int k = 0; k < shape.length; k++){
            for(int l = 0; l < shape[0].length; l++){
                if(i + k >= matrix.length || j + l >= matrix[0].length) return false;
                if(shape[k][l] == 0) continue;
                if(matrix[i + k][j + l] == 1) return false;
            }
        }
        return true;
    }

}
