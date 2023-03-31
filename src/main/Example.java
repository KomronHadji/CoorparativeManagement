package main;

import cooperativeMgmt.*;

import java.util.*;

public class Example {

    static void print(Object obj) {
        System.out.println(obj);
    }


    public static void main(String[] args) throws CMException {
        Cooperative cp = new Cooperative();
        List<String> list;
        Integer n;
        List<Integer> listI;
        SortedMap<String, Integer> map;

        //R1
        print("R1");
        list = cp.addMembers("m4", "m2", "m3");
        print(list); //[m2, m3, m4]
        print(cp.addMembers("m5", "m1", "m2", "m6", "m1")); //[m1, m5, m6]
        System.out.println("__________________________");

        list = cp.addProducts("p2:10", "p3:15", "p1:5", "p6:20", "p7:30", "p4:6");
        print(list); //[p1, p2, p3, p4, p6, p7]
        System.out.println("__________________________");

        try {
            list = cp.addCampaign("c1", "p1", "p4", "p2", "p3");
        } catch (CMException e) {
            System.out.println("Error");
        }
        print(list); //[p1, p2, p3, p4]
        try{
        list = cp.addCampaign("c2", "p3", "p7", "p1", "p6");}
        catch (CMException e){
            System.out.println("Error");
        }
        print(list); //[p1, p3, p6, p7]
        try {
            cp.addCampaign("c3", "p3", "p11");
        } catch (CMException ex) {
            print("p11 undefined");
        }

        System.out.println("_______________________");

        //R2
        print("R2");
        n = cp.join("m1", "c1");
        print(n); //1
        print(cp.join("m1", "c2")); //2
        print(cp.join("m3", "c1")); //1
        print(cp.join("m6", "c1")); //1
        try {
            cp.join("m10", "c1");
        } catch (CMException ex) {
            print("m10 undefined");
        }
        try {
            cp.join("m4", "c10");
        } catch (CMException ex) {
            print("c10 undefined");
        }
        n = cp.addPayment("m1", 50);
        print(n); //50
        n = cp.addPayment("m1", 20);
        print(n); //70
        listI = cp.getPayments("m1");
        print(listI); //[50, 20]
        try {
            cp.addPayment("m10", 100);
        } catch (CMException ex) {
            print("m10 undefined");
        }
        try {
            cp.getPayments("m10");
        } catch (CMException ex) {
            print("m10 undefined");
        }
        System.out.println("______________________");

        //R3
        print("R3");
        n = cp.addOrder("o1", "m1", "c1", "p1:2", "p4:3", "p3:2");
        print(n); //58
        n = cp.getBalance("m1");
        print(n); //12
        try {
            cp.addOrder("o3", "m6", "c1", "p7:2");
        } catch (CMException ex) {
            print("p7 not included in campaign");
        }
        try {
            cp.addOrder("o4", "m1", "c1", "p1:2", "p4:3", "p3:2");
        } catch (CMException ex) {
            print("m1 balance is < order amount / 2");
        }
        try {
            cp.addOrder("o5", "m6", "c2", "p1:2");
        } catch (CMException ex) {
            print("m6 didn't join c2");
        }
        n = cp.addPayment("m1", 100);
        print(n); //170
        n = cp.addOrder("o6", "m1", "c2", "p3:2", "p6:2", "p7:2", "p1:2");
        print(n); //140
        n = cp.getBalance("m1");
        print(n); //-28
        n = cp.addPayment("m3", 100);
        print(n); //100
        n = cp.addOrder("o7", "m3", "c1", "p1:2", "p3:2", "p4:4");
        print(n); //64
        n = cp.getBalance("m3");
        print(n); //36

        //R4
        print("R4");
        map = cp.nOfUnitsPerProductFromOrders();
        print(map); //{p1=6, p3=6, p4=7, p6=2, p7=2}  from orders o1, o6, o7
        map = cp.amountPerMember();
        print(map); //{m1=198, m3=64}
    }

}
