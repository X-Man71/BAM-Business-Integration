package de.hs.furtwangen.bam.bi.jmsBank.utils;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class ValidatorUtils {
	
	@Resource(name = "configProperties")
	private Properties configProperties;

	public boolean isValidConsumerRate(char consumerRate) {
		boolean minCorrect = false;
		boolean maxCorrect = false;

		// MinConsumerRate
		if (getMinConsumerRate() != '0') {
			minCorrect = consumerRate >= getMinConsumerRate();
		} else {
			minCorrect = true;
		}

		// MaxAmount
		if (getMaxConsumerRate() != '0') {
			maxCorrect = consumerRate <= getMaxConsumerRate();
		} else {
			maxCorrect = true;
		}

		return minCorrect && maxCorrect;
	}

	public boolean isValidAmount(double amount) {
		boolean minCorrect = false;
		boolean maxCorrect = false;

		// MinAmount
		if (getMinAmount() != 0) {
			minCorrect = amount >= getMinAmount();
		} else {
			minCorrect = true;
		}

		// MaxAmount
		if (getMaxAmount() != 0) {
			maxCorrect = amount <= getMaxAmount();
		} else {
			maxCorrect = true;
		}

		return minCorrect && maxCorrect;
	}

	public boolean isValidTerm(int term) {
		boolean minCorrect = false;
		boolean maxCorrect = false;

		// MinTerm
		if (getMinTerm() != 0) {
			minCorrect = term >= getMinTerm();
		} else {
			minCorrect = true;
		}

		// MaxTerm
		if (getMaxTerm() != 0) {
			maxCorrect = term <= getMaxTerm();
		} else {
			maxCorrect = true;
		}

		return minCorrect && maxCorrect;
	}

	/* Validation needed */
	public boolean isTermValidationWanted() {
		return getMinTerm() != 0 || getMaxTerm() != 0;
	}

	public boolean isAmountValidationWanted() {
		return getMinAmount() != 0 || getMaxAmount() != 0;
	}

	public boolean isConsumerRateValidationWanted() {
		return getMinConsumerRate() != '0' || getMaxConsumerRate() != '0';
	}

	/* READ CONFIG */
	public int getMinTerm() {
		return Integer.valueOf(configProperties.getProperty(
				"bank.valid.minTerm", "0"));
	}

	public int getMaxTerm() {
		return Integer.valueOf(configProperties.getProperty(
				"bank.valid.maxTerm", "0"));
	}

	public double getMinAmount() {
		return Double.valueOf(configProperties.getProperty(
				"bank.valid.minAmount", "0"));
	}

	public double getMaxAmount() {
		return Double.valueOf(configProperties.getProperty(
				"bank.valid.maxAmount", "0"));
	}

	public char getMinConsumerRate() {
		String minConsumerRate = configProperties
				.getProperty("bank.valid.minConsumerRate");
		if (minConsumerRate.length() != 1) {
			throw new NumberFormatException("Only one character is allowed!");
		}
		return minConsumerRate.charAt(0);
	}

	public char getMaxConsumerRate() {
		String maxConsumerRate = configProperties
				.getProperty("bank.valid.maxConsumerRate");
		if (maxConsumerRate.length() != 1) {
			throw new NumberFormatException("Only one character is allowed!");
		}
		return maxConsumerRate.charAt(0);
	}
}
