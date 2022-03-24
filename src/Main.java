import java.util.ArrayList;

public class Main {
	
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C
	// Loup = W , Chevre = S, Chou = C

	public Main() {
		String depart = "WSC//0/";
		ArrayList<String> aFaire = new ArrayList<String>();
		ArrayList<String> dejaFait = new ArrayList<String>();
		
		aFaire.add(depart);
		boolean fini = false;
		int index = 0;
		while(!aFaire.isEmpty() && !fini) {
			index ++;
			System.out.println("Tour: " + index);
			String current = aFaire.get(0);
			System.out.println("Traitement: " + current);
			ArrayList<String> successeurs = calculSuccesseurs(current);
			System.out.println("\n");
			if(trouvé(current)) {
				System.out.println("Trouvé !!!!!!");
				System.exit(0);
			}
			for (String next: successeurs) {
				System.out.println("next: " + next + "\n");
				if (!aFaire.contains(next) && !dejaFait.contains(next)) {
					aFaire.add(next);
				}
			}
			aFaire.remove(current);
			dejaFait.add(current);
		}
		
	}

	public static void main(String[] args) {
		new Main();
	}
	
	boolean validerConfig(String config) {
		int posGars = getPositionGars(config);
		if (posGars == 0) {
			if (getRiveDroite(config).contains("W") && getRiveDroite(config).contains("S")) {
				return false;
			}
			if (getRiveDroite(config).contains("C") && getRiveDroite(config).contains("S")) {
				return false;
			}
		}else {
			if (getRiveGauche(config).contains("W") && getRiveGauche(config).contains("S")) {
				return false;
			}
			if (getRiveGauche(config).contains("C") && getRiveGauche(config).contains("S")) {
				return false;
			}
		}
		return true;
	}
	
	ArrayList<String> calculSuccesseurs(String config) { //Renvoie la liste des successeurs valides d'une certaine configuration
		ArrayList<String> listeSuccesseurs = new ArrayList<String>();
		if(getPositionGars(config) == 0) {
			String rive = getRiveGauche(config);
			String[] splittedConfig;
			String[] splitted = rive.split("(?!^)");
			String test = config.replace("", "");
			if (validerConfig(switchPositionGars(test, 1))) {
				listeSuccesseurs.add(switchPositionGars(test, 1));
			}
			for(int i = 0; i < splitted.length; i++) {
				splittedConfig = config.split("/");
				splittedConfig[0] = splittedConfig[0].replaceAll(splitted[i], "");
				splittedConfig[1] += splitted[i];
				splittedConfig[2] = switchPositionGars(splittedConfig[2], 1);
				String retour = recollage(splittedConfig);
				if (validerConfig(retour)) {
					listeSuccesseurs.add(retour);
				}
			}
		}else {
			String rive = getRiveDroite(config);
			String[] splittedConfig;
			String[] splitted = rive.split("(?!^)");
			String test = config.replace("", "");
			if (validerConfig(switchPositionGars(test, 0))) {
				listeSuccesseurs.add(switchPositionGars(test, 0));
			}
			for(int i = 0; i < splitted.length; i++) {
				splittedConfig = config.split("/");
				splittedConfig[1] = splittedConfig[1].replaceAll(splitted[i], "");
				splittedConfig[0] += splitted[i];
				splittedConfig[2] = switchPositionGars(splittedConfig[2], 0);
				String retour = recollage(splittedConfig);
				if (validerConfig(retour)) {
					listeSuccesseurs.add(retour);
				}
			}
		}
		return listeSuccesseurs;
	}
	
	String recollage(String[] splittedConfig) {
		String config = "";
		for (String str : splittedConfig) {
			config += str + "/";
		}
		return config;
	}
	
	
	
	int getPositionGars(String config) { // Renvoie la a position de la rive
		String [] splitted = config.split("/");
		return Integer.parseInt(splitted[2]);
	}
	
	String switchPositionGars(String str, int value) {
		if (value == 1) {
			str = str.replace("0", "1");
		}else {
			str = str.replace("1", "0");
		}
		return str;
	}
	
	String getRiveDroite(String config) {
		return config.split("/")[1];
	}
	
	String getRiveGauche(String config) {
		return config.split("/")[0];
	}
	
	boolean trouvé(String config){
		String riveD = getRiveDroite(config);
		if (riveD.contains("W") && riveD.contains("S") && riveD.contains("C")) {
			return true;
		}else {
			return false;
		}
	}
	

}
