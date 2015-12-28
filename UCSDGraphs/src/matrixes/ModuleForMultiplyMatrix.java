/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixes;

/**
 *
 * @author Evgenn
 */
public class ModuleForMultiplyMatrix extends CheckMatrix {

    /**
     *
     */
    public ModuleForMultiplyMatrix(){}
        
    /**
     *
     * @param FirstArr
     * @param SecondArr
     * @return
     */
    public int[][] MultiplyMatrix(int FirstArr[][],int SecondArr[][]) {
        int lengthArr = getArrayCountRow(FirstArr);
        int HeightArr = getArrayCountColumn(SecondArr);
        int MultArr[][]= new int [lengthArr][HeightArr];
        int countF = 0;
       int countS = 0;
        if(
        getArrayCountColumn(FirstArr) == getArrayCountRow(SecondArr) &&
        checkArray(FirstArr) &&
        checkArray(SecondArr)
        ){
            for (int k=0; k<getArrayCountRow(FirstArr);k++) {
                for (int n=0; n<getArrayCountColumn(FirstArr);n++) {
                    for (int r=0; r<getArrayCountColumn(FirstArr);r++) {
                        for (int i=0;i<getArrayCountColumn(FirstArr);i++) {
                            countF = FirstArr[k][r]*SecondArr[r][n];
                            countS += countF;
                            break;
                            }
                            MultArr[k][n] = countS;
                            }
                            countS = 0;
                            }
                            }
                            }
                            return MultArr;
                            }

}
