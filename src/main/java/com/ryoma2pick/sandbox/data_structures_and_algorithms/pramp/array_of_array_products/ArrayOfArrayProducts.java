package org.pramp.array_of_array_products;

public class ArrayOfArrayProducts {

    static int[] arrayOfArrayProducts(int[] arr) {
        if (arr.length <= 1) {
            return new int[]{};
        }
        int[] productsToLeft = productsToLeft(arr);
        int product = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            product *= arr[i + 1];
            productsToLeft[i] *= product;
        }
        return productsToLeft;
    }

    static int[] productsToLeft(int[] arr) {
        int[] cumulativeProducts = new int[arr.length];
        cumulativeProducts[0] = 1;
        for (int i = 1; i < cumulativeProducts.length; i++) {
            int product = cumulativeProducts[i - 1] * arr[i - 1];
            cumulativeProducts[i] = product;
        }
        return cumulativeProducts;
    }

}
