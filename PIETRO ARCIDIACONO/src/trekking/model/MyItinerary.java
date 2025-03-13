package trekking.model;

import java.util.ArrayList;
import java.util.List;

public class MyItinerary implements Itinerary {
	private Trail sentieri;
	private List<Trail> trails;

	public MyItinerary() {
		this.sentieri = null;
		this.trails = new ArrayList<>();
	}

	@Override
	public void addTrail(Trail s) throws IllegalArgumentException {
		if (trails.isEmpty()) {
			trails.add(s);
		} else {
			Trail lastTrail = trails.get(trails.size() - 1);
			if (!lastTrail.getTrailEnd().equals(s.getTrailHead())) {
				throw new IllegalArgumentException(
						"Il sentiero da aggiungere non inizia dove termina il sentiero precedente.");
			} else {
				trails.add(s);
			}
		}
	}

	@Override
	public List<Trail> getTrails() {
		return trails;
	}

	@Override
	public String isValid(double dislivelloMax, double distanzaMax, Difficulty difficoltaMax, double difficoltaMedia) {
		double maxAltitudeDifference = calcMaxAltitudeDifference();
		double totalLength = calcTotalLength();
		Difficulty maxDifficulty = calcMaxDifficulty();
		double averageDifficulty = calcAverageDifficulty();

		if (maxAltitudeDifference > dislivelloMax) {
			return "Superato il dislivello massimo consentito.";
		}

		if (totalLength > distanzaMax) {
			return "Superata la distanza massima consentita.";
		}

		if (maxDifficulty.getValue() > difficoltaMax.getValue()) {
			return "Superata la difficoltà massima consentita.";
		}

		if (averageDifficulty > difficoltaMedia) {
			return "Superata la difficoltà media consentita.";
		}
		return null;
	}

	@Override
	public double calcMaxAltitudeDifference() {
		if (trails.isEmpty()) {
			return 0;
		}
		double DislivelloMax = 0;
		double DislivelloMin = 0;
		/*
		 * for(Trail i:trails) { if(DislivelloMin<i.getMinAltitude()) {
		 * DislivelloMin=i.getMinAltitude(); } } for(Trail i:trails) {
		 * if(DislivelloMax>i.getMaxAltitude()) { DislivelloMax=i.getMaxAltitude(); } }
		 */

		for (int i = 0; i < trails.size(); i++) {
			if (DislivelloMax < trails.get(i).getMaxAltitude()) {
				DislivelloMax = trails.get(i).getMaxAltitude();
			}
		}

		return DislivelloMax;

	}

	@Override
	public double calcTotalLength() {
		if (trails.isEmpty()) {
			return 0;
		}

		double Distanza = 0;
		for (int i = 0; i < trails.size(); i++) {
			Distanza += trails.get(i).getLength();
		}
		return Distanza;
	}

	@Override
	public Difficulty calcMaxDifficulty() {
		if (trails.isEmpty()) {
			return null;
		}

		Difficulty maxDifficulty = trails.get(0).getDifficulty();
		for (Trail trail : trails) {
			if (trail.getDifficulty().compareTo(maxDifficulty) > 0) {
				maxDifficulty = trail.getDifficulty();
			}
		}
		return maxDifficulty;
	}

	@Override
	public double calcAverageDifficulty() {
		if (trails.isEmpty()) {
			return 0;
		}

		double totalWeightedDifficulty = 0;
		double totalWeight = 0;

		for (Trail trail : trails) {
			double trailWeight = trail.getLength() * trail.getAltitudeDifference();
			totalWeightedDifficulty += trailWeight * trail.getDifficulty().getValue();
			totalWeight += trailWeight;
		}

		return totalWeightedDifficulty / totalWeight;
	}

}
