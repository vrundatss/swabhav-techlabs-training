package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.VirtualProxyImageSystem.test;

import com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.VirtualProxyImageSystem.model.IImage;
import com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.VirtualProxyImageSystem.model.ImageProxy;

import java.awt.*;

public class ImageProxyMain {
    public static void main(String[] args) {
        IImage image1 = new ImageProxy("C:\\Users\\vrunda.chavada\\Training Projects\\Java projects\\DesignPatterns\\src\\com\\tss\\StructuralDesignPattern\\PrxoyDesignPatternPOC\\VirtualProxyImageSystem\\model\\TSS-Logo.jpg");

        image1.display();
        image1.display();


        IImage image2 = new ImageProxy("C:\\Users\\vrunda.chavada\\Training Projects\\Java projects\\DesignPatterns\\src\\com\\tss\\StructuralDesignPattern\\PrxoyDesignPatternPOC\\VirtualProxyImageSystem\\model\\SwabhavTechlabs-logo.png");

        image2.display();
    }
}
