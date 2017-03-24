import java.util.List;
import java.util.ArrayList;

public class Team{
  private String mName;
  private Member mMember;
  private static List<Team> instances1 = new ArrayList<Team>();
  private int mId;
  private List<Member> mMembers;

  public Team(String a){
    mName = a;
    instances1.add(this);
    mId = instances1.size();
    mMembers = new ArrayList<Member>();
  }

  public String getName(){
    return mName;
  }

  public Member getHero(){
    return mMember;
  }

  public static List<Team> all(){
    return instances1;
  }

  public static void clear() {
    instances1.clear();
  }

  public int getId() {
    return mId;
  }

  public int getHeroId(Member member){
    int id = mMembers.indexOf(member) + 1;
    return id;
  }

  public static Team find(int id) {
    return instances1.get(id-1);
  }

  public List<Member> getHeros(){
    return mMembers;
  }

  public void addHero(Member member) {
    mMembers.add(member);
  }

}
