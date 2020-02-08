package com.hieuminh.utils.handlingTSP;

import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.LocationDTO;
import com.hieuminh.dto.StoreDTO;
import com.hieuminh.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GPsMapUtils {
    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    private static final double distanceGreen = 20.0;
    private final static double max = 999999.0;
    private final static double transporterGoMax = 60.0;
    private static Integer maxsize=7;
    public static Object[] getDataForTrans(List<BillDTO> list, List<StoreDTO> storeDTOList, LocationDTO listUsers) throws java.lang.Exception
    {

        Object[] objects = new Object[4];
        try{
             checkListBill(list,storeDTOList);
             List<Integer> classifyMatrix =  setClassifyMatrix(list,storeDTOList);
             objects = setDataTrans(list,storeDTOList,listUsers,classifyMatrix,maxsize);
        }catch (Exception e){
            return objects;
        }
        return objects;
    }

    private static Object[] setDataTrans(List<BillDTO> list, List<StoreDTO> storeDTOList,  LocationDTO listUsers,List<Integer> classifyMatrix,Integer maxsize) {
        boolean kt=true;
        Object[] objectsData = new Object[4];
        while(kt) {
            double[][] matrix = new double[list.size()][list.size()];
            List<BillDTO> billDTOList = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (listUsers.getStoreLocationEntity().getIdstore() == classifyMatrix.get(j)) {
                    if (billDTOList.size() < maxsize) {
                        billDTOList.add(list.get(j));
                    } else {
                        break;
                    }
                }
            }
            if(billDTOList.size()>0){
            matrix = createMatrix(billDTOList, storeDTOList, listUsers.getStoreLocationEntity().getIdstore());
            Object[] objects = TSPUtils.algorithmTSP(matrix);
            if (Double.parseDouble(objects[1].toString()) > transporterGoMax) {
                maxsize--;
                setDataTrans(list,storeDTOList,listUsers,classifyMatrix,maxsize);
            }else{
                objectsData[0]=  billDTOList;
                objectsData[1]=objects[0];
                objectsData[2] = objects[1];
                objectsData[3]= listUsers.getUserEntityLocation();

                kt = false;
            }
            }else{
                kt=false;
            }
        }


        return objectsData;
    }

    private static double[][] createMatrix(List<BillDTO> billDTOList,List<StoreDTO> storeDTOs,Integer idStore) {


        BillDTO billDTO = setBillNew(storeDTOs,idStore);

        billDTOList.add(0,billDTO);

        double[][] matrix = new double[billDTOList.size()][billDTOList.size()];

        for(int i=0;i<billDTOList.size();i++){
            for (int j=0;j<billDTOList.size();j++){
                if(i==j){
                    matrix[i][j] = max;
                }else{
                    matrix[i][j]=distance(Double.parseDouble(billDTOList.get(i).getLatitude()),Double.parseDouble(billDTOList.get(i).getLongitude())
                    ,Double.parseDouble(billDTOList.get(j).getLatitude()),Double.parseDouble(billDTOList.get(j).getLongitude()),'K');
                }
            }
        }
        return matrix;
    }

    private static BillDTO setBillNew(List<StoreDTO> storeDTOs, Integer idStore) {
        BillDTO b = new BillDTO();

        Integer indexStore = 0;
        for (int i=0;i<storeDTOs.size();i++){
            if(storeDTOs.get(i).getIdstore() == idStore){
                indexStore = i;
            }
        }
        b.setLongitude(storeDTOs.get(indexStore).getLongitude());
        b.setLatitude(storeDTOs.get(indexStore).getLatitude());
        return  b;
    }

    public static List<Integer> setClassifyMatrix(List<BillDTO> list, List<StoreDTO> storeDTOList) {
        List<Integer> listIdBill = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            double maxDis = 99;
            Integer idBillStore = null;
            for (int j=0;j<storeDTOList.size();j++){
                double dis=distance(Double.parseDouble(list.get(i).getLatitude()),Double.parseDouble(list.get(i).getLongitude()),Double.parseDouble(storeDTOList.get(j).getLatitude()),Double.parseDouble(storeDTOList.get(j).getLongitude()),'K');
                if(dis<maxDis){
                    maxDis = dis;
                    idBillStore =storeDTOList.get(j).getIdstore();
                }
            }
            listIdBill.add(idBillStore);
        }

     return listIdBill;
    }

    private static void checkListBill(List<BillDTO> list,List<StoreDTO> dtoList) {
        for (int i=0;i<list.size();i++){
            boolean tk=false;
            for (int j=0;j<dtoList.size();j++){
                if(distance(Double.parseDouble(list.get(i).getLatitude()),Double.parseDouble(list.get(i).getLongitude()),Double.parseDouble(dtoList.get(j).getLatitude()),Double.parseDouble(dtoList.get(j).getLongitude()),'K')<=distanceGreen){
                    tk=true;
                }
            }
            if (tk==false){
                list.remove(i);
            }
        }
    }


    public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
