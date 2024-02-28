package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtalsmax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		Marche marche = new Marche();
		marche.setmarche(nbEtalsmax);
	}
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		int numetal;
		//vendeur cherche un endroit pour vendre nbproduit produit
		
		numetal = marche.trouverEtalLibre();
		marche.utiliserEtal(numetal, vendeur,produit, nbProduit);
		
		//Le vendeur vendeur vend des produit à l'étal n°numetal.
		
		return null;
	}
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	/*______________________________________________________________*/
	public class Marche {
		
		private Etal[] etal;

		public  void setmarche(int nbetal) {
			etal = new Etal[nbetal];
		}
		
		public  void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			Etal netal = new Etal();
			netal.occuperEtal(vendeur,produit,nbProduit);
			etal[indiceEtal] =  netal;
		}
		
		public  int trouverEtalLibre() {
			for(int i = 0; i < etal.length; i++) {
				if(etal[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		public  Etal[] trouverEtals(String produit) {
			Etal[] etalprdt = new Etal[etal.length];
			
			int cpt =0;
			for(int i = 0; i < etal.length; i++) {
				if(etal[i].contientProduit(produit)) {
					etalprdt[cpt] = etal[i];
					cpt++;
				}
			}
			return etalprdt;
		}
		
		public  Etal trouverVendeur(Gaulois gaulois) {
			
			for(int i = 0; i < etal.length; i++) {
				if(gaulois.equals(etal[i].getVendeur())) {
					return etal[i];
				}
			
			}
			return null;
		}
		
		
		
		 public void afficherMarche() {
			 StringBuilder chaine = new StringBuilder();
			 for(int i = 0; i < etal.length ; i++) {
				 
				 chaine.append(etal[i].afficherEtal());
			 }
			 chaine.append("il reste "+trouverEtalLibre()+"étals non utilisés dans le marché.\\n");
			 System.out.println(chaine);
		 }

		
		
	}	 
		
		/*______________________________________________________________*/
	


	
}