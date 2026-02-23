package com.tss.CreationalDesignPattern.SingletonPattern;

public class DB {
    private static DB instance = null;

    private DB() {
        System.out.println("Instance of DB is created...");
    }

    static class InnerDB {
        private static final DB INSTANCE = new DB();

        public static DB getInstance() {
            if (instance == null){
                instance = new DB();
            }
            return instance;
        }
    }

//    static class InnerDB{
//        public static DB getInstance() {
//            if (instance == null){
//                instance = new DB();
//            }
//            return instance;
//        }
//    }

}
