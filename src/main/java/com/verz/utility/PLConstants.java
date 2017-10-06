package com.verz.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PLConstants {
	
	public final static String PL = "PL";
	
	public final static Map<String, String> mapOfPLStates = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("LB", "Lubuskie");
			put("ZP", "Zachodnio-pomorskie");
			put("PM", "Pomorskie");
            put("DS", "Dolnoslaskie");
            put("WP", "Wielkoposlkie");
            put("KP", "Kujawsko-pomorskie");
            put("WN", "Warminsko-mazurskie");
            put("OP", "Opolskie");
            put("LD", "Lodzkie");
            put("MZ", "Mazowieckie");
            put("PD", "Podlaskie");
            put("MA", "Malopolskie");
            put("SL", "Swietokrzyskie");
            put("LU", "Lubelskie");
            put("PK", "Podkarpackie");
		}
	};
	
	public final static List<String> listOfPLStatesCode = new ArrayList<>(mapOfPLStates.keySet());
	public final static List<String> listOfPLStatesName = new ArrayList<>(mapOfPLStates.values());

}
