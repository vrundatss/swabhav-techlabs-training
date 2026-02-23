package com.tss.CreationalDesignPattern.PrototypeNetworkPOC.ShallowCopy.test;

import com.tss.CreationalDesignPattern.PrototypeNetworkPOC.DeepCopy.model.NetworkConnection;

public class NetworkPrototypeMain {
    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {

        System.out.println("Connecting to Network....");
        com.tss.CreationalDesignPattern.PrototypeNetworkPOC.DeepCopy.model.NetworkConnection networkConnection = new com.tss.CreationalDesignPattern.PrototypeNetworkPOC.DeepCopy.model.NetworkConnection();
        networkConnection.setIpAddress("232.323.43.3");
        networkConnection.loadData();


        try {
            com.tss.CreationalDesignPattern.PrototypeNetworkPOC.DeepCopy.model.NetworkConnection networkConnection2 = (com.tss.CreationalDesignPattern.PrototypeNetworkPOC.DeepCopy.model.NetworkConnection) networkConnection.clone();
            com.tss.CreationalDesignPattern.PrototypeNetworkPOC.DeepCopy.model.NetworkConnection networkConnection3 = (NetworkConnection) networkConnection.clone();

            System.out.println(networkConnection);

            networkConnection.getDomains().remove(2);

            System.out.println("===>After removing domain....\n");

            System.out.println(networkConnection);
            System.out.println(networkConnection2);
            System.out.println(networkConnection3);

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }
}
