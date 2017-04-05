package main;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;

import javax.naming.InvalidNameException;

import domain.*;
import fileAccessLayer.*;

public class PoemApplication {
	
	public static void main(String[] args) throws IOException, InvalidNameException{
		DataAccessLayer data = new DataAccessLayer();
		PoemMenu menu = new PoemMenu(data);
		menu.run();
	
	}
}
