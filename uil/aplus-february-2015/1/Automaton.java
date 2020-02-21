import static java.lang.System.out;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class Automaton {
    private String match;
    private String input;

    public Automaton(String match, String input) {
        match = String.format("^%s$", match);
        this.match = match.replace('U', '|'); 
        this.input = input;
    }

    public String search() {
        String cInput = this.input;
        while(cInput.length() > 0) {
            // out.println(String.format("%s %s", match, cInput));
            if(cInput.matches(this.match))
                if(cInput.length() == this.input.length())
                    return "YES";
                else
                    return String.format("NO %s", cInput.length());
            cInput = cInput.substring(0, cInput.length() - 1);
        }
        return "NO 0";
    }
}

class Main {
  public static void main(String[] args) throws IOException {
      Scanner s = new Scanner(new File("automaton.dat"));
      while(s.hasNextLine()) {
          Automaton fsa = new Automaton(s.next(), s.next());
          out.println(fsa.search());
      }
  }
}