import static java.lang.System.out;

class Main {
  public static void main(String[] args) {
      String[] names = new String[]{"John", "Sarah", "Mark"};
      String[] actors = new String[]{"MJohn Wayne", "FMeryl Streep", "MAl Pacino"};
      assert names.length == actors.length : "Team Names array and Actor/Actress names array must be equal in length.";

      for(int i = 0; i < names.length; i++) {
          out.println(
              String.format("My name is %s, and my favorite movie %s is %s.",
                names[i], actors[i].substring(0, 1).equals("M") ? "actor" : "actress", actors[i].substring(1) 
              )
          );
      }
  }
}