package com.hieuminh.utils.handlingTSP;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TSPUtils {
    public final static double max = 999999.0;
    private static double sum=0.0;
    public static Object[] algorithmTSP(double[][] matrix) {
        final long startTime = System.nanoTime();

        List<List<Integer>> list = new ArrayList<List<Integer>>();

        List<Integer> listRow = new ArrayList<Integer>();
        List<Integer> listCol = new ArrayList<Integer>();

        //double matrix[][] = {{max,7.0,5.0,20.0},{4.0,max,9.0,12.0},{15.0,6.0,max,12.0},{5.0,20.0,17.0,max}};
        //double matrix[][] = {{max,25.0,45.0,14.0,32.0,24.0},{9.0,max,16.0,2.0,34.0,23.0},{22.0,11.0,max,33.0,7.0,0.0},{23.0,14.0,27.0,max,20.0,21.0},{14.0,44.0,29.0,46.0,max,3.0},{25.0,3.0,4.0,7.0,8.0,max}};
        //double matrix[][] = {{max,10.0,15.0,20.0},{10.0,max,35.0,25.0},{15.0,35.0,max,30.0},{20.0,25.0,30.0,max}};
        //int matrix[][] = {{max,3,93,13,33,9},{4,max,77,42,21,16},{45,17,max,36,16,28},{39,90,80,max,56,7},{28,46,88,33,max,25},{3,88,16,48,92,max}};
        //int matrix[][] = {{max,	0,	90,	10,	30	,6},{0,	max,73	,38	,17,	12},{29,	1,	max,	20,	0	,12},{32,	83,	72,	max	,49	,0},{3	,21,	63,	8	,max	,0},{0	,85	,15	,43,	89,	max}};
        //double matrix[][] = {{max, 20.0,35.0, 42.0},{20.0, max ,34.0,30.0},{35.0, 34.0 ,max, 12.0},{42.0 ,30.0 ,12.0,max}};



        CreateListRowCol( listRow, listCol,matrix );

        //showMatrix(matrix);

        while(matrix.length>=2) {

            List<Double> minrow = getMinRow(matrix);
            matrix =setSubRowmatrix(matrix,minrow);
            List<Double> mincol = getMinCol(matrix);
            matrix = setSubColMatrix(matrix,mincol);
            //	 showMatrix(matrix);
            sum+= sumG(minrow, mincol);

            matrix = cutMatrix(matrix,list,listCol,listRow);

        }

        list = sortList(list);
       // System.out.println(sum);
        //showRoad(list);

       // final long duration = System.nanoTime() - startTime;
       // System.out.println((float)duration/1000000000);
        return new Object[]{list,sum};
    }

    private static List<Integer> listRoad(List<List<Integer>> list) {
        List<Integer> listz = new ArrayList<Integer>();

        for (int i = 0; i < list.size()-1; i++) {
            if (i%2==0) {
                listz.add(list.get(i).get(0));
            }else {
                listz.add(list.get(i).get(0));
                listz.add(list.get(i).get(1));
            }

        }
        return listz;
    }

    private static void CreateListRowCol(List<Integer> listRow, List<Integer> listCol,double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            listCol.add(i);
            listRow.add(i);
        }

    }

    private static void showRoad(List<List<Integer>> list) {
        for(List<Integer> l : list) {
            System.out.print((l.get(0)+1)+"-"+(l.get(1)+1)+" ");
        }
    }

    private static void swap(List<Integer> a, List<Integer>  b) {
        List<Integer> tmpList = new ArrayList<Integer>(a);
        a.clear();
        a.addAll(b);
        b.clear();
        b.addAll(tmpList);
    }
    private static List<List<Integer>> sortList(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).get(0) == 0 ) {
                swap(list.get(0),list.get(i));
            }

        }

        for (int i = 0; i < list.size()-1; i++) {
            for(int j=i+1;j<list.size();j++) {
                if(list.get(i).get(1)== list.get(j).get(0)) {
                    swap(list.get(i+1), list.get(j));
                }
            }
        }

        return list;
    }

    private static double[][] cutMatrix(double[][] matrix,List<List<Integer>> list,List<Integer> listCol,List<Integer> listRow) {
        //int[][] Newmatrix = new int[matrix.length-1][matrix.length-1];
        int ci = 0,cj = 0;
        double maxRowCol=0.0;
        //System.out.print("-------------------------------\n");
       // showMatrix(matrix);
        for(int i=0;i<matrix.length;i++) {

            for(int j=0;j<matrix.length;j++) {

                if(matrix[i][j]==0) {
                    double maxrow=999999.0;
                    double maxcol=999999.0;
                    for(int y=0;y<matrix[0].length;y++) {
                        if(matrix[i][y]<maxrow && y!=j) {
                            maxrow= matrix[i][y];
                        }
                    }
                    for(int y=0;y<matrix.length;y++) {
                        if(matrix[y][j]<maxcol && y!=i ) {
                            maxcol= matrix[y][j];
                        }
                    }
                    if((maxcol+maxrow)>maxRowCol) {
                        //System.out.print(maxRowCol+"\n");
                        maxRowCol =(maxcol+maxrow);
                        //System.out.print(maxRowCol+"\n");
                        //System.out.print(maxcol+"-"+maxrow+"\n");
                        ci=i;
                        cj=j;
                       // System.out.print("max:"+(maxcol+"+"+maxrow)+"="+maxRowCol+"\n");
                    }
                }
            }

        }


        List<Integer> l = new ArrayList<Integer>();
        if(matrix.length>2) {
            int x = 0,y = 0;
            for (int i = 0; i < listRow.size(); i++) {
                if(listRow.get(ci)==listRow.get(i)) {
                    x=i;
                }
            }
            for (int i = 0; i < listCol.size(); i++) {
                if(listCol.get(cj)==listCol.get(i)) {
                    y=i;
                }
            }
            if(x<=matrix.length && y<=matrix.length) {
                matrix[y][x]=max;
            }
        }

        System.out.print(ci+"-"+cj+"\n");
        if(matrix.length==2) {
            List<Integer> lx = new ArrayList<Integer>();
            lx = setListCutLeng2(list,matrix,listCol,listRow);

        }else if (matrix.length>2) {
            l = setListCut(ci,cj,listCol,listRow);
            list.add(l);

        }

        if (matrix.length>=2) {
            matrix = cutRowMatrix(matrix,ci,cj);
            matrix = cutColMatrix(matrix,ci,cj);
        }
        //showMatrix(matrix);



        return matrix;
    }
    private static List<Integer> setListCutLeng2(List<List<Integer>> list,double[][] matrix, List<Integer> listCol, List<Integer> listRow) {
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        if(matrix[0][0]==max) {
            l1.add(listRow.get(0));
            l1.add(listCol.get(1));
            list.add(l1);
            l2.add(listRow.get(1));
            l2.add(listCol.get(0));
            list.add(l2);
            sum+=matrix[0][1]+ matrix[1][0];
        }
        else if(matrix[1][1] ==max ) {
            l1.add(listRow.get(0));
            l1.add(listCol.get(1));
            list.add(l1);
            l2.add(listRow.get(1));
            l2.add(listCol.get(0));
            list.add(l2);
            sum+=matrix[0][1]+ matrix[1][0];
        }
        else if(matrix[1][0] ==max ) {
            l1.add(listRow.get(0));
            l1.add(listCol.get(0));
            list.add(l1);
            l2.add(listRow.get(1));
            l2.add(listCol.get(1));
            list.add(l2);
            sum+=matrix[0][0]+ matrix[1][1];
        }
        else if(matrix[0][1] ==max ) {
            l1.add(listRow.get(0));
            l1.add(listCol.get(0));
            list.add(l1);
            l2.add(listRow.get(1));
            l2.add(listCol.get(1));
            list.add(l2);
            sum+=matrix[0][0]+ matrix[1][1];
        }else if((matrix[0][0] < max && matrix[1][1] <max ) && (matrix[0][0] != 0 && matrix[1][1] !=0  )) {
            l1.add(listRow.get(0));
            l1.add(listCol.get(0));
            list.add(l1);
            l2.add(listRow.get(1));
            l2.add(listCol.get(1));
            list.add(l2);
            sum+=matrix[0][0]+ matrix[1][1];
        }else if((matrix[0][1] < max && matrix[1][0] <max ) && (matrix[0][1] != 0 && matrix[1][0] !=0  )) {
            l1.add(listRow.get(0));
            l1.add(listCol.get(1));
            list.add(l1);
            l2.add(listRow.get(1));
            l2.add(listCol.get(0));
            list.add(l2);
            sum+=matrix[0][0]+ matrix[1][1];
        }else {
            l1.add(listRow.get(0));
            l1.add(listCol.get(1));
            list.add(l1);
            l2.add(listRow.get(1));
            l2.add(listCol.get(0));
            list.add(l2);
            sum+=matrix[0][0]+ matrix[1][1];
        }
        return null;
    }


    private static List<Integer> setListCut(int ci, int cj, List<Integer> listCol, List<Integer> listRow) {
        List<Integer> l = new ArrayList<Integer>();

        l.add(listRow.get(ci));
        l.add(listCol.get(cj));

       // System.out.println((listRow.get(ci)+1)+"-"+(listCol.get(cj)+1));


        listRow.remove(ci);
        listCol.remove(cj);
        return l;
    }

    private static double[][] cutColMatrix(double[][] matrix, int ci, int cj) {
        double[][] newArray = new double[matrix.length][matrix[0].length-1];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0, k=0; j < matrix[0].length && k < matrix[0].length-1; j++)
            {
                if(j != cj)
                {
                    newArray[i][k++] = matrix[i][j];
                }
            }
        }
        return newArray;
    }

    private static double[][] cutRowMatrix(double[][] matrix, int ci, int cj) {
        double[][] newArray = new double[matrix.length-1][matrix[0].length];
        for(int i = 0,k=0; i < matrix.length && k<matrix.length-1; i++)
        {
            if(i != ci)
            {
                for(int j = 0; j < matrix[0].length; j++)
                {

                    newArray[k][j] = matrix[i][j];
                }
                k++;
            }
        }
        return newArray;
    }

    private static double sumG(List<Double> list1,List<Double> list2) {
        double sum = 0.0;
        for (int i = 0; i < list1.size(); i++) {
            sum+= list1.get(i)+ list2.get(i);
        }

        return sum;
    }

    private static double[][] setSubColMatrix(double[][] matrix, List<Double> mincols) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix.length;j++) {

                if(matrix[j][i] ==max) {
                    matrix[j][i]= matrix[j][i] -0 ;
                }else {
                    matrix[j][i]= matrix[j][i] - mincols.get(i);
                }

            }
        }
        return matrix;
    }

    private static void showMatrix(double [][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix.length;j++) {
                System.out.print(matrix[i][j] + "              ");

            }
            System.out.println();

        }
        System.out.println();
    }

    private static double[][] setSubRowmatrix(double[][] matrix,List<Double> minrows) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix.length;j++) {

                if(matrix[i][j]==max) {
                    matrix[i][j]= matrix[i][j] - 0;
                }else {
                    matrix[i][j]= matrix[i][j] - minrows.get(i);
                }


            }
        }
        return matrix;
    }

    private static List<Double> getMinCol(double[][] matrix) {
        List<Double> list = new ArrayList<Double>();
        for(int i=0;i<matrix[0].length;i++) {
            double min = 9999999.0;
            for(int j=0;j<matrix.length;j++) {
                if(matrix[j][i]!=0.0) {
                    if(matrix[j][i]<min) {
                        min=matrix[j][i];
                    }

                }else {
                    min=0.0;
                    break;
                }

            }
            list.add(min);
        }
        return list;
    }


    private static List<Double> getMinRow(double[][] matrix) {
        List<Double> list = new ArrayList<Double>();


        for(int i=0;i<matrix.length;i++) {
            double min = 9999999.0;
            for(int j=0;j<matrix[0].length;j++) {
                if(matrix[i][j]!=0.0) {

                    if(matrix[i][j]<min) {
                        min=matrix[i][j];
                    }

                }else {
                    min=0.0;
                    break;
                }
            }
            list.add(min);
        }
        return list;
    }
}
