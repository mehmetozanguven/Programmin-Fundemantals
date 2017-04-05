package main;

import java.io.FileNotFoundException;

import domain.*;
import fileAccessLayer.*;
import presentation.*;

public class EstateAgentApp {

	public static void main(String[] args) throws FileNotFoundException{
		EstateAgent estateAgent = new EstateAgent();
		SwingPresentation s1 = new SwingPresentation(estateAgent,estateAgent.getDal());
		
	}
}
