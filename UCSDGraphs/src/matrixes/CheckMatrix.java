/*
 * Copyright (C) 2015 Johnn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package matrixes;

/**
 *
 * @author Johnn
 */
public abstract class CheckMatrix {
    
    /**
     * @param firstArr is an array which has to be check for rectangularity.
     * @return boolean
     */
    public boolean checkArray(int[][] firstArr) {
       boolean checking = false;
       for (int i=1;i<firstArr.length;i++) {
           checking = firstArr[i].length == firstArr[i-1].length;
        
        }
       return checking;    
    }
    /**
     * @param FirstA is a first array which dimension has to be check and compare 
     * with other array. 
     * @param SecondA is a second array which has to be check and compare 
     * with other array.
     * @return boolean
     */
    public boolean checkArraysDimension(int FirstA[][], int SecondA[][])
    {
       boolean checking = false;
       if (
       checkArray(FirstA) &&
       checkArray(SecondA) &&
       FirstA.length == SecondA.length &&
       FirstA[0].length == SecondA[0].length &&
       getArrayCountColumn(FirstA)!=-1 &&
       getArrayCountRow(FirstA)!=-1
       ) {
       checking = true;
       }
       return checking;
    }
    /**
     * @param is is an  array which has to be printed. 
     * 
     */   
    public void printMatrixArray(int[][] is) {
    if (checkArray(is)) {
            for (int [] PrintedArr1 : is) {
                for (int k = 0; k<is[0].length; k++) {
                    System.out.print(PrintedArr1[k] + "\t");
                }
                System.out.println();
            }
         }else {System.out.println("Matrix is not rectangular");} 
    }
    /**
     *
     * @param firstArr is an array which rows has to be counted
     * @return amount of rows
     */
    public int getArrayCountRow(int[][] firstArr) {
         int i = 0;
         if (firstArr.length<=0) {
            i = -1;
        }else{i=firstArr.length;}
        return i;
    }
    /**
     *
     * @param secondArr is an array which columns has to be counted 
     * @return amount of columns
     */
     public int getArrayCountColumn(int[][] secondArr) {
         int i = 0;
         if (secondArr.length<=0 ||
                 secondArr[0].length<=0 ||
                 checkArray(secondArr)==false) {
            i = -1;}else{
             i=secondArr[0].length;
         }
        return i;
    }
    /**
     *
     * @param MatrixArrayNoNameFirst
     * @param MatrixArrayNoNameSecond
     * @return
     */
     public boolean checkArraysWidth(int MatrixArrayNoNameFirst [][],
             int MatrixArrayNoNameSecond [][]) {
         boolean checking = false;
         if (getArrayCountColumn(MatrixArrayNoNameFirst)==getArrayCountColumn(MatrixArrayNoNameSecond)){
             checking = true;
        }
         return checking;        
        }
}

