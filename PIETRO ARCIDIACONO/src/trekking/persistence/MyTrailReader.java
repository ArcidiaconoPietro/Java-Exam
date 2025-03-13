package trekking.persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import trekking.model.Difficulty;
import trekking.model.Trail;
import trekking.model.TrailHead;

public class MyTrailReader implements TrailReader {
	BufferedReader InnerReader;
	TrailHead Partenza = null;
	TrailHead Arrivo = null;
	Difficulty difficolta = null;

	public MyTrailReader(Reader reader) {
		this.InnerReader = new BufferedReader(reader);
	}

	@Override
	public List<Trail> readTrails() throws InvalidTrailFormatException {
		List<Trail> trails = new ArrayList<>();
		String line;
		try {
			while ((line = InnerReader.readLine()) != null) {

				String[] parts = line.split(",");
				if (parts.length < 5) {
					throw new InvalidTrailFormatException("");

				}
				for (int i = 0; i < parts[0].trim().length(); i++) {
					if (!(Character.isUpperCase(parts[0].trim().charAt(i))
							|| (Character.isDigit(parts[0].trim().charAt(i))))) {
						throw new InvalidTrailFormatException("a");
					}
				}
				String IdentificativoSentiero = parts[0];

				if (parts[1].contains("(")) {
					String[] LOCalt = parts[1].split("\\(");
					if (LOCalt[0].equals(" ")) {
						throw new InvalidTrailFormatException("b");

					}
					for (int i = 0; i < LOCalt[0].trim().length(); i++) {
						if (!(Character.isUpperCase(LOCalt[0].trim().charAt(i)) || LOCalt[0].trim().charAt(i) == ' '
								|| Character.isAlphabetic(LOCalt[0].trim().charAt(i)))) {
							throw new InvalidTrailFormatException("b");
						}
					}
					String Locazione = LOCalt[0].trim();
					Partenza = new TrailHead();
					Partenza.setName(Locazione);
					if (LOCalt[1].contains(")")) {
						String alt = LOCalt[1].substring(0, LOCalt[1].length() - 1);

						for (int i = 0; i < alt.length(); i++) {
							if (!(Character.isDigit(alt.charAt(i)))) {
								throw new InvalidTrailFormatException("c");
							}
						}

						double Altezza = Double.parseDouble(alt);
						Partenza.setAltitude(Altezza);
					} else {
						throw new InvalidTrailFormatException("d");
					}
				} else {

					throw new InvalidTrailFormatException("formato non valido");
				}
				// ---------------------------------------------------------------------

				if (parts[2].contains("(")) {
					String[] Arralt = parts[2].split("\\(");
					if (Arralt[0].equals(" ")) {
						throw new InvalidTrailFormatException("b");

					}

					for (int i = 0; i < Arralt[0].trim().length(); i++) {
						if (!(Character.isUpperCase(Arralt[0].trim().charAt(i)) || Arralt[0].trim().charAt(i) == ' '
								|| Character.isAlphabetic(Arralt[0].trim().charAt(i)))) {
							throw new InvalidTrailFormatException("formato non valido");
						}
					}
					String Locazione1 = Arralt[0].trim();
					Arrivo = new TrailHead();
					Arrivo.setName(Locazione1);
					if (Arralt[1].contains(")")) {
						String[] alt1 = Arralt[1].split("\\)");
						for (int i = 0; i < alt1[0].trim().length(); i++) {
							if (!(Character.isDigit(alt1[0].trim().charAt(i)))) {
								throw new InvalidTrailFormatException("formato non valido");
							}
						}
						double Altezza1 = Double.parseDouble(alt1[0]);
						Arrivo.setAltitude(Altezza1);
					} else {
						throw new InvalidTrailFormatException("formato non valido");
					}
				} else {
					throw new InvalidTrailFormatException("formato non valido");
				}
				double Lunghezza;
				if (parts[3].contains("km")) {
					String[] Lung = parts[3].split("km");
					for (int i = 0; i < Lung[0].trim().length(); i++) {
						if (!(Character.isDigit(Lung[0].trim().charAt(i)) || Lung[0].trim().charAt(i) == '.')) {
							throw new InvalidTrailFormatException("formato non valido");
						}
					}
					Lunghezza = Double.parseDouble(Lung[0].trim());
				} else {
					throw new InvalidTrailFormatException("formato non valido");
				}
				if (parts[4].contains("Difficolta")) {
					boolean contieneNumeri = false;

					for (int i = 0; i < parts[4].length(); i++) {
						if (Character.isDigit(parts[4].charAt(i))) {
							contieneNumeri = true;
							break;
						}
					}

					if (!contieneNumeri) {
						throw new InvalidTrailFormatException("");
					}
					String[] Diff = parts[4].trim().split(" ");

					for (int i = 0; i < Diff[1].length(); i++) {
						if (!Character.isDigit(Diff[1].charAt(i))) {
							throw new InvalidTrailFormatException("");
						}
					}

					int diffValue = Integer.parseInt(Diff[1]);

					if (diffValue == 1) {
						difficolta = difficolta.LOW;
					} else if (diffValue == 2) {
						difficolta = difficolta.MEDIUM;
					} else if (diffValue == 3) {
						difficolta = difficolta.HIGH;
					} else if (diffValue == 4) {
						difficolta = difficolta.EXTREME;
					} else {
						throw new InvalidTrailFormatException("");
					}

				} else {
					throw new InvalidTrailFormatException("");
				}

				Trail T1 = new Trail(Partenza, Arrivo, difficolta);
				T1.setLength(Lunghezza);
				T1.setName(IdentificativoSentiero);
				trails.add(T1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trails;
	}

	public void close() {
		try {
			if (InnerReader != null) {
				InnerReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
