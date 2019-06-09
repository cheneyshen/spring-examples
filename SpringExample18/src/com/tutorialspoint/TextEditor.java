package com.tutorialspoint;

import javax.annotation.Resource;

public class TextEditor {
   private SpellChecker spellChecker;
   @Resource
//   @Resource(name="spellChecker")
   public void setSpellChecker( SpellChecker spellChecker ){
      this.spellChecker = spellChecker;
   }
   public SpellChecker getSpellChecker(){
      return spellChecker;
   }
   public void spellCheck(){
      spellChecker.checkSpelling();
   }

}
