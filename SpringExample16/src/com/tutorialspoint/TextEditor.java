package com.tutorialspoint;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
	@Autowired
	private SpellChecker spellChecker;
	private TextEditor() {
		System.out.println("Inside TextEditor constructor.");
	}
	public SpellChecker getSpellChecker() {
		return this.spellChecker;
	}
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
