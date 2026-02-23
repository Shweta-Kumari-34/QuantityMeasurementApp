package com.apps.quantitymeasurement;

public enum WeightUnit implements IMeasurable {

	GRAM(1.0), KILOGRAM(1000.0), TONNE(1_000_000.0);

	private final double factor;

	WeightUnit(double factor) {
		this.factor = factor;
	}

	@Override
	public double convertToBase(double value) {
		return value * factor;
	}

	@Override
	public double convertFromBase(double baseValue) {
		return baseValue / factor;
	}

	@Override
	public String getCategory() {
		return "WEIGHT";
	}

}