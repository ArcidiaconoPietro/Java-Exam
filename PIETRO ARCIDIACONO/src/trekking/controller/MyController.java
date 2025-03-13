package trekking.controller;

import java.util.List;

import trekking.model.*;
import trekking.ui.MessageManager;

public class MyController implements Controller {
	private Trekking trekking;
	private MessageManager manager;
	private MyItinerary itinerary;

	public MyController(Trekking trekking, MessageManager managery) {
		this.trekking = trekking;
		this.manager = managery;
		itinerary = new MyItinerary();

	}

	public List<Trail> getAllTrails() {
		return trekking.getTrailList();
	}

	@Override
	public Itinerary getItinerary() {
		return itinerary;
	}

	@Override
	public boolean checkItinerary(double dislivelloMax, double distanzaMax, Difficulty difficoltaMax,
			double difficoltaMedia) {
		if (itinerary.isValid(dislivelloMax, distanzaMax, difficoltaMax, difficoltaMedia) == null) {
			return true;
		} else {
			manager.showMessage(itinerary.isValid(dislivelloMax, distanzaMax, difficoltaMax, difficoltaMedia));

			return false;
		}
	}

	@Override
	public void addTrail(Trail s) throws IllegalArgumentException {
		itinerary.addTrail(s);
	}

	@Override
	public void reset() {
		itinerary.getTrails().clear();
	}

}
