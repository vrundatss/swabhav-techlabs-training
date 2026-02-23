package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.ProtectionProxyDocumentSystem.model;

public class DocumentProxy implements Document{
    private RealDocument realDocument;
    private final String fileName;
    private final User user;

    private static final String ADMIN_PASSWORD = "admin@123";
    private static final String MANAGER_PASSWORD = "manager@123";

    public DocumentProxy(String fileName, User user) {
        this.fileName = fileName;
        this.user = user;
    }

    @Override
    public void display() {
        if (!authenticate()) {
            System.out.println("Authentication failed for user: " + user.getName());
            return;
        }

        if(isAuthorized()){
            if(realDocument == null){
                realDocument = new RealDocument(fileName);
            }
            System.out.println("Access granted to " + user.getName() + " (" + user.getRole() + ")");
            realDocument.display();
        } else {
            System.out.println("Access denied for user: " + user.getName() + " (" + user.getRole() + ")");
        }
    }

    private boolean authenticate() {
        if (user.getRole().equalsIgnoreCase("ADMIN") && user.getPassword().equals(ADMIN_PASSWORD))
            return true;
        if (user.getRole().equalsIgnoreCase("MANAGER") && user.getPassword().equals(MANAGER_PASSWORD))
            return true;
        return false;
    }

    private boolean isAuthorized(){
        return user.getRole().equalsIgnoreCase("ADMIN") ||
                user.getRole().equalsIgnoreCase("MANAGER");
    }
}
