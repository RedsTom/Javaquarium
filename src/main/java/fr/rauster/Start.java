package fr.rauster;

import java.util.Scanner;

public class Start {
    
    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();
        
        Scanner in = new Scanner(System.in);
        System.out.print("Start iteration ? ");
        int startIteration = in.nextInt();
        System.out.print("Total iterations ? ");
        int totalIterations = in.nextInt();
        System.out.print("Input file ? ");
        String path = in.next();
        System.out.println();
        
        aquarium.startSimulation(startIteration, totalIterations, path);
    }
    
}
