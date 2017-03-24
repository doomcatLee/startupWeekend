import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class TestJava{

  //Variables for testing
  Member member1 = new Member("Alex");
  Member member2 = new Member("Jane");
  Team team1 = new Team("Blossom");

  @Test
  public void object_instantiates_correctly(){
    assertEquals(true, member1 instanceof Member);
    assertEquals(true, team1 instanceof Team);
  }

  @Test
  public void member_object_inside_of_team_works(){
    team1.addMember(member1);
    assertEquals(true, team1.getMembers() instanceof List<?>);
  }

  @Test
  public void team_returns_the_right_id(){
    team1.addMember(member1);
    assertEquals(2, team1.getId());
  }

  @Test
  public void team_returns_name(){
    assertEquals("Blossom", team1.getName());
  }

  @Test
  public void team_returns_list_of_all_instances(){
    assertEquals(true, team1.all() instanceof List<?>);
  }


}
