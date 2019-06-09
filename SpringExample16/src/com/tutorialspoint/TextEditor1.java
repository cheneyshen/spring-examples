package com.tutorialspoint;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor1 {
	private SpellChecker spellChecker;
	@Autowired
	public TextEditor1(SpellChecker spellChecker) {
		System.out.println("Inside TextEditor1 constructor.");
		this.spellChecker = spellChecker;
	}
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
